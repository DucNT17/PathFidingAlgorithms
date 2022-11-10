package Main;

import javax.swing.JFrame;

import GenarateMaze.Panel;


public class GameFrame extends JFrame{
	public Panel panel;
	public final int WINDOW_WIDTH_SIZE = 1000;
	public final int WINDOW_HEIGHT_SIZE = 600;
	GameFrame(){
		this.setTitle("Maze Generator and Pathfinding Algorithms");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WINDOW_WIDTH_SIZE, WINDOW_HEIGHT_SIZE);
		this.setResizable(false);
		panel = new Panel(WINDOW_WIDTH_SIZE,WINDOW_HEIGHT_SIZE);
		this.add(panel);
		this.pack();
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
