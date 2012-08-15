/**
 * Nitish Parkar
 * 09-187
 * BEIT- Batch 4
 */

package nitish.ai;
import java.io.*;

public class WaterBucket {

    /**
     * @param args the command line arguments
     */
    int x=0,y=0;
    public static void main(String[] args)throws IOException
    {
        WaterBucket m=new WaterBucket();
        boolean loop=true;
        int choice=0;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        do
        {
            System.out.println("\n\n----------------------------------");
            System.out.println("Current State:"+m.x+", "+m.y);
            System.out.println("Menu:");
            System.out.println("1. Fill 4G");
            System.out.println("2. Fill 3G");
            System.out.println("3. Empty 4G");
            System.out.println("4. Empty 3G");
            System.out.println("5. Transfer all water from 4G to 3G");
            System.out.println("6. Transfer all water from 3G to 4G");
            System.out.println("7. Transfer some water from 4G to 3G");
            System.out.println("8. Transfer some water from 3G to 4G");
            System.out.println("10. Exit");
            System.out.print("Enter Choice:");
            choice=Integer.parseInt(br.readLine());
            switch(choice)
            {
                case 1:m.fillX();
                    break;
                case 2:m.fillY();
                    break;
                case 3:m.emptyX();
                    break;
                case 4:
                    m.emptyY();
                    break;
                case 5:
                    m.transferXYA();
                    break;

                case 6:
                    m.transferYXA();
                    break;

                case 7:
                    m.transferXY();
                    break;

                case 8:
                    m.transferYX();
                    break;

                case 10:loop=false;
                    break;

                default: System.out.println("Invalid Choice");



            }
            if(m.x==2)
            {
                System.out.print("\nGoal is achieved!");
                loop=false;
            }
        }while(loop);

    }

   

    void fillX()
    {
        if(x<4)
            x=4;
        else
            System.out.println("4G is already full");
    }

    void fillY()
    {
        if(y<3)
            y=3;
        else
            System.out.println("3G is already full");
    }

    void emptyX()
    {
        if(x>0)
            x=0;
        else
            System.out.println("4G is already Empty");
    }

    void emptyY()
    {
        if(y>0)
            y=0;
        else
            System.out.println("3G is already Empty");
    }

    void transferXY()
    {
        if(x==0)
            System.out.println("4G is Empty");
        else if(y>=3)
            System.out.println("3G is Full");
        else if((x+y)>=3)
        {
            int tmp=x+y;
        
                tmp=tmp-3;
                x=tmp;
                y=3;
           
        }
        else
        {
            y=x+y;
            x=0;
        }
    }

    void transferYX()
    {
        if(y==0)
            System.out.println("3G is Empty");
        else if(x>=4)
            System.out.println("4G is Full");
        else if((x+y)>=4)
        {
            int tmp=x+y;
         
                tmp=tmp-4;
                y=tmp;
                x=4;
          
        }
        else
        {
            x=x+y;
            y=0;
        }
    }

     void transferXYA()
    {
        if(x==0)
            System.out.println("4G is Empty");

        else if((x+y)>3)
            System.out.println("Water will overflow. Hence cannot be done");
          
        else
        {
            y=x+y;
            x=0;
        }
    }

     void transferYXA()
    {
        if(x==y)
            System.out.println("3G is Empty");

        else if((x+y)>4)
            System.out.println("Water will overflow. Hence cannot be done");

        else
        {
            x=x+y;
            y=0;
        }
    }

     



}
