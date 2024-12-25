import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

import java.awt.datatransfer.*;

public class Cpw{
	static int count=0;
	public static void main(String[] args){
		new Cpw();
	}
	public Cpw(){
		
		JFrame frame = new JFrame();
		frame.setSize(300, 300);
		
		ArrayList<String> Content = DataGetter.getData();
		int contentSize = Content.size();
		//JButton[] button = new JButton[contentSize];

		JLabel[] label = new JLabel[contentSize];

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0,1));
		for(int i=0;i<contentSize;i++){
			label[i] = new JLabel(Content.get(i));
			//button[i] = new JButton("Copy");
			//button[i].setPreferredSize(new Dimension(20,20));
			panel.add(label[i]);
			//panel.add(button[i]);
			CpActionListener al = new CpActionListener(label[i]);
			//button[i].addActionListener(al);
		}
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Copy Window");
		frame.pack();
		frame.setVisible(true);
		
	}

}
class DataGetter{
	public static ArrayList<String> getData(){
		ArrayList<String> arr = new ArrayList<String>();
		try{
			File file = new File("content.txt");
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()){
				arr.add(sc.nextLine());
			}
			sc.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File Not Found");
		}
		
		return arr;
	}
}
class CpActionListener implements ActionListener{

	JLabel label;
	public CpActionListener(JLabel l){
		label = l;
		label.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){			
				copy();
			}
		});
		
	}
	public void actionPerformed(ActionEvent e){
		copy();
	}
	private void copy(){
		StringSelection ss = new StringSelection(label.getText());
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(ss, null);
	}
	
}