package operacoes.maximo;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceMax extends Reducer<Text, Text, Text, Text> {

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		
		double atual;		
		double max = 0;
	
		for (Text val : values) {
			atual = Double.valueOf(val.toString());
			if(atual > max) max = atual;
		}
		Text result = new Text(String.valueOf(max));
		context.write(key, result);
	}
}
