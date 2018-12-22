package operacoes.moda;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceModa2 extends Reducer<Text, Text, Text, Text> {
	
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		
		String valStr;
		String moda = "";
		long quantidade;
		long maior = 0;
		
		String[] tokens;
		
		for (Text val : values) {
			valStr = val.toString();
			
			tokens = valStr.split("_");
			quantidade = Long.parseLong(tokens[1]);
			
			if(quantidade > maior) {
				maior = quantidade;
				moda = valStr;
			}
		}
		Text result = new Text(moda);
		context.write(key, result);
	}
}