package GenarateMaze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Panel extends JPanel implements ActionListener,ChangeListener{
	
	private final int mazeSize = 600;
	private int windowW,windowH;
	private Maze maze;
	private JPanel smallPanel;
	private Button startButton;
	private Button resetButton;
	private Button reMazeButton;	
	private JLabel cellLabel;
	private JSlider cellSlider;	
	private JLabel speedLabel;
	private JSlider speedSlider;	
	private JCheckBox mazeCheckbox;
	private JLabel algoBoxLabel;
	private JComboBox algoBox;
	private int mode;
	private Button startSolvingButton;
	private Timer timer;

	public Panel(int windowW,int windowH){
		this.windowW = windowW;
		this.windowH = windowH;		
		this.setPreferredSize(new Dimension(windowW,windowH));
		this.setBounds(0, 0, windowW, windowH);
		this.setBackground(new Color(250,250,250));
		this.setLayout(null);
		
		
		// create table
		smallPanel = new JPanel();
		smallPanel.setPreferredSize(new Dimension((windowW-mazeSize),windowH));
		smallPanel.setBounds(mazeSize, 0, windowW-mazeSize, windowH);
		smallPanel.setLayout(null);
		this.add(smallPanel);
		
		
		// start button
		startButton = new Button("Start",(int)(smallPanel.getSize().width/2)-120-30, 30, 120, 50,Color.PINK);
		smallPanel.add(startButton);
		startButton.addActionListener(this);
		
		
		
		// remaze button
		reMazeButton = new Button("Re-Maze",(int)(smallPanel.getSize().width/2)+30, 30, 120, 50,Color.CYAN);
		smallPanel.add(reMazeButton);
		reMazeButton.addActionListener(this);
		reMazeButton.setEnabled(false);
		
		
		
		
		//Bang lua chon cellSlider + label cua no
		cellSlider = new JSlider(10,100,40);
		cellSlider.setBounds((windowW-mazeSize)/2-(350/2), 150, 350, 50);
		cellSlider.setPaintTicks(true);
		cellSlider.setMinorTickSpacing(10);
		cellSlider.setPaintTrack(true);
		cellSlider.setMajorTickSpacing(10);
		cellSlider.setSnapToTicks(true);
		cellSlider.setPaintLabels(true);
		cellSlider.addChangeListener(this);
		cellLabel = new JLabel();
		cellLabel.setText("CELL'S SIZE: " + cellSlider.getValue());
		cellLabel.setBounds(30, 130, 250, 20);
		cellLabel.setFont(new Font("",Font.BOLD,18));		
		smallPanel.add(cellLabel);
		smallPanel.add(cellSlider);
		
		
		
		// Bang lua chon speed
		speedSlider = new JSlider(1,5, 5);
		speedSlider.setBounds((windowW - mazeSize)/2 - 175, 230, 350, 40); 
		speedSlider.setPaintTrack(true);
		speedSlider.setMajorTickSpacing(1);
		speedSlider.setSnapToTicks(true);
		speedSlider.setPaintLabels(true);
		speedSlider.addChangeListener(this);		
		speedLabel = new JLabel();
		speedLabel.setText("SPEED: " + speedSlider.getValue());
		speedLabel.setBounds(30, 210, 180, 20);
		speedLabel.setFont(new Font("",Font.BOLD,18));		
		smallPanel.add(speedLabel);
		smallPanel.add(speedSlider);
		
		// CheckBox
		mazeCheckbox = new JCheckBox();
		mazeCheckbox.setBounds((int)(smallPanel.getSize().width/2)-120-30, 95, 250, 20);		
		mazeCheckbox.setText("GENERATE INSTANTLY");
		mazeCheckbox.setFont(new Font("", Font.BOLD, 15));
		mazeCheckbox.setFocusable(false);
		smallPanel.add(mazeCheckbox);
		
		//AlgoBox
		algoBoxLabel = new JLabel("Maze Pathfinding");
		algoBoxLabel.setBounds(smallPanel.getSize().width/2-155, 370, 220, 30);
		algoBoxLabel.setFont(new Font("",Font.BOLD,15));
		smallPanel.add(algoBoxLabel);
		
		String[] algoList = {"Breadth First Search(BFS)","Depth First Search(DFS)","Dijkstra","A* Search"};// (0 = BFS , 1 = DFS, 2 = Dijkstra, 3 = A* search)
		algoBox = new JComboBox(algoList);
		algoBox.setBounds(smallPanel.getSize().width/2-160, 400, 220, 30);
		algoBox.setFont(new Font("",Font.BOLD,15));
		algoBox.setFocusable(false);
		algoBox.addActionListener(this);
		mode = algoBox.getSelectedIndex(); // (0 == BFS , 1 = DFS, 2 = Dijkstra, 3 = A* search)
		smallPanel.add(algoBox);
		
		// Start
		startSolvingButton = new Button("START",smallPanel.getSize().width/2-130-20, 300, 130, 60,new Color(255, 231, 122));
		smallPanel.add(startSolvingButton);
		startSolvingButton.addActionListener(this);
		startSolvingButton.setEnabled(false);
		
		//Reset
		resetButton = new Button("RESET",smallPanel.getSize().width/2+20, 300, 130, 60,new Color(44, 95, 45));
		smallPanel.add(resetButton);
		resetButton.addActionListener(this);
		
		initMaze();
		timer.start();
	}
	
	
	private void initMaze() {
		maze = new Maze();
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
