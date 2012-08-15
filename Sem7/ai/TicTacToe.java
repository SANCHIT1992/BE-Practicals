package nitish.ai;
import java.io.*;
import java.util.ArrayList;
/**
 * Name:Nitish Parkar
 * Roll No: 09-187
 * Batch 4
 */
public class TicTacToe
{
    static ArrayList[] log=new ArrayList[2];
    static int turn=10;
    static String namex="Nitish",nameo="Parkar";
    static int[][] tttarray=new int[3][3];  //0-0 x-10
    static int[][] heuristic=new int[3][3];

    public static void main(String args[]) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int rnd=new java.util.Random().nextInt(2);
       // System.out.println("r"+rnd);
        System.out.println("Player1, enter name:");

        if(rnd==0)
        {
            nameo=br.readLine();
            System.out.println("Player2, enter name:");
            namex=br.readLine();
        }
        else
        {
            namex=br.readLine();
            System.out.println("Player2, enter name:");
            nameo=br.readLine();
        }

        log[0]=new ArrayList<String>();
        log[1]=new ArrayList<Integer>();
        for(int i=0,z=1;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                tttarray[i][j]=z++;
            }
        }

        do
        {
            if(turn==10)
            {
                System.out.print("MAX");
            }
            else
            {
                System.out.print("MIN");
            }
            System.out.println("-------------------------");

            for(int i=0,z=1;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    heuristic[i][j]=0;
                }
            }

            calculateHeuristic();
            display();

            if(turn==0)
            {
                System.out.println(nameo+"(O) enter position[0=exit]:");
            }
            else
            {
                System.out.println(namex+"(X) enter position[0=exit]:");
            }

            int ch=Integer.parseInt(br.readLine());
            if(ch==0)
            {
                break;
            }
            if(ch<1||ch>9)
            {
                System.out.println("Wrong Position!");
                continue;
            }
            ch--;
            if((tttarray[ch/3][ch%3]!=0)&&(tttarray[ch/3][ch%3]!=10))
            {
                if(turn==0)
                {
                    tttarray[ch/3][ch%3]=0;
                    log[0].add(nameo);
                    log[1].add(++ch);
                    turn=10;
                }
                else
                {
                    tttarray[ch/3][ch%3]=10;
                    log[0].add(namex);
                    log[1].add(++ch);
                    turn=0;
                }
                
                if(isFinished()!=-1)
                {
                    String winner=(turn==0)?namex:nameo;
                    System.out.println(winner+" won the game!");
                    break;
                }

                if(isDraw())
                {

                    System.out.println("Draw!");
                    break;
                }
            }
            else
            {
                System.out.println("Tile is already occupied");
            }
        }while(true);
        printLog();
    }

    static void calculateHeuristic()
    {

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(tttarray[i][j]!=turn)
                {
                    int tmp=tttarray[i][j];
                    tttarray[i][j]=turn;
                    heuristic[i][j]=xH()-yH();
                    tttarray[i][j]=tmp;
                }
            }
        }

    }

    static boolean isDraw()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if((tttarray[i][j]>0)&&(tttarray[i][j]<10))
                {
                    return false;
                }
            }
        }
        return true;
    }

    static int isFinished()
    {
        int anti_turn=(turn==0)?10:0;
        if((tttarray[0][0]==anti_turn)&&(tttarray[1][1]==anti_turn)&&(tttarray[2][2]==anti_turn))
        {
             return anti_turn;
        }
        if((tttarray[0][2]==anti_turn)&&(tttarray[1][1]==anti_turn)&&(tttarray[2][0]==anti_turn))
        {
             return anti_turn;
        }

        for(int i=0;i<3;i++)
        {
                    if((tttarray[i][0]==anti_turn)&&(tttarray[i][1]==anti_turn)&&(tttarray[i][2]==anti_turn))
                    {
                        return anti_turn;
                    }
                    if((tttarray[0][i]==anti_turn)&&(tttarray[1][i]==anti_turn)&&(tttarray[2][i]==anti_turn))
                    {
                        return anti_turn;
                    }
        }

        return -1;
    }

    static int yH()
    {
        int h=0;
        if((tttarray[0][0]!=10)&&(tttarray[1][1]!=10)&&(tttarray[2][2]!=10))
        {
             h++;
        }
        if((tttarray[0][2]!=10)&&(tttarray[1][1]!=10)&&(tttarray[2][0]!=10))
        {
             h++;
        }

        for(int i=0;i<3;i++)
        {
                    if((tttarray[i][0]!=10)&&(tttarray[i][1]!=10)&&(tttarray[i][2]!=10))
                    {
                        h++;
                    }
                    if((tttarray[0][i]!=10)&&(tttarray[1][i]!=10)&&(tttarray[2][i]!=10))
                    {
                        h++;
                    }
        }

        return h;

    }

    static int xH()
    {
        int h=0;
        if((tttarray[0][0]!=0)&&(tttarray[1][1]!=0)&&(tttarray[2][2]!=0))
        {
             h++;
        }
        if((tttarray[0][2]!=0)&&(tttarray[1][1]!=0)&&(tttarray[2][0]!=0))
        {
             h++;
        }

        for(int i=0;i<3;i++)
        {
                    if((tttarray[i][0]!=0)&&(tttarray[i][1]!=0)&&(tttarray[i][2]!=0))
                    {
                        h++;
                    }
                    if((tttarray[0][i]!=0)&&(tttarray[1][i]!=0)&&(tttarray[2][i]!=0))
                    {
                        h++;
                    }
        }

        return h;

    }

    static void display()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(tttarray[i][j]==10)
                {
                    System.out.print("X   \t");
                    continue;
                }
                if(tttarray[i][j]==0)
                {
                    System.out.print("O   \t");
                    continue;
                }
                System.out.print(tttarray[i][j]+"");
                System.out.print("("+heuristic[i][j]+")\t");
            }
            System.out.println("");
        }
    }

    static void printLog()
    {
        System.out.println("\nLOG:");
        for(int i=0;i<log[0].size();i++)
        {
            System.out.println(log[0].get(i)+" played at "+log[1].get(i));
        }
    }
}


