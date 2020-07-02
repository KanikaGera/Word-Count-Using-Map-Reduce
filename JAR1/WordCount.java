//Without Writing Stop Words
import java.util.*;
import java.io.*;

import java.util.regex.Pattern;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.net.URI;
import org.apache.log4j.Logger;

import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.io.NullWritable;

public class WordCount extends Configured implements Tool 
{

  

 
  private static final Logger LOG = Logger.getLogger(WordCount.class);



  public static void main(String[] args) throws Exception 
  {
    int res = ToolRunner.run(new WordCount(), args);
    System.exit(res);
  }

  public int run(String[] args) throws Exception 
  {
    Job job = Job.getInstance(getConf(), "wordcount");
    job.setJarByClass(this.getClass());
    // Use TextInputFormat, the default unless job.setInputFormatClass is used
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    job.addCacheFile(new Path(args[2]).toUri());
    job.setMapperClass(Map.class);
    job.setCombinerClass(Reduce.class);
    job.setReducerClass(Reduce.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);

    MultipleOutputs.addNamedOutput(job,"wordcount",TextOutputFormat.class,Text.class,IntWritable.class);

    return job.waitForCompletion(true) ? 0 : 1;
  }

  public static class Map extends Mapper<LongWritable, Text, Text, IntWritable> 
  {
    private final static IntWritable one = new IntWritable(1);
    private final static IntWritable ignore = new IntWritable(-1);
    private Text word = new Text();
    private long numRecords = 0;    
    private static final Pattern WORD_BOUNDARY = Pattern.compile("\\s*\\b\\s*");
    private static  Set<String> elementSet = new HashSet<String>();
    

    protected void setup(Mapper.Context context)throws IOException,InterruptedException 
    {
   
      URI[] localPaths= context.getCacheFiles(); 
      URI location = localPaths[0];  
      BufferedReader br=new BufferedReader(new FileReader(new File(location.getPath()).getName()));
      String line="";
      while((line=br.readLine())!=null)
	{
		elementSet.add(line);
	}

     
   }

    public void map(LongWritable offset, Text lineText, Context context) throws IOException, InterruptedException 
    {
      String line = lineText.toString().toLowerCase();
      Text currentWord = new Text();
      for (String word : WORD_BOUNDARY.split(line)) 
      {
            if (word.isEmpty() || elementSet.contains(word))
            {
                continue;
            }
          
		currentWord = new Text(word);
            	context.write(currentWord,one);

           
       }
    } 
}



  public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> 
  {
    private static MultipleOutputs mos;
    
    protected void setup(Reducer.Context context)throws IOException,InterruptedException 
    {
         mos= new MultipleOutputs(context);
    }
    @Override
    public void reduce(Text word, Iterable<IntWritable> counts, Context context)
        throws IOException, InterruptedException 
      {

	      //remove duplicates from file
	      int sum = 0;
	      for (IntWritable count : counts) 
	      {
		sum += count.get();
	      }

	      mos.write("wordcount",word, new IntWritable(sum));
              
     }

   protected void cleanup(Reducer.Context context)throws IOException,InterruptedException 
    {
         mos.close();
    }

  }


}
