package nitish.ai;


import java.io.*;
/**
 * Name:Nitish Parkar
 * Roll No: 09-187
 * Batch 4
 */
public class PuzzleAI {
    int tmp[][]=new int[3][3];
    int pz[][]={{2,8,3},{1,6,4},{7,0,5}};
    int gs[][]={{1,2,3},{8,0,4},{7,6,5}};
    int choice[]={1,1,1,1}; //0-left 1-up 2-right 3-down
    public static void main(String[] args) throws IOException
    {
       new PuzzleAI().init();
    }
    void init() throws IOException
    {
   
        boolean loop=true;
        int switch_value=0;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        do
        {
            System.out.println("\n\n----------------------------------");
            System.out.println("Current State:");
            currentState();
	    checkMoves();
            System.out.println("\nMenu:");
		if(choice[1]==1)
                {
                    copyTemp();
                    moveUp();
                    
                    System.out.println("8. Move Up [Heuristic="+calcH()+"]");
                    copyReverse();
                }
                if(choice[2]==1)
                {
                     copyTemp();
                    moveRight();
                    
                    System.out.println("6. Move Right [Heuristic="+calcH()+"]");
                    copyReverse();
                }
                if(choice[3]==1)
                {
                    copyTemp();
                    moveDown();
                    
                    System.out.println("2. Move Down [Heuristic="+calcH()+"]");
                    copyReverse();
                }
                    if(choice[0]==1)
                    {
                        copyTemp();
                    moveLeft();
                    
                    System.out.println("4. Move Left [Heuristic="+calcH()+"]");
                    copyReverse();
                    }
            System.out.println("10. Exit");
            System.out.print("Enter Choice:");
            switch_value=Integer.parseInt(br.readLine());
            switch(switch_value)
            {
                case 8:
				if(choice[1]==1)
					moveUp();
				else
					System.out.println("Invalid Choice");
                                break;
                case 6:
				if(choice[2]==1)
					moveRight();
				else
					System.out.println("Invalid Choice");

                                    break;
                case 2:
			if(choice[3]==1)
					moveDown();
			else
					System.out.println("Invalid Choice");

                    break;
                case 4:
			if(choice[0]==1)
                    moveLeft();
			else
					System.out.println("Invalid Choice");
                    break;
     
                case 10:loop=false;
                    break;

                default: System.out.println("Invalid Choice");



            }
            if(hasReachedGoal())
            {
                System.out.println("Goal Achieved!");
                loop=false;
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
    void checkMoves()
     {
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

	  if(x==0)
		choice[1]=0;
	else
		choice[1]=1;

	  if(x==2)
		choice[3]=0;
	else
		choice[3]=1;


	 if(y==0)
		choice[0]=0;
	else
		choice[0]=1;


	if(y==2)
		choice[2]=0;
	else
		choice[2]=1;



     }
    
    int calcH()
    {
        int h=0;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if((pz[i][j]==gs[i][j])&&((pz[i][j]!=0)&&(gs[i][j]!=0)))
                    h++;
            }
          
        }
        return h;
    }
    
    void currentState()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(pz[i][j]!=0)
                    System.out.print(pz[i][j]+" ");
                else
                    System.out.print("X"+ " ");
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
    
    void copyTemp()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                tmp[i][j]=pz[i][j];
            }
            
        }
    }
    
    void copyReverse()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                pz[i][j]=tmp[i][j];
            }
            
        }
    }
    
    
}
