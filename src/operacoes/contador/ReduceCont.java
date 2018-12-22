package operacoes.contador;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceCont extends Reducer<Text, Text, Text, Text> {
	
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		
		long contador = 0;
		
		for (Text val : values) {
			contador += Long.parseLong(val.toString());
		}
		Text result = new Text(String.valueOf(contador));
		context.write(key, result);
	}
}
