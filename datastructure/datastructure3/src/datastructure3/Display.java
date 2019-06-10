package datastructure3;
import java.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Display extends JFrame implements ActionListener
{
	int costMatrix[][] = new int[][] {
		{ 0, 80, 20, 50, 0, 0, 0, 0 },
		{ 80, 0, 0, 20, 0, 130, 0, 0 },
		{ 20, 0, 0, 20, 50, 0, 0, 0 },
		{ 50, 20, 20, 0, 10, 60, 30, 0 },
		{ 0, 0, 50, 10, 0, 0, 10, 0 },
		{ 0, 130, 0, 60, 0, 0, 20, 30 },
		{ 0, 0, 0, 30, 10, 20, 0, 60 },
		{ 0, 0, 0, 0, 0, 30, 60, 0 } };
		
	int distanceMatrix[][] = new int[][] {
		{ 0, 500, 100, 70, 0, 0, 0, 0 },
		{ 80, 0, 0, 100, 0, 40, 0, 0 },
		{ 230, 0, 0, 20, 50, 0, 0, 0 },
		{ 28, 36, 20, 0, 10, 60, 70, 0 },
		{ 0, 0, 550, 120, 0, 0, 7, 0 },
		{ 0, 500, 0, 25, 0, 0, 87, 30 },
		{ 0, 0, 0, 310, 10, 20, 0, 60 },
		{ 0, 0, 0, 0, 0, 150, 86, 0 } };

	String[] choice = {"Cost", "Distance"};
	String[] start = { "Vancouver", "Calgary", "Edmonton", "Ottawa", "Winnipeg", "Toronto", "Montreal", "Quebec"};
	String[] end = { "Vancouver", "Calgary", "Edmonton", "Ottawa", "Winnipeg", "Toronto", "Montreal", "Quebec"};
	static int indexStart;
	static int indexEnd;
	static int indexChoice;
	Destination destination = new Destination();
	Container content = getContentPane();
    
	public static void main(String[] args)
	{
		System.out.println("The minimum flight cost TEXT VIEWER");
		System.out.println("& the shortest flight distance");
		System.out.println("====================================");
		Display frame = new Display();
		frame.setVisible(true);
	}
	
	public Display()
	{
		setTitle("Visualizer");
		setBounds(50,50,500,400);	//x,y,width,height (x, y 는 창 뜰 ㄸㅐ 모니터에서 보이는 위치)
		

		content.setLayout(new FlowLayout());
			
		JComboBox choicecombo = new JComboBox(choice);
		content.add(choicecombo);
		
		JLabel startText = new JLabel("Start");
		JComboBox startcombo = new JComboBox(start);
	    content.add(startText);
		content.add(startcombo);

	    JLabel endText = new JLabel("End");
	    JComboBox endcombo = new JComboBox(end);
	    content.add(endText);
	    content.add(endcombo);
	    
	    JButton compute = new JButton("Compute");
	    content.add(compute);
	    compute.addActionListener(this);
	    
	    ImageIcon image = new ImageIcon("Canada.png");
	    Image beforeImage = image.getImage();	//imageIcon 을 image 로 변환
	    Image afterImage = beforeImage.getScaledInstance(480, 200, java.awt.Image.SCALE_SMOOTH);
	    ImageIcon newImage = new ImageIcon(afterImage);
	    JLabel labelImage = new JLabel(newImage);
	
	    content.add(labelImage);
	    
	    choicecombo.addActionListener(new ActionListener()
	    {
	 
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		JComboBox cb0 = (JComboBox)e.getSource();
	    		indexChoice = (int)cb0.getSelectedIndex();
	    		//System.out.println(indexEnd);
	    	}
	    });
	    
	    startcombo.addActionListener(new ActionListener()
	    {
	 
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		JComboBox cb1 = (JComboBox)e.getSource();
	    		indexStart = cb1.getSelectedIndex();
	    		//System.out.println(indexStart);
	    	}
	    });
	    
	    endcombo.addActionListener(new ActionListener()
	    {
	 
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		JComboBox cb2 = (JComboBox)e.getSource();
	    		indexEnd = (int)cb2.getSelectedIndex();
	    		//System.out.println(indexEnd);
	    	}
	    });	 
	}
    public void actionPerformed(ActionEvent e)
    {
    	if(e.getActionCommand().equals("Compute"))
    	{
    		if(indexChoice == 0)	//cost
    		{
    			destination.read(indexStart, indexEnd);

    	    	Dijkstra dijk = new Dijkstra();
    	    	dijk.dijkstra(costMatrix, destination.getStart(), destination.getEnd());
    	    
    	    	JLabel pathText = new JLabel(dijk.path);
    	    	content.add(pathText);
    	    
    	    	JLabel costText = new JLabel(dijk.cost);
    	    	content.add(costText);

    	    	JLabel totalText = new JLabel(dijk.total);
    	    	content.add(totalText);
    	    	
    	    
    			
    		}
    		else	//distance
    		{
    			destination.read(indexStart, indexEnd);

    	    	Dijkstra2 dijk2 = new Dijkstra2();
    	    	dijk2.dijkstra(distanceMatrix, destination.getStart(), destination.getEnd());
    	    
    	    	JLabel pathText = new JLabel(dijk2.path);
    	    	content.add(pathText);
    	    
    	    	JLabel costText = new JLabel(dijk2.cost);
    	    	content.add(costText);
    	    	
    	    	JLabel totalText = new JLabel(dijk2.total2);
    	    	content.add(totalText);
    		}
    	}
    	
    }
}