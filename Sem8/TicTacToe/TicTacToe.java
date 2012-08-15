import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/*
* Title: Tic Tac Toe
* Author: Nitish
*/

class TicTacToe implements ActionListener {
	
	JFrame frame;
	JButton[][] jb;
	JLabel[][] jls;
	JLabel jl;
	Container pane;
	int[][] matrix=new int[3][3];
	int turn=1;
	int x=0,y=0;
	ArrayList<Integer> xlog=new ArrayList<Integer>();
	ArrayList<Integer> ylog=new ArrayList<Integer>();
	
	
	public static void main(String args[]) {
		new TicTacToe();
	}
	
	TicTacToe() {

		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				matrix[i][j]=2;
			}
		}
		
		frame = new JFrame("Tic Tac Toe");
		pane = frame.getContentPane();
		pane.setLayout(new GridLayout(4,3)); 
		
		jl=new JLabel("X's turn");
		jb=new JButton[3][3];
		//jls=new JLabel[3][3];
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				jb[i][j]=new JButton("-");
				jb[i][j].addActionListener(this);
				pane.add(jb[i][j]);
			}
		}
		pane.add(jl);
		
		
		
		frame.setSize(400,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent a) {
		JButton jbt=(JButton)a.getSource();
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(jbt==jb[i][j]) {
					x=i;
					y=j;
					break;
				}
			}
		}
		
		if(turn==0) {
			jb[x][y].setText("O");
		}
		else if(turn==1){
			jb[x][y].setText("X");
		}
		
		xlog.add(x);
		ylog.add(y);
		jb[x][y].setEnabled(false);
		
		
		matrix[x][y]=turn;
		int retstat=checkStatus();
		if(retstat==1) {
			JOptionPane.showMessageDialog(pane, "X Won","Congratulations!", JOptionPane.PLAIN_MESSAGE);
			myDispose();
			//pane.repaint();
			//frame.dispose();
			
		}
		else if(retstat==0) {
			JOptionPane.showMessageDialog(pane, "0 Won","Congratulations!", JOptionPane.PLAIN_MESSAGE);
			myDispose();
			//frame.dispose();
		}
		else if(retstat==3) {
			JOptionPane.showMessageDialog(pane, "Draw","No Result!", JOptionPane.PLAIN_MESSAGE);
			myDispose();
			//frame.dispose();
		}

		flipTurn();
		refresh();

	}
	
	void flipTurn() {
		if(turn==0) {
			turn=1;
		}
		else if(turn==1){
			turn=0;
		}
		
	}

	void refresh() {
		
		String turnLabel="";
		if(turn==0)
			turnLabel="0";
		if(turn==1)
			turnLabel="X";
		jl.setText(turnLabel+"'s Turn");
		
	}
	
	int checkStatus() {
		int winner=2;
		for(int i=0;i<3;i++) {
			if(matrix[i][0]==1 && matrix[i][1]==1 && matrix[i][2]==1 ) {
				return 1;
			}
			if(matrix[0][i]==1 && matrix[1][i]==1 && matrix[2][i]==1 ) {
				return 1;
			}
			if(matrix[i][0]==0 && matrix[i][1]==0 && matrix[i][2]==0 ) {
				return 0;
			}
			if(matrix[0][i]==0 && matrix[1][i]==0 && matrix[2][i]==0 ) {
				return 0;
			}
		}
		
		if(matrix[0][0]==1 && matrix[1][1]==1 && matrix[2][2]==1 ) {
				return 1;
				
			}
		if(matrix[0][0]==0 && matrix[1][1]==0 && matrix[2][2]==0 ) {
				return 0;
				
			}
		if(matrix[0][2]==1 && matrix[1][1]==1 && matrix[2][0]==1 ) {
				return 1;
				
			}
		if(matrix[0][2]==0 && matrix[1][1]==0 && matrix[2][0]==0 ) {
				return 0;
				
			}
			
		boolean flag=false;	
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(matrix[i][j]==2) {
					flag=true;
					break;
				}
			}
		}	
		if(!flag) {
			winner=3;
		}
			
		return winner;

	}
	
	void myDispose() {
			DisplayLog dlog=new DisplayLog(xlog,ylog);
			Thread t=new Thread(dlog);
			pane.removeAll();
			pane.setLayout(new BorderLayout()); 
			pane.add(dlog,BorderLayout.CENTER);
			frame.setSize(320,330);
			t.start();
	}

} 



class DisplayLog extends Canvas implements Runnable{

	ArrayList<Integer> xlog=new ArrayList<Integer>();
	ArrayList<Integer> ylog=new ArrayList<Integer>();
	int[][] matrix=new int[3][3];
	int width=50,height=50;
	int turn=1;
	DisplayLog(ArrayList<Integer> x,ArrayList<Integer> y){
		
		
		xlog=x;
		ylog=y;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				matrix[i][j]=2;
			}
		}
		//this.start();
		//move();
	}
	
	public void run() {
		for(int i=0;i<xlog.size();i++) {
			matrix[xlog.get(i)][ylog.get(i)]=turn;
			flipTurn();
			
			try{
			Thread.sleep(1000); }
			catch(InterruptedException e){
			}
			repaint();
			//System.out.println("X"+xlog.get(i)+"Y"+ylog.get(i));
		}
	}
	

	public void paint(Graphics g)
        {
		g.drawLine(0, 100, 300, 100);
		g.drawLine(0, 200, 300, 200);
		g.drawLine(100, 0, 100, 300);
		g.drawLine(200, 0, 200, 300);
                g.setFont(new Font("Arial",Font.BOLD,25));
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(matrix[i][j]==1) {
					g.drawString("X", (j*100)+30,(i*100)+50);
				}
				if(matrix[i][j]==0) {
					g.drawString("0", (j*100)+30,(i*100)+50);
				}
			}
		}
         
        }

	void flipTurn() {
		if(turn==0) {
			turn=1;
		}
		else if(turn==1){
			turn=0;
		}
	}
	


}