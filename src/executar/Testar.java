package executar;

import java.io.BufferedReader;
import java.util.HashMap;
import org.apache.hadoop.conf.Configuration;
import graphics.Periodo;
import util.BuscarDados;

public class Testar {
	
	public static HashMap<Integer, String> diaDaSemana = util.Inicializar.inicializarDiaDaSemana();
	private static HashMap<String, Integer[]> atributoIndices = util.Inicializar.inicializarAtributoIndices();
	private static HashMap<String, Boolean> atributoEhNumerico = util.Inicializar.inicializarAtributoEhNumerico();
	
	public static int modoAgrupamento;
	
	public static int diaInicio;
	public static int diaFim;
	public static int mesInicio;
	public static int mesFim;
	public static int anoInicio;
	public static int anoFim;
	
	private static String atributo;
	private static int indexIniAtributo;
	private static int indexFimAtributo;
	public static boolean atributoNumerico;
	
	private static String input;
	private static String output;
	
	public static void setAtributo(String atr) {
		Integer[] indices = atributoIndices.get(atr);
		if(indices == null) return; //Nao existe o atributo procurado
		
		atributo = atr;
		atributoNumerico = atributoEhNumerico.get(atr);
		indexIniAtributo = indices[0];
		indexFimAtributo = indices[1];
	}
	public static String getAtributo() {
		return atributo;
	}
	public static Configuration novaConfiguracao() {
		Configuration conf = new Configuration();
		
		conf.setInt("diaInicio", diaInicio);
		conf.setInt("mesInicio", mesInicio);
		conf.setInt("anoInicio", anoInicio);
		
		conf.setInt("diaFim", diaFim);
		conf.setInt("mesFim", mesFim);
		conf.setInt("anoFim", anoFim);
		
		conf.setInt("indexIni", indexIniAtributo);
		conf.setInt("indexFim", indexFimAtributo);
		conf.setInt("modoAgrupamento", modoAgrupamento);
		
		return conf;
	}
	public static BufferedReader executarAtributoNumerico(int estatistica) throws Exception {
		
		String sufixo = atributo+ "" +diaInicio+ "" +mesInicio+ "" +anoInicio+ "" +diaFim+ "" 
				+mesFim+ "" +anoFim+ "" +modoAgrupamento;
		
		if(estatistica == 1) 		return BuscarDados.getMin(input, output, sufixo);
		else if(estatistica == 2)	return BuscarDados.getMax(input, output, sufixo);
		else if(estatistica == 3)	return BuscarDados.getMedia(input, output, sufixo);
		else if(estatistica == 4)	return BuscarDados.getVariancia(input, output, sufixo);
		else if(estatistica == 5)	return BuscarDados.getDesvioPadrao(input, output, sufixo);
		else if(estatistica == 6)	return BuscarDados.getDesvioMedio(input, output, sufixo);
		
		return null;
	}
	public static BufferedReader executarAtributoNaoNumerico() throws Exception {
		String sufixo = atributo+ "" +diaInicio+ "" +mesInicio+ "" +anoInicio+ "" +diaFim+ "" 
				+mesFim+ "" +anoFim+ "" +modoAgrupamento;
		return BuscarDados.getModa(input, output, sufixo);
	}
	
	public static void main(String[] args) throws Exception {
		input = args[0];
		output = args[1];
		Periodo.exibir();
	}
}