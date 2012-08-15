import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/*
* Title: Cows and Bulls in Java
* Author: Nitish
* Date Created: 20/03/2012
* Last Modified: 22/03/2012
* All Files => https://sites.google.com/site/nitishspblog/test/CowsAndBulls.zip
*/

class CowsBullsScratch extends JFrame implements ActionListener{
JButton submit;
JTextField input;
JLabel word,chanceslbl;
ArrayList<String> words;
ArrayList<String> entered_words;
BufferedReader br;
String selected_word=null;
JScrollPane jsp;
JTable table;
DefaultTableModel model;
int chances=10;

	CowsBullsScratch(){
		super("Cows and Bulls");
		
		//Initialize GUI components
		setLayout(null);
	
		submit=new JButton("Submit");
		submit.addActionListener(this);
		submit.setBounds(170,20,100,30);
		add(submit);
		
		input=new JTextField(10);
		input.setBounds(20,20,150,30);
		add(input);

		//Fetch words from file and randomly select one
		words=new ArrayList<String>();
		try{
		br=new BufferedReader(new FileReader(new File("cbdata.txt")));
			while((selected_word=br.readLine())!=null) {
				words.add(selected_word);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		selected_word=words.get(new Random().nextInt(words.size()));
		
		word=new JLabel("Word length :"+Integer.toString(selected_word.length()));
		word.setBounds(300,20,100,30);
		add(word);
		
		chanceslbl=new JLabel("Chances remaining: "+Integer.toString(chances));
		chanceslbl.setBounds(400,20,200,30);
		add(chanceslbl);

		String[] columns = {"Word","Bulls","Cows"};       
		Object[][] data = {};
		
		//Override isCellEditable to make Jtable non editable
		model=new DefaultTableModel(data,columns){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table=new JTable(model);
		jsp=new JScrollPane(table);
		jsp.setBounds(20,70,500,200);
		add(jsp);		
		
		setSize(550,350);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		entered_words=new ArrayList<String>();
		//JOptionPane.showMessageDialog(this, "The word is "+selected_word);
	}
	
	
	public static void main(String args[]){
		new CowsBullsScratch();
	}
	
	public void actionPerformed(ActionEvent e){
		String st=input.getText();
		st=st.trim();
		if(st.length()!=selected_word.length()){
			JOptionPane.showMessageDialog(this, "Invalid length");
			return;
		}
		
		if(entered_words.contains(st)){
			JOptionPane.showMessageDialog(this, "You have already entered that word");
			return;
		}
		
		if(st.equals(selected_word)){
			JOptionPane.showMessageDialog(this, "Congratulations! You guessed the word correctly.");
			return;
		}
			
		chances--;
		
		int cows=0;
		int bulls=0;
		
		//calculate bulls
		
		for(int i=0;i<selected_word.length();i++){
			if(selected_word.charAt(i)==st.charAt(i)){
				bulls++;
			}
		}
		

		//remove duplicate characters from input and store the result in tmp 
		ArrayList<Character> tmp=new ArrayList<Character>();
		for(int i=0;i<st.length();i++){
			if(!tmp.contains(st.charAt(i))){
				tmp.add(st.charAt(i));
			}
		}
		
		//calculate cows
		for(int i=0;i<tmp.size();i++){
			char c=tmp.get(i);
			int last_index=0;
			
			//iterate till the string doesn't contain character c
			//with each iteration narrow down the search space
			//This process can be made simpler by comparing characters individually
			//I guess I was experimenting with something which made this process more complex
			while(true){
				int index=selected_word.indexOf(c,last_index);

				if(index==-1){
					break;
				}
				else{
					cows++;
				}
				last_index=index+1;
			}
		}
		
		entered_words.add(st);
		
		//update GUI
		input.setText("");
		chanceslbl.setText("Chances remaining:"+Integer.toString(chances));
		model.insertRow(model.getRowCount(),new Object[]{st,  new Integer(bulls), new Integer(cows-bulls)});
		
		if(chances<=0){
			JOptionPane.showMessageDialog(this, "You Lost! The word was "+selected_word);
		}
	
	}
}