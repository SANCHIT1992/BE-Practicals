package nitish.dwm;
import java.io.*;

/**
 *
 * @author Nitish
 */
public class DWM1
{
    public double initialentropy()throws FileNotFoundException,IOException
    {
        double ie = 0.0;
        
        double sht, med, tal;
        sht = med = tal = 0.0;
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Nitish\\Downloads\\data.txt"));
        String str;
        String[] s;

        while ((str = br.readLine()) != null)
        {
                s = str.split(" ");
                if (s[2].equals("s"))
                {
                    sht++;
                } 
                else if (s[2].equals("m"))
                    {
                        med++;
                    } 
                else
                {
                        tal++;
                }
                
          }
        
        ie = (sht / 9) * (Math.log(9 / sht) / (Math.log(2))) + (med / 9) * (Math.log(9 / med) / (Math.log(2))) + (tal / 9) * (Math.log(9 / tal) / (Math.log(2)));
        return ie;
    }

    public double genderentropy() throws Exception
    {
        double male_entropy, female_entropy = 0.0;
        double male, female, mshort, mmedium, mtall, fshort, fmedium, ftall;
        male = female = mshort = mmedium = mtall = fshort = fmedium = ftall = 0.0;
        
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Nitish\\Downloads\\data.txt"));
            String str;
            String[] s;
            while ((str = br.readLine()) != null)
            {
                s = str.split(" ");
                if (s[0].equals("m")) {
                    if (s[2].equals("s")) {
                        mshort++;
                    } else {
                        if (s[2].equals("m")) {
                            mmedium++;
                        } else {
                            mtall++;
                        }
                    }
                    male++;
                }

                if (s[0].equals("f"))
                {
                    if (s[2].equals("s"))
                    {
                        fshort++;
                    } 
                    else if (s[2].equals("m"))
                    {
                            fmedium++;
                    } 
                    else
                    {
                            ftall++;
                    }
                    
                    female++;
                }
            }
        
        male_entropy = (mshort / male) * (Math.log(male / mshort) / (Math.log(2))) + (mmedium / male) * (Math.log(male / mmedium) / (Math.log(2))) + (mtall / male) * (Math.log(male / mtall) / (Math.log(2)));
        female_entropy = (fshort / female) * (Math.log(female / fshort) / (Math.log(2))) + (fmedium / female) * (Math.log(female / fmedium) / (Math.log(2))) + (ftall / female) * (Math.log(female / ftall) / (Math.log(2)));
        return (((male / 9) * male_entropy) + ((female / 9) * female_entropy));
    }

    public static void main(String args[]) throws Exception
    {
        double ie, ge;
        DWM1 obj = new DWM1();
        ie = obj.initialentropy();
        ge = obj.genderentropy();
         java.text.DecimalFormat df = new java.text.DecimalFormat("#.####");
        System.out.println("Initial Entropy is : " + df.format(ie));
        System.out.println("Gender Entropy is : " + df.format(ge));
        System.out.println("Information Gain : " + df.format(ie - ge));
    }
}

