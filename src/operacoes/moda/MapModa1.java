package operacoes.moda;

import java.io.IOException;
import java.util.Calendar;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapModa1 extends Mapper<LongWritable, Text, Text, Text> {
	private Calendar dataInicio = Calendar.getInstance();
	private Calendar dataFim = Calendar.getInstance();
	private int indexIni;
	private int indexFim;
	private int modoAgrupamento;
	
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		
		dataInicio.set(Calendar.DAY_OF_MONTH, conf.getInt("diaInicio", 1));
		dataInicio.set(Calendar.MONTH, conf.getInt("mesInicio", 0));
		dataInicio.set(Calendar.YEAR, conf.getInt("anoInicio", 1));
		
		dataFim.set(Calendar.DAY_OF_MONTH, conf.getInt("diaFim", 1));
		dataFim.set(Calendar.MONTH, conf.getInt("mesFim", 0));
		dataFim.set(Calendar.YEAR, conf.getInt("anoFim", 1));
		
		indexIni = conf.getInt("indexIni", 0);
		indexFim = conf.getInt("indexFim", 0);
		
		modoAgrupamento = conf.getInt("modoAgrupamento", 1); //1=ano, 2=mes, 3=dia do mes, 4=dia da semana
	}
	
	public void map(LongWritable keyWritable, Text text, Context context) throws IOException, InterruptedException {		
		
		String linha = text.toString();
		if(linha.equals("") || linha.charAt(0)=='S') return; //Linha do cabecalho ou linha vazia
		
		int dia = Integer.parseInt(linha.substring(20, 22).replace(" ", ""));
		int mes = Integer.parseInt(linha.substring(18, 20).replace(" ", ""));
		int ano = Integer.parseInt(linha.substring(14, 18).replace(" ", ""));
		
		Calendar dataAtual = Calendar.getInstance();
		dataAtual.set(Calendar.DAY_OF_MONTH, dia);
		dataAtual.set(Calendar.MONTH, mes-1);
		dataAtual.set(Calendar.YEAR, ano);
		
		boolean dataValida1 = (dataAtual.after(dataInicio) || dataAtual.equals(dataInicio)) &&
							(dataAtual.before(dataFim) || dataAtual.equals(dataFim));
		boolean dataValida2 = (dataAtual.after(dataFim) || dataAtual.equals(dataFim)) &&
							(dataAtual.before(dataInicio) || dataAtual.equals(dataInicio));
		if(!dataValida1 && !dataValida2) return; //Data deve estar dentro do limite [dataInicio, dataFim] ou [dataFim, dataInicio]
		
		String keyStr;
		Text key = new Text();
		
		if(modoAgrupamento == 1) 		keyStr = String.valueOf(ano);
		else if(modoAgrupamento == 2)	keyStr = String.valueOf(mes);
		else if(modoAgrupamento == 3)	keyStr = String.valueOf(dia);
		else 							keyStr = String.valueOf(dataAtual.get(Calendar.DAY_OF_WEEK));
		
		String valor = linha.substring(indexIni, indexFim).replace(" ", "");
		if(valor.equals("")) valor = "NAN";
		keyStr += "_" + valor;
		key.set(keyStr);
		Text value = new Text("1");
		context.write(key, value);
	}
}