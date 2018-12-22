package operacoes.diferencaModulo;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceDifMod extends Reducer<Text, Text, Text, Text> {
	
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		
		double atual;
		double soma = 0;
		double media = 0;
		String atualStr;
		String[] tokens;
		
		for (Text val : values) {
			atualStr = val.toString();
			
			if(atualStr.contains("_")) {
				tokens = atualStr.split("_");
				atual = Double.parseDouble(tokens[0]);
				media = Double.parseDouble(tokens[1]);
				soma += (Math.abs((atual - media)));
			}
			else soma += Double.parseDouble(atualStr);
		}
		Text result = new Text(String.valueOf(soma));
		context.write(key, result);
	}
}