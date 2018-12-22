package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import executar.Testar;
import operacoes.minimo.*;
import operacoes.maximo.*;
import operacoes.soma.*;
import operacoes.contador.*;
import operacoes.diferencaModulo.*;
import operacoes.diferencaQuadratica.*;
import operacoes.moda.*;

public class BuscarDados {
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static void executarNovoJob(Configuration conf, Class mapper, Class reducer, String input, String output) throws Exception {
		
		Job job = Job.getInstance(conf, "map reduce");
		
		job.setJarByClass(Testar.class);
		job.setMapperClass(mapper);
		job.setCombinerClass(reducer);
		job.setReducerClass(reducer);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));
		
		job.waitForCompletion(true);
	}
	@SuppressWarnings("rawtypes")
	public static void executarMapReduceSimples(String input, String output, String sufixo, int funcao) throws Exception {

		Class mapper = MapSoma.class;
		Class reducer = ReduceSoma.class;
		
		if(funcao == 1)	{		//Minimo
			mapper = MapMin.class;
			reducer = ReduceMin.class;
			output += "min" + sufixo;
		}
		else if(funcao == 2) {	//Maximo
			mapper = MapMax.class;
			reducer = ReduceMax.class;
			output += "max" + sufixo;
		}
		else if(funcao == 3) {	//Soma
			mapper = MapSoma.class;
			reducer = ReduceSoma.class;
			output += "soma" + sufixo;
		}
		else if(funcao == 4) { //Quantidade de registros
			mapper = MapCont.class;
			reducer = ReduceCont.class;
			output += "cont" + sufixo;
		}
		else if(funcao == 5) {
			mapper = MapModa1.class;
			reducer = ReduceModa1.class;
			output += "moda1" + sufixo;
		}
		else if(funcao == 6) {
			mapper = MapModa2.class;
			reducer = ReduceModa2.class;
			output += "moda2" + sufixo;
		}
		
		Configuration conf = Testar.novaConfiguracao();
		executarNovoJob(conf, mapper, reducer, input, output);
	}
	public static BufferedReader getOperacaoSimples(String input, String output, String sufixo,
											String codenome, int operacao) throws Exception {
		
		Configuration conf = new Configuration();
		Path path = new Path(output + codenome + sufixo + "/part-r-00000");
		FileSystem fs = FileSystem.get(conf);
		
		if(fs.exists(path)) return new BufferedReader(new InputStreamReader(fs.open(path)));
		
		executarMapReduceSimples(input, output, sufixo, operacao);
		return new BufferedReader(new InputStreamReader(fs.open(path)));	
	}
	public static BufferedReader getMin(String input, String output, String sufixo) throws Exception {
		return getOperacaoSimples(input, output, sufixo, "min", 1);
	}
	public static BufferedReader getMax(String input, String output, String sufixo) throws Exception {
		return getOperacaoSimples(input, output, sufixo, "max", 2);
	}
	public static BufferedReader getSoma(String input, String output, String sufixo) throws Exception {
		return getOperacaoSimples(input, output, sufixo, "soma", 3);
	}
	public static BufferedReader getCont(String input, String output, String sufixo) throws Exception {
		return getOperacaoSimples(input, output, sufixo, "cont", 4);
	}
	
	public static BufferedReader getModa(String input, String output, String sufixo) throws Exception {
		
		Configuration conf = new Configuration();
		Path path3 = new Path(output + "moda3" + sufixo + "/part-r-00000");
		FileSystem fs = FileSystem.get(conf);
		
		if(fs.exists(path3)) {
			return new BufferedReader(new InputStreamReader(fs.open(path3)));
		}
		String dirModa1 = output + "moda1" + sufixo;
		Path path1 = new Path(dirModa1 + "/part-r-00000");
		Path path2 = new Path(output + "moda2" + sufixo + "/part-r-00000");
		
		if(!fs.exists(path2)) {
			if(!fs.exists(path1)) executarMapReduceSimples(input, output, sufixo, 5);
			executarMapReduceSimples(dirModa1 , output, sufixo, 6);
		}
		
		BufferedReader brModaAux = new BufferedReader(new InputStreamReader(fs.open(path2)));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fs.create(path3)));
		Scanner sc = new Scanner(brModaAux);
		while(sc.hasNext()) {
			bw.write(sc.next()+ "\t" +sc.next().split("_")[0]); //Desconsidera textos apos underline
			if(sc.hasNext()) bw.newLine();
		}
		sc.close();
		brModaAux.close();
		bw.close();
		
		return new BufferedReader(new InputStreamReader(fs.open(path3)));
	}
	public static BufferedReader getMedia(String input, String output, String sufixo) throws Exception {
		
		Configuration conf = new Configuration();
		Path path = new Path(output + "med" + sufixo + "/part-r-00000");
		FileSystem fs = FileSystem.get(conf);
		
		if(fs.exists(path)) {
			return new BufferedReader(new InputStreamReader(fs.open(path)));
		}
		
		BufferedReader brSoma = getSoma(input, output, sufixo);
		BufferedReader brCont = getCont(input, output, sufixo);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fs.create(path)));
		
		Scanner scSoma = new Scanner(brSoma);
		Scanner scCont = new Scanner(brCont);
		String agrupado;
		double valorSoma, valorCont;
		double media;
		
		while(scSoma.hasNext()) {
			media = 0;
			scSoma.next();
			valorSoma = Double.parseDouble(scSoma.next());
			agrupado = scCont.next();
			valorCont = Double.parseDouble(scCont.next());
			
			if(valorCont != 0) media = valorSoma/valorCont;
			bw.write(agrupado+ "\t" +media);
			if(scSoma.hasNext()) bw.newLine();
		}
		scSoma.close();
		scCont.close();
		brSoma.close();
		brCont.close();
		bw.close();
		
		return new BufferedReader(new InputStreamReader(fs.open(path)));
	}
	public static BufferedReader getDiferencaModulo(String input, String output, String sufixo) throws Exception {
		
		Configuration confPath = new Configuration();
		Path path = new Path(output + "difmod" + sufixo + "/part-r-00000");
		FileSystem fs = FileSystem.get(confPath);
		
		if(fs.exists(path)) {
			return new BufferedReader(new InputStreamReader(fs.open(path)));
		}
		
		String medias = "";
		BufferedReader brMedia = getMedia(input, output, sufixo);
		Scanner sc = new Scanner(brMedia);
		
		while(sc.hasNext()) medias += sc.next() + " ";
		medias = medias.substring(0, medias.length()-1); //Removendo ultimo espaco
		sc.close();
		brMedia.close();
		
		Configuration confJob = Testar.novaConfiguracao();
		confJob.set("medias", medias);
		output += "difmod" + sufixo;
		executarNovoJob(confJob, MapDifMod.class, ReduceDifMod.class, input, output);
		
		return new BufferedReader(new InputStreamReader(fs.open(path)));
	}
	public static BufferedReader getDesvioMedio(String input, String output, String sufixo) throws Exception {
		
		Configuration conf = new Configuration();
		Path path = new Path(output + "desvmed" + sufixo + "/part-r-00000");
		FileSystem fs = FileSystem.get(conf);
		
		if(fs.exists(path)) {
			return new BufferedReader(new InputStreamReader(fs.open(path)));
		}
		
		BufferedReader brDifMod = getDiferencaModulo(input, output, sufixo);
		BufferedReader brCont = getCont(input, output, sufixo);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fs.create(path)));
		
		Scanner scDifMod = new Scanner(brDifMod);
		Scanner scCont = new Scanner(brCont);
		String agrupado;
		double valorDifMod, valorCont;
		double desvMed;
		
		while(scDifMod.hasNext()) {
			desvMed = 0;
			scDifMod.next();
			valorDifMod = Double.parseDouble(scDifMod.next());
			agrupado = scCont.next();
			valorCont = Double.parseDouble(scCont.next());
			
			if(valorCont > 0) desvMed = valorDifMod/valorCont;
			bw.write(agrupado+ "\t" +desvMed);
			if(scDifMod.hasNext()) bw.newLine();
		}
		scDifMod.close();
		scCont.close();
		brDifMod.close();
		brCont.close();
		bw.close();
		
		return new BufferedReader(new InputStreamReader(fs.open(path)));
	}
	public static BufferedReader getDiferencaQuadratica(String input, String output, String sufixo) throws Exception {
		
		Configuration confPath = new Configuration();
		Path path = new Path(output + "difquad" + sufixo + "/part-r-00000");
		FileSystem fs = FileSystem.get(confPath);
		
		if(fs.exists(path)) {
			return new BufferedReader(new InputStreamReader(fs.open(path)));
		}
		
		String medias = "";
		BufferedReader brMedia = getMedia(input, output, sufixo);
		Scanner sc = new Scanner(brMedia);
		
		while(sc.hasNext()) medias += sc.next() + " ";
		medias = medias.substring(0, medias.length()-1); //Removendo ultimo espaco
		sc.close();
		brMedia.close();
		
		Configuration confJob = Testar.novaConfiguracao();
		confJob.set("medias", medias);
		output += "difquad" + sufixo;
		executarNovoJob(confJob, MapDifQuad.class, ReduceDifQuad.class, input, output);
		
		return new BufferedReader(new InputStreamReader(fs.open(path)));
	}
	public static BufferedReader getVariancia(String input, String output, String sufixo) throws Exception {
		
		Configuration conf = new Configuration();
		Path path = new Path(output + "var" + sufixo + "/part-r-00000");
		FileSystem fs = FileSystem.get(conf);
		
		if(fs.exists(path)) {
			return new BufferedReader(new InputStreamReader(fs.open(path)));
		}
		
		BufferedReader brDifQuad = getDiferencaQuadratica(input, output, sufixo);
		BufferedReader brCont = getCont(input, output, sufixo);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fs.create(path)));
		
		Scanner scDifQuad = new Scanner(brDifQuad);
		Scanner scCont = new Scanner(brCont);
		String agrupado;
		double valorDifQuad, valorCont;
		double variancia;
		
		while(scDifQuad.hasNext()) {
			variancia = 0;
			scDifQuad.next();
			valorDifQuad = Double.parseDouble(scDifQuad.next());
			agrupado = scCont.next();
			valorCont = Double.parseDouble(scCont.next());
			
			if(valorCont > 1) variancia = valorDifQuad/(valorCont-1);
			bw.write(agrupado+ "\t" +variancia);
			if(scDifQuad.hasNext()) bw.newLine();
		}
		scDifQuad.close();
		scCont.close();
		brDifQuad.close();
		brCont.close();
		bw.close();
		
		return new BufferedReader(new InputStreamReader(fs.open(path)));
	}
	public static BufferedReader getDesvioPadrao(String input, String output, String sufixo) throws Exception {
		
		Configuration conf = new Configuration();
		Path path = new Path(output + "desvpad" + sufixo + "/part-r-00000");
		FileSystem fs = FileSystem.get(conf);
		
		if(fs.exists(path)) {
			return new BufferedReader(new InputStreamReader(fs.open(path)));
		}
		
		BufferedReader brVar = getVariancia(input, output, sufixo);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fs.create(path)));
		
		Scanner scVar = new Scanner(brVar);
		String agrupado;
		double desvPad;
		
		while(scVar.hasNext()) {
			agrupado = scVar.next();
			desvPad = Double.parseDouble(scVar.next());
			desvPad = Math.sqrt(desvPad);
			
			bw.write(agrupado+ "\t" +desvPad);
			if(scVar.hasNext()) bw.newLine();
		}
		scVar.close();
		brVar.close();
		bw.close();
		
		return new BufferedReader(new InputStreamReader(fs.open(path)));
	}
}
