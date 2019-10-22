//Matt Stropkey
//CS1699 Homework 3
import java.io.*;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.util.Enumeration;

public class WordCount {

    public static void tokenize(String line, Hashtable words)
    {
        StringTokenizer tokenizer = new StringTokenizer(line);
        String key = "";
        Object value;
        int val;
        while(tokenizer.hasMoreTokens())
        {
            key = tokenizer.nextToken();
            value = words.get(key);
            if(value != null)
            {
                val = (int)value;
                val++;
                words.replace(key, val);
            }
            else
            {
                words.put(key, 1);
            }
        }
    }

    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        long endTime = 0;
        int counter = 1;
        String line = "";
        File file;
        BufferedReader br = null;
        BufferedWriter writer = null;
        Hashtable<String, Integer> words = new Hashtable<String, Integer>();

        while(counter <= Integer.parseInt(args[0]))            // continue looping for however many files there are
        {
            file = new File(args[counter]);        // get the file path
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e){
                System.out.println(e);
            }
            try {
                line = br.readLine();
            } catch (IOException e){

            }
            while(line != null)
            {
				line = line.replaceAll("[\\p{Punct}*&&[^']]", " ");
                tokenize(line, words);
                try {
                    line = br.readLine();
                } catch (IOException e){

                }
            }
            counter++;
        }
        try {
            writer = new BufferedWriter(new FileWriter("Output1.txt",false));
            writer.append(' ');
			Enumeration<String> e = words.keys();
			while(e.hasMoreElements())
			{
				String key = e.nextElement();
				writer.append(key + "\t" + words.get(key) + "\n");
			}
            writer.close();
        } catch(IOException e) {
            System.out.println(e);
        }
		endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + ((endTime - startTime)/1000.0) + " seconds"); // in milliseconds
		System.exit(0);
    }


}
