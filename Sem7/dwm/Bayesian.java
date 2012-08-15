package nitish.dwm;
import java.io.*;

/**
 * Nitish Parkar
 * 09-187
 * BEIT- Batch 4
 */

public class Bayesian
{
	 
	public static void main(String args[])throws Exception
	{
		
		String age,income,student,credit;
		BufferedReader bri=new BufferedReader(new InputStreamReader(System.in));
		
		
                System.out.println("Enter age(<=30,31-40,>40):");
                age=bri.readLine();

                System.out.println("Enter income(high,medium,low):");
                income=bri.readLine().toLowerCase();

                System.out.println("Enter student(yes/no):");
                student=bri.readLine().toLowerCase();

                System.out.println("Enter credit:(fair,excellent)");
                credit=bri.readLine().toLowerCase();
		
		File f=new File("C:\\Users\\Nitish\\Downloads\\Bay\\data.txt");
		double no,yes,pyes,pno,agey,agen,incomey,incomen,studenty,studentn,credity,creditn,py,pn;
		no=yes=pyes=pno=agey=agen=incomey=incomen=studenty=studentn=credity=creditn=py=pn=0.0;
		BufferedReader br=new BufferedReader(new FileReader(f));	
		String st="";

		st=br.readLine();
		String str[];
		while((st=br.readLine())!=null)
		{
		System.out.println(st);
			str=st.split("\t");
			if(str[5].equals("no"))
				no++;
			else
				yes++;
			
			
			if((str[5].equals("yes"))&&(str[1].equals(age)))
				agey++;

			if((str[5].equals("no"))&&(str[1].equals(age)))
				agen++;

			if((str[5].equals("yes"))&&(str[2].equals(income)))
				incomey++;

			
			if((str[5].equals("no"))&&(str[2].equals(income)))
				incomen++;
			
			if((str[5].equals("yes"))&&(str[3].equals(student)))
				studenty++;
			
			if((str[5].equals("no"))&&(str[3].equals(student)))
				studentn++;

			if((str[5].equals("yes"))&&(str[4].equals(credit)))
				credity++;
			
			if((str[5].equals("no"))&&(str[4].equals(credit)))
				creditn++;
		

			
		}
		pyes=yes/(yes+no);		
		pno=no/(yes+no);

		py=pyes*(agey/yes)*(incomey/yes)*(studenty/yes)*(credity/yes);
		pn=pno*(agen/no)*(incomen/no)*(studentn/no)*(creditn/no);

		java.text.DecimalFormat df=new java.text.DecimalFormat("#.####");

		System.out.println("Probability of Computer buyer = yes:"+df.format(py));
		System.out.println("Probability of Computer buyer = yes:"+df.format(pn));
		
                if(py>pn)
                {
                System.out.println("Computer buyer = yes");
                 }
		else
                {
		System.out.println("Computer buyer = no");
                }
		


		
	}

}