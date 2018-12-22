package operacoes.moda;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapModa2 extends Mapper<LongWritable, Text, Text, Text> {
	public void map(LongWritable keyWritable, Text text, Context context) throws IOException, InterruptedException {		
		
		StringTokenizer itr = new StringTokenizer(text.toString());
		
		String keyStr = itr.nextToken();
		String valueStr = itr.nextToken();
		String[] pieces = keyStr.split("_");
		
		Text key = new Text(String.format("%2s", pieces[0]));
		Text value = new Text(pieces[1]+ "_" +valueStr);
		
		context.write(key, value);
	}
}