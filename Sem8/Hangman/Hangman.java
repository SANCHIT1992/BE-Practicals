import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.util.*;

/*
* Title: Hangman in Java
* Author: Nitish
* Date Created: 06/03/2012
* Last Modified: 22/03/2012
* All files => https://sites.google.com/site/nitishspblog/test/Hangman.zip
*/

class Hangman extends JFrame{
	
	Hangman() {	
		super("Hangman");
		HangmanDraw hdObj=new HangmanDraw();		
		hdObj.init();
		add(hdObj);
		setSize(800,500);
		setVisible(true);
		hdObj.requestFocus();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public static void main(String args[]) {
	  
	   new Hangman();
	}
}


class HangmanDraw extends Canvas implements KeyListener {
	ArrayList<String> fileContent=null;
	ArrayList<Character> selectedCharacters=null;
	int missedChances=0;
	char[] word;
	char[] tmp;
	String fruit;
	String[] himages;
	
	void init() {
		
		addKeyListener(this);
		fruit=new String();
		himages=new String[7];
		fileContent=new ArrayList<String>();
		selectedCharacters=new ArrayList<Character>();
		
		try {
			BufferedReader br=new BufferedReader(new FileReader("hmandata.txt"));
			//Import all the fruit names in an ArrayList 
			while((fruit=br.readLine())!=null) {
				fileContent.add(fruit);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//Randomly select 1 fruit name from the ArrayList
		//lowercase conversion is done to simplify the comparison in later steps
		fruit=fileContent.get(new Random().nextInt(fileContent.size())).toLowerCase();
		
		/* The array 'tmp' contains fruit name to guess.
		*  The array 'word' is used to hold part of the fruit name correctly guessed by user. 
		*  Underscore represents the character that has not been guessed.
		*  
		*  Example: mango
		*  Initialization:
		*  word => _ _ _ _ _ 
		*  tmp =>  m a n g o
		*/
		word=new char[fruit.length()*2];
		tmp=new char[fruit.length()*2];
		for(int arri=0,i=0;arri<word.length;arri++) {
			tmp[arri]=fruit.charAt(i++);
			word[arri++]='_';
			word[arri]=' ';
			tmp[arri]=' ';
		}
		
		/*
		int tt=word.length/3;
		while(tt-->0) {
			int rr=new Random().nextInt(word.length);
			word[rr]=tmp[rr];
		}*/
		
		//generate the names of hangman images (from 1.jpg to 7.jpg)
		for(int i=0;i<7;i++){
			himages[i]=(i+1)+".jpg";
		}
		
		//End of initialization, call paint() method and wait for user input
		repaint();
	}
	
	public void paint(Graphics g) {
		setBackground(Color.WHITE);
		g.setFont(new Font("Arial",Font.BOLD,50));
		if(checkWin()){ //player guessed the word correctly 
			removeKeyListener(this);
			g.drawString("Congratulations!",50,200);
			
		}
		else if(missedChances<6) { // game is still on
			g.drawString((6-missedChances)+" chance(s) remaining!",20,60);
			g.drawString(new String(word),20,200);
			String uw="";
			for(int l=0;l<selectedCharacters.size();l++) {
				uw=uw+selectedCharacters.get(l)+" ";				
			}
			g.drawString("Used Characters:",20,400);
			g.drawString(uw,20,440);
		}
		else { //player made 6 false guesses 
			removeKeyListener(this);
			g.drawString("You couldn't guess the word!",20,40);
			g.drawString("The word was:",20,150);
			g.drawString(new String(tmp),20,200);
		}
		
		//Load appropriate image based on missedChances
		Image img=null;
		try{
			img=ImageIO.read(new File("himages/"+himages[missedChances]));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img,450,70,this);
	}
	
	public void keyPressed(KeyEvent e)  {
	}
	
	public void keyReleased(KeyEvent e )  {
	}
		
	public void keyTyped(KeyEvent e )  {
		//Fired each time user presses & releases one of the non-virtual keys.
		char c=e.getKeyChar();
		
		//process key press only if user hasn't pressed the key before
		if(!selectedCharacters.contains(c)) {
			//add the key to the list of entered characters
			selectedCharacters.add(c);
			
			//if user guesses a valid character, update the 'word' array
			//else increment missedChances
			boolean flag=false;
			for(int i=0;i<word.length;i++) {
				if(c==tmp[i]) {
					word[i]=tmp[i];
					flag=true;
				}
			}
			if(!flag) {
				missedChances++;
			}
			repaint();
			//call the paint() method to reflect the changes
		}
	}
	
	boolean checkWin() {
		for(int i=0;i<word.length;i++) {
				if(word[i]!=tmp[i]) {
					return false;
				}
		}
		return true;
	}

}