/**
 * Nitish Parkar
 * 09-187
 * BEIT- Batch 4
 */

package nitish.ai;
import java.io.*;

public class ManCow {
    int location[]={0,0,0,0};
    public static void main(String[] args)throws IOException
    {
        ManCow m=new ManCow();
        boolean loop=true;
        int choice=0;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        do
        {
            System.out.println("\n\n----------------------------------");
            System.out.println("Current State:");
		m.showState();
            System.out.println("Menu:");
            System.out.println("1. Move man from A to B");
            System.out.println("2. Move man from B to A");
            System.out.println("3. Move grass from A to B");
            System.out.println("4. Move grass from B to A");
            System.out.println("5. Move cow from A to B");
            System.out.println("6. Move cow from B to A");
            System.out.println("7. Move tiger from A to B");
            System.out.println("8. Move tiger from B to A");
            System.out.println("10. Exit");
            System.out.print("Enter Choice:");
            choice=Integer.parseInt(br.readLine());
            switch(choice)
            {
                case 1:m.manAB();
                    break;
                case 2:m.manBA();
                    break;
                case 3:m.grassAB();
                    break;
                case 4:
                    m.grassBA();
                    break;
                case 5:
                    m.cowAB();
                    break;

                case 6:
                    m.cowBA();
                    break;

                case 7:
                    m.tigerAB();
                    break;

                case 8:
                    m.tigerBA();
                    break;

                case 10:loop=false;
                    break;

                default: System.out.println("Invalid Choice");



            }
            /*if(m.x==2)
            {
                System.out.print("\nGoal is achieved!");
                loop=false;
            }*/
        }while(loop);

    }

    void showState()
    {	
		boolean flag1=false;
		boolean flag2=false;
		int count=0;
		if((location[1]==location[2])&&location[2]!=location[0])
		{
			System.out.println("Cow has eaten Grass");
			flag1=true;
		}
		if((location[3]==location[2])&&location[2]!=location[0])
		{
			System.out.println("Tiger has eaten Cow");
			flag2=true;

		}
		if(flag1||flag2)
		{
			System.out.println("****Game Over****");
			System.exit(0);
		}		
		else
		{
		System.out.print("Bank A: ");
		if(location[0]==0)
		{
			System.out.print("Man ");
			count++;
		}
		if(location[1]==0)
		{
			System.out.print("Grass ");
			count++;
		}
		if(location[2]==0)
		{
			System.out.print("Cow ");
			count++;
		}
		if(location[3]==0)
		{
			System.out.print("Tiger ");
			count++;
		}
		if(count==0)
			System.out.print("Empty ");

		count=0;
		System.out.print("\tBank B: ");
		if(location[0]==1)
		{
			System.out.print("Man ");
			count++;
		}
		if(location[1]==1)
		{
			System.out.print("Grass ");
			count++;
		}
		if(location[2]==1)
		{
			System.out.print("Cow ");
			count++;
		}
		if(location[3]==1)
		{
			System.out.print("Tiger ");
			count++;
		}
		if(count==0)
			System.out.print("Empty ");

		System.out.println("\n");
		}

		if(location[0]==1&&location[1]==1&&location[2]==1&&location[3]==1)
		{
			System.out.println("\n****Congratulations!! Goal Achieved!!****");
			System.exit(0);
		}
    }

	void manAB()
	{
		if(location[0]==1)
			System.out.println("\nMan is at Bank B");
		else
		  location[0]=1;
			
	}

   
	void manBA()
	{
		if(location[0]==0)
			System.out.println("\nMan is at Bank A");
		else
		  location[0]=0;

		
	}

	void grassAB()
	{
		if(location[0]==1)
			System.out.println("\nMan is at Bank B");
		else if(location[1]==1)
		  	System.out.println("\nGrass is at Bank B");
		else
		{
			manAB();
			location[1]=1;
		}
			
	}

	void grassBA()
	{
		if(location[0]==0)
			System.out.println("\nMan is at Bank B");
		else if(location[1]==0)
		  	System.out.println("\nGrass is at Bank B");
		else
		{	
			manBA();
			location[1]=0;
		}
	}

	void cowAB()
	{
		if(location[0]==1)
			System.out.println("\nMan is at Bank B");
		else if(location[2]==1)
		  	System.out.println("\nGrass is at Bank B");
		else
		{
			manAB();
			location[2]=1;
		}

		
	}

	void cowBA()
	{
		if(location[0]==0)
			System.out.println("\nMan is at Bank B");
		else if(location[2]==0)
		  	System.out.println("\nGrass is at Bank B");
		else
		{	
			manBA();
			location[2]=0;
		}

	}

	void tigerAB()
	{
		if(location[0]==1)
			System.out.println("\nMan is at Bank B");
		else if(location[3]==1)
		  	System.out.println("\nGrass is at Bank B");
		else
		{
			manAB();
			location[3]=1;
		}

	}

	void tigerBA()
	{
		if(location[0]==0)
			System.out.println("\nMan is at Bank B");
		else if(location[3]==0)
		  	System.out.println("\nGrass is at Bank B");
		else
		{	
			manBA();
			location[3]=0;
		}

	}


    
    
     



}

