package nitish.dwm;

import java.io.*;
import java.util.*;
/**
 * Nitish Parkar
 * 09-187
 * Sandip Jadhav
 * 05-188
 * BEIT- Batch 4
 */
public class NearestNeighbour
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader br=new BufferedReader(new FileReader(new File("C:\\Users\\Nitish\\Documents\\NetBeansProjects\\Sem7\\src\\nitish\\dwm\\nndata")));
        BufferedReader bri=new BufferedReader(new InputStreamReader(System.in));

        int pass=1;

        String st="";
        String str[]=null;

        while((st=br.readLine())!=null)
        {
            str=st.split(",");
        }

        int[] obj=new int[str.length];

        for(int x=0;x<str.length;x++)
        {
            obj[x]=Integer.parseInt(str[x]);
        }
        System.out.println("Objects:");
        System.out.println(java.util.Arrays.toString(obj));

        System.out.println("Enter threshold:");
        int t=Integer.parseInt(bri.readLine());

        ArrayList<Integer> clusters[]=new ArrayList[100];

        int N=1;
        clusters[0]=new ArrayList<Integer>();
        clusters[0].add(obj[0]);

        System.out.println("\nPass"+(pass++));
        System.out.println("Number of Clusters:"+N);
        for(int i=0;i<N;i++)
        {
                System.out.println("Cluster "+(i+1)+" "+clusters[i]);
        }

        for(int i=1;i<obj.length;i++)
        {
            int clsi=-1,mindist=9999;
            for(int j=0;j<N;j++)
            {
                for(int k=0;k<clusters[j].size();k++)
                {
                    int tmp=Math.abs(obj[i]-clusters[j].get(k));
                    if(tmp<mindist && tmp<=t)
                    {
                        clsi=j;
                    }
                }
            }

            if(clsi==-1)
            {
                clusters[N++]=new ArrayList<Integer>();
                clusters[N-1].add(obj[i]);
            }
            else
            {
              clusters[clsi].add(obj[i]);
            }

           System.out.println("\nPass"+(pass++));
            System.out.println("Number of Clusters:"+(N+1));
            for(int z=0;z<N;z++)
            {
                System.out.println("Cluster "+(z+1)+" "+clusters[z]);
            }

        }


        System.out.println("\nFinal no of Clusters:"+(N));
        for(int i=0;i<N;i++)
        {
                System.out.println("Cluster "+(i+1)+" "+clusters[i]);
        }

    }

}
