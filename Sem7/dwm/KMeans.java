package nitish.dwm;
import java.io.*;
import java.util.*;
/**
 * Nitish Parkar
 * 09-187
 * BEIT- Batch 4
 */
public class KMeans
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader br=new BufferedReader(new FileReader(new File("C:\\Users\\Nitish\\Documents\\NetBeansProjects\\Sem7\\src\\nitish\\dwm\\kmeansdata")));
        BufferedReader bri=new BufferedReader(new InputStreamReader(System.in));
        

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

        System.out.println("Objects:"+Arrays.toString(obj));

        System.out.println("\nEnter number of clusters:");
        int k=Integer.parseInt(bri.readLine());

        ArrayList<Integer> clusters[]=new ArrayList[k];
        float[] centers=new float[k];
        float[] oldcenters=new float[k];

        for(int x=0;x<k;x++)
        {
            clusters[x]=new ArrayList<Integer>();
            clusters[x].add(obj[x]);
            centers[x]=obj[x];
        }
        int step=1;
        System.out.println("\nStep "+(step++)+":");
        for(int i=0;i<k;i++)
        {
            System.out.println(clusters[i]+" Center:"+centers[i]);
        }
        
        for(;;)
        {
            
            for(int i=0;i<k;i++)
            {
                clusters[i].clear();
            }
            for(int i=0;i<obj.length;i++)
            {
                float mindist=9999,tmp=99999;
                int ci=-1;
                for(int j=0;j<k;j++)
                {
                    tmp=Math.abs(centers[j]-obj[i]);
                    if(tmp<mindist)
                    {
                        mindist=tmp;
                        ci=j;

                    }
                }

                clusters[ci].add(obj[i]);

              
            }
            

            System.arraycopy(centers, 0, oldcenters, 0, k);
            int ecount=0;

            for(int i=0;i<k;i++)
            {
                float total=0;
                for(int j=0;j<clusters[i].size();j++)
                {
                    total+=clusters[i].get(j);
                }

                centers[i]=total/clusters[i].size();
                if(centers[i]==oldcenters[i])
                    ecount++;
            }
            if(ecount==k)
                break;

            System.out.println("\nStep "+(step++)+":");
            for(int i=0;i<k;i++)
            {
                System.out.println(clusters[i]+" Center:"+centers[i]);
            }
        }

        System.out.println("\nFinal clusters are:");
        for(int i=0;i<k;i++)
        {
            System.out.println(clusters[i]+" Center:"+centers[i]);
        }
    }

}
