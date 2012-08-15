/**
 * Nitish Parkar
 * 09-187
 * BEIT- Batch 4
 */
package nitish.ai;
import java.io.*;

public class PuzzleRandom {

    
    int pz[][]=new int[3][3];
    int is[][]={{2,8,3},{1,6,4},{7,0,5}};
    int gs[][]=new int[3][3];

    PuzzleRandom()
    {
       int gsa[][]={{0,8,7},{4,5,6},{3,2,1}};
       int gsb[][]={{1,2,3},{8,0,4},{7,6,5}};
       int gsc[][]={{1,2,3},{4,5,6},{7,8,0}};
       int gsd[][]={{1,6,7},{2,5,8},{3,4,0}};
       int i= new java.util.Random().nextInt(4);

       pz=is;
       if(i==0)
           gs=gsa;
       else if(i==1)
           gs=gsb;
       else if(i==2)
           gs=gsc;
       else
           gs=gsd;
    }


    public static void main(String[] args) throws IOException
    {
       //new PuzzleRandom().initRandom();
       new PuzzleRandom().init();
    }
   
    
    void init() throws IOException
    {
   
        boolean loop=true;
        int choice=0;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        do
        {
            System.out.println("\n\n----------------------------------");
            System.out.println("Current State:\tGoal State:");
            currentState();
            System.out.println("\nMenu:");
            System.out.println("8. Move Up");
            System.out.println("6. Move Right");
            System.out.println("2. Move Down");
            System.out.println("4. Move Left");
           
            System.out.println("10. Exit");
            System.out.print("Enter Choice:");
            choice=Integer.parseInt(br.readLine());
            switch(choice)
            {
                case 8:moveUp();
                    break;
                case 6:moveRight();
                    break;
                case 2:moveDown();
                    break;
                case 4:
                    moveLeft();
                    break;
                
                    
                    
                case 10:System.exit(0);

                default: System.out.println("Invalid Choice");



            }
            if(hasReachedGoal())
            {
                System.out.println("Goal Achieved!\nDo you want to continue?[y/n]");
                
                if('y'!=br.readLine().charAt(0))
                    System.exit(0);
                else
                    new PuzzleRandom().init();

            }
        }while(loop);
        
        
    }
    
    void moveUp()
    {
        for(int i=0;i<3;i++)
        {
            if((pz[0][i])==0)
            {
                System.out.println("Can't move UP");
                return;
            }
        }
        int x=-1,y=-1;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
               if((pz[i][j])==0)
               {
                   x=i;
                   y=j;
                   break;
               }
            }
            
        }
       swap(x,y,x-1,y);
       
       
        
    }
    
    void moveDown()
    {
        for(int i=0;i<3;i++)
        {
            if(pz[2][i]==0)
            {
                System.out.println("Can't move Down");
                return;
            }
        }
        int x=-1,y=-1;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
               if((pz[i][j])==0)
               {
                   x=i;
                   y=j;
                   break;
               }
            }
            
        }
       swap(x,y,x+1,y);
       
       
        
    }
    
    
    void moveRight()
    {
        for(int i=0;i<3;i++)
        {
            if(pz[i][2]==0)
            {
                System.out.println("Can't move Right");
                return;
            }
        }
        int x=-1,y=-1;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
               if((pz[i][j])==0)
               {
                   x=i;
                   y=j;
                   break;
               }
            }
            
        }
       swap(x,y,x,y+1);
       
       
        
    }
    
    
    void moveLeft()
    {
        for(int i=0;i<3;i++)
        {
            if(pz[i][0]==0)
            {
                System.out.println("Can't move Left");
                return;
            }
        }
        int x=-1,y=-1;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
               if((pz[i][j])==0)
               {
                   x=i;
                   y=j;
                   break;
               }
            }
            
        }
       swap(x,y,x,y-1);
       
       
        
    }
    
    
    void currentState()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                System.out.print(pz[i][j]+" ");
            }
            System.out.print("\t\t");
             for(int j=0;j<3;j++)
            {
                System.out.print(gs[i][j]+" ");
            }
            System.out.println("");
        }
        
    }
    boolean hasReachedGoal()
    {
        
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if((pz[i][j])!=gs[i][j])
                    return false;
            }
            
        }
        return true;        
        
    }
    
    void swap(int sx,int sy,int dx,int dy)
    {
        if(sx!=-1&&sy!=-1)
        {
        int tmp=pz[sx][sy];
        pz[sx][sy]=pz[dx][dy];
        pz[dx][dy]=tmp;
        }
    }

     void copyArray(int a[][],int b[][])
    {

        for(int i=0;i<3;i++)
        {
            System.arraycopy(a[i], 0, b[i], 0, 3);

        }

       
    }
   
    
    
}
