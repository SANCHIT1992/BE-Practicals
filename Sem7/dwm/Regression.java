package nitish.dwm;
import java.io.*;

/**
 * Nitish Parkar
 * 09-187
 * BEIT- Batch 4
 */

public class Regression {

   
    public static void main(String[] args) throws Exception
    {
        BufferedReader br=new BufferedReader(new FileReader(new File("C:\\Users\\Nitish\\Documents\\NetBeansProjects\\Sem7\\src\\nitish\\dwm\\reg_data.txt")));
        String st=br.readLine();
        String[] str;
        java.text.DecimalFormat df=new java.text.DecimalFormat(".####");
        double[] yoe=new double[100];
        double[] sal=new double[100];
        int i=0;
        double xbar=0,ybar=0;
       
        while((st=br.readLine())!=null)
        {
            
            str=st.split(" ");
            yoe[i]=Double.parseDouble(str[0]);
            xbar+=yoe[i];
            sal[i]=Double.parseDouble(str[1]);
            ybar+=sal[i];
            i++;
        }
        xbar/=i;
        ybar/=i;

        double[] xxbar=new double[i];
        double[] yybar=new double[i];
        double xmx=0,xiy=0;
        
        for(int j=0;j<i;j++)
        {
            xxbar[j]=yoe[j]-xbar;
            yybar[j]=sal[j]-ybar;
            yybar[j]*=xxbar[j];
            xxbar[j]*=xxbar[j];
            xmx+=xxbar[j];
            xiy+=yybar[j];
        }
        
        System.out.println("Xbar:"+xbar);
        System.out.println("Ybar:"+ybar);
           //System.out.println("(xi-xbar)^2:"+xmx);
             // System.out.println("xy:"+xiy);
        double beta=xiy/xmx;
        double alpha=ybar-beta*xbar;
        System.out.println("Alpha:"+df.format(alpha));
        System.out.println("Beta:"+df.format(beta));
        
        BufferedReader bri=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Years of experience:");
        double yrs=Double.parseDouble(bri.readLine());
        
        System.out.println("Salary:"+df.format(alpha+beta*yrs));
    }

}
