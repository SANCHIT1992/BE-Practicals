package nitish.dwm;
import java.io.*;
import java.util.*;

/**
 * Nitish Parkar
 * 09-187
 * BEIT- Batch 4
 */

public class KNN {
    
    public static void main(String[] args) throws Exception
    {
        BufferedReader br=new BufferedReader(new FileReader(new File("C:\\Users\\Nitish\\Documents\\NetBeansProjects\\Sem7\\src\\nitish\\dwm\\knndata.txt")));
        BufferedReader bri=new BufferedReader(new InputStreamReader(System.in));
        String st;
        String[] str;
        List<Float> height=new ArrayList<Float>();
        List<Float> distance=new ArrayList<Float>();
        List<String> cls=new ArrayList<String>();


        int count=0;
        System.out.println("Enter Height to classify:");
        float unknown_sample=Float.parseFloat(bri.readLine());
        
        while((st=br.readLine())!=null)
        {
            str=st.split(" ");
            height.add(Float.valueOf(str[1]));
            distance.add(Math.abs(unknown_sample-height.get(count).floatValue()));
            cls.add(str[2]);
            count++;
        }

        int k=(int)Math.round(Math.sqrt(count));
        System.out.println("Number of Nearest Samples(K):"+k);
        float[] kheight=new float[k];
        float[] kdistance=new float[k];
        String[] kclass=new String[k];
        for(int l=0;l<k;l++)
        {
            kheight[l]=height.get(l).floatValue();
            kdistance[l]=distance.get(l).floatValue();
            kclass[l]=cls.get(l);
        }
        
        int largest_distance_index=0;
        for(int i=k;i<count;i++)
        {
            
            for(int l=0;l<k;l++)
            {
                if(kdistance[l]>kdistance[largest_distance_index])
                {
                    largest_distance_index=l;
                   
                }
            }

            if(distance.get(i).floatValue()<kdistance[largest_distance_index])
            {
                kheight[largest_distance_index]=height.get(i).floatValue();
                kdistance[largest_distance_index]=distance.get(i).floatValue();
                kclass[largest_distance_index]=cls.get(i);
            }
        }
        //System.out.println(java.util.Arrays.toString(height));
        //System.out.println(java.util.Arrays.toString(distance));
        //System.out.println(java.util.Arrays.toString(cls));
        int scount=0,mcount=0,tcount=0;
        
        for(int l=0;l<k;l++)
        {
            if(kclass[l].equalsIgnoreCase("m"))
                mcount++;
            if(kclass[l].equalsIgnoreCase("t"))
                tcount++;
            if(kclass[l].equalsIgnoreCase("s"))
                scount++;
        }
        System.out.println("Samples with class medium:"+mcount);
        System.out.println("Samples with class short:"+scount);
        System.out.println("Samples with class tall:"+tcount);
        System.out.println("\nFinal "+k+" nearest samples:");
        
        java.text.DecimalFormat df=new java.text.DecimalFormat("##.##");
        for(int l=0;l<k;l++)
        {
            System.out.println("Height:"+kheight[l]+"\tDistance:"+df.format(kdistance[l])+"\tClass:"+kclass[l]);
        }
        
        
        if(mcount>tcount&&mcount>scount)
            System.out.println("Hence Unknown sample with height "+unknown_sample+" will be classified as Medium");
        
        else if(scount>tcount&&scount>mcount)
            System.out.println("Hence Unknown sample with height "+unknown_sample+" will be classified as Short");
   
        else
            System.out.println("Hence Unknown sample with height "+unknown_sample+" will be classified as Tall");
   
    }

}
