package nitish.ai;

/**
 * Nitish Parkar
 * 09-187
 * BEIT- Batch 4
 */

import java.io.*;
public class BestFirst
{
    static Puzzle8 available_states[]=new Puzzle8[30];
    static int count=0;
    static int lvl=0;
    static char lbl='A';
    final static int gs[][]={{1,2,3},{8,0,4},{7,6,5}};

    public static void main(String[] args)throws IOException
    {
        boolean loop=true;
        int is[][]={{2,8,3},{1,6,4},{7,0,5}};
        Puzzle8 cs=new Puzzle8(is);
        available_states[0]=cs;
        int ch=0;
        
        do
        {
         if(ch<=count)
         {
             lvl++;
             cs.findPossibleStates();
             System.out.println("\n----------------------------------");
             System.out.println("Current State:");
             cs.display();
             System.out.println("Available States:");
             for(int i=1;i<count;i++)
             {
                 for(int j=count-1;j>i;j--)
                 {    
                    
                    if(available_states[j].heuristic>available_states[j-1].heuristic)
                    {
                        Puzzle8 tmp=available_states[j];
                        available_states[j]=available_states[j-1];
                        available_states[j-1]=tmp;
                    }
                 }
             }
             for(int i=1;i<count;i++)
             {
                 System.out.println("Choice:"+i+"\tLabel:"+available_states[i].label +"\tLevel:"+available_states[i].level +"\tHeuristic Value:"+available_states[i].heuristic);
                 available_states[i].display();
             }
         }
         
         System.out.print("\nEnter Choice No.(Enter 0 for Exit):");
         BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         ch=Integer.parseInt(br.readLine());
         if(ch==0)
         {
             System.out.println("BYE!!");
             break;
         }
         if(ch<count&&ch>0)
         {
            cs.switchTo(ch);
            if(cs.hasReachedGoal())
            {
                 System.out.println("*****Goal Reached!*****");
                 loop=false;
            }
         }
         else
         {
                 System.out.println("Wrong Choice!("+ch+")");
         }
          
      }while(loop);

    }
} 

class Puzzle8 extends BestFirst
{
    int pz[][]=new int[3][3];
    int heuristic=0,level=0;
    char label;
    
    Puzzle8(int[][] pzi)
    {
       if(!ifExists(pzi))
       {
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    pz[i][j]=pzi[i][j];
                    
                        
                }
            }
            count++;
            level=lvl;
            label=lbl++;
            heuristic=calculateHeuristicValue();
       }
    }

    boolean ifExists(int[][] pzl)
    {
        if(count<2)
            return false;
            
         for(int i=1;i<count;i++)
         {
             boolean ex=true;
             for(int k=0;k<3;k++)
             {
                for(int j=0;j<3;j++)
                {
                   if(available_states[i].pz[k][j]!=pzl[k][j])
                   {
                       ex=false;
                   }
                }
            }
            if(ex)
                    return true;
         }

        return false;
    }
  
    int calculateHeuristicValue()
    {
        int h=0;
        int tmp[]=new int[2];
        for(int i=0;i<3;i++)
        {
                for(int j=0;j<3;j++)
                {
                    if(pz[i][j]!=0)
                    {
                        tmp=getPositionInGoalState(pz[i][j]);
                        h+=Math.abs(i-tmp[0])+Math.abs(j-tmp[1]);
                    }
                }
                
        }
        return h;
    }
    
    int[] getPositionInGoalState(int element)
    {
        int[] xy=new int[2];

        for(int i=0;i<3;i++)
        {
                for(int j=0;j<3;j++)
                {
                    if(gs[i][j]==element)
                    {
                        xy[0]=i;
                        xy[1]=j;
                    }
                }
        }
        return xy;
    }
    
    
    void display()
    {
         for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    System.out.print(pz[i][j]+" ");
                    
                }
                System.out.println("");
            }
    }
    
    void switchTo(int a)
    {

           for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    pz[i][j]=available_states[a].pz[i][j];
                    
                }
            }
           for(int i=a;i<count;i++)
           {
               available_states[i]=available_states[i+1];
           }
           count--;
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
    
    void findPossibleStates()
    {
         int[][] tmp=new int[3][3];
         int x=0,y=0;
         
         for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    
                    if(pz[i][j]==0)
                    {
                        
                        x=i;
                        y=j;
                        break;
                    }
                }
                
            }
         
         if(x!=0)
          {
            
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    tmp[i][j]=pz[i][j];
                }

            }
            int temp=tmp[x][y];
            tmp[x][y]=tmp[x-1][y];
            tmp[x-1][y]=temp;
           
            available_states[count]=new Puzzle8(tmp);
          }

         
            if(x!=2)
          {
            
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    tmp[i][j]=pz[i][j];
                }

            }
            int temp=tmp[x][y];
            tmp[x][y]=tmp[x+1][y];
            tmp[x+1][y]=temp;
 
            available_states[count]=new Puzzle8(tmp);
          }
            
          if(y!=2)
          {
            
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    tmp[i][j]=pz[i][j];
                }

            }
            int temp=tmp[x][y];
            tmp[x][y]=tmp[x][y+1];
            tmp[x][y+1]=temp;
  
            available_states[count]=new Puzzle8(tmp);
          }
          
          if(y!=0)
          {
            
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    tmp[i][j]=pz[i][j];
                }

            }
            int temp=tmp[x][y];
            tmp[x][y]=tmp[x][y-1];
            tmp[x][y-1]=temp;
        
            available_states[count]=new Puzzle8(tmp);
          }

       }
}

    




