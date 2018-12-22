package operacoes.minimo;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceMin extends Reducer<Text, Text, Text, Text> {
	
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		
		double atual;		
		double min = Double.MAX_VALUE;
	
		for (Text val : values) {
			atual = Double.valueOf(val.toString());
			if(atual < min) min = atual;
		}
		Text result = new Text(String.valueOf(min));
		context.write(key, result);
	}
}
