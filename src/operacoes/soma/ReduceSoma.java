package operacoes.soma;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceSoma extends Reducer<Text, Text, Text, Text> {
	
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		
		double atual;
		double soma = 0;
		
		for (Text val : values) {
			atual = Double.parseDouble(val.toString());
			soma += atual;
		}
		Text result = new Text(String.valueOf(soma));
		context.write(key, result);
	}
}