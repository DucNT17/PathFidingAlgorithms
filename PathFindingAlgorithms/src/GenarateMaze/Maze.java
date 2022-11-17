package GenarateMaze;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class Maze {
	private final int MAZE_SIZE;
	private int CELL_SIZE;
	private final int w;
	private Cell[][] grids;
	private Cell current;
	private Cell next;
	private Stack<Cell> vistitedStack;
	private Stack<Cell> visitedStack;
	private boolean running = false;
	private int countVisited=0;
	public boolean finish = false;
	private ArrayList<Cell> pathsFromAtoB;
	private Cell start,end;
	private Queue<Cell> visitedQueue;
	private Stack<Cell> visitedS;

	
	Maze(int mazeSize, int cellSize){
		MAZE_SIZE = mazeSize;
		CELL_SIZE = cellSize;
		w = (int)(MAZE_SIZE/CELL_SIZE);
		init();
	}
	private void init() {
		grids = new Cell[w][w];
		for(int row = 0; row < w; row ++) {
			for(int col = 0; col < w; col ++) {
				grids[row][col] = new Cell(row, col, CELL_SIZE);
			}
		}
		current = grids[new Random().nextInt(w)][new Random().nextInt(w)];
		current.visited = true;
		visitedStack = new Stack<Cell>();
		running = true;
	}
	public void resetMaze() {
		for(int row = 0; row < w; row ++) {
			for(int col = 0; col < w; col ++) {
				grids[row][col].resetCell();
			}
		}
		finish = false;
	}
	public void drawMaze(Graphics g) {
		for(int i = 0;i<w;i++) {
			for(int j = 0;j<w;j++) {
				grids[i][j].drawBox(g, new Color(0,0,255,100));
				grids[i][j].drawCell(g);
			}
		}
	}
	public void mazeAlgorithms(Graphics g) {
		if(running) {
			if(countVisited <= w* w) {
				current.drawBox(g, new Color(0,255,0,100));
				update();
				System.out.println(countVisited);
			}
		}
	}
	public void drawMazeInstantly() {
		mazeAlgorithms();
		running = false;
	}
	public void drawPathFinder(Graphics g,int mode) {
		if(mode == 0) breadthFirstSearch(g); // BFS
		else if(mode == 1) depthFirstSearch(g); // DFS
		else if(mode == 2) aStarSearch(g); // A*
		else if(mode == 3) dijkstraSearch(g); // dijkstra search
		for(Cell x: pathsFromAtoB) {
			x.drawPath(g, Color.pink);
		}		
		start.drawBox(g,new Color(252, 118, 106));
		end.drawBox(g,new Color(91, 132, 177));
		for(int i = 0;i<w;i++) {
			for(int j = 0;j<w;j++) {
				grids[i][j].drawCell(g);
			}
		}	
	}
	public boolean checkFinished() {
		if(running) return false;
		return true;
	}
	public void mazeAlgorithms() {
		Stack<Cell> visitedList = new Stack<>();
		current.visited = true;
		visitedList.push(current);
		while(!visitedList.isEmpty()) {
			current = visitedList.pop();
			if(hasNeighbor(current)) {
				visitedList.push(current);
				next = getOneRandomNeighbor(current);
				wallBreaker(current, next);
				visitedList.push(next);
			}
		}
		
	}	
	public boolean hasNeighbor(Cell currentCell) {
		if(getOneRandomNeighbor(currentCell) != null) {
			return true;
		}
		return false;
	}
	public Cell getOneRandomNeighbor(Cell currentCell) { // maze generation
		ArrayList<Cell> neighbors = new ArrayList();
		if(currentCell.row - 1 >= 0 && grids[currentCell.row-1][currentCell.col].visited == false) {
			neighbors.add(grids[currentCell.row-1][currentCell.row]); // top of the cell
		}
		if (currentCell.row + 1 < w && grids[currentCell.row+1][currentCell.col].visited == false) {
			neighbors.add(grids[currentCell.row+1][currentCell.col]); // bottom of the cell
		}
		if (currentCell.col+1 < w && grids[currentCell.row][currentCell.col+1].visited == false) {
			neighbors.add(grids[currentCell.row][currentCell.col+1]); // right of the cell
		}
		if (currentCell.col-1 >= 0 && grids[currentCell.row][currentCell.col-1].visited == false) {
			neighbors.add(grids[currentCell.row][currentCell.col-1]); // left of the cell
		}
		if(neighbors.isEmpty()) {
			return null;
		}
		return neighbors.get(new Random().nextInt(neighbors.size()));
	}
	public void wallBreaker(Cell cellA, Cell cellB) {
		if (cellA.row == cellB.row+1) { // B top A
			cellA.Walls[0] = false;
			cellB.Walls[2] = false;
			return;
		}
		if (cellA.row == cellB.row-1) { // B bottom A
			cellA.Walls[2] = false;
			cellB.Walls[0] = false;
			return;
		}
		if (cellA.col == cellB.col+1) { // B left A
			cellA.Walls[3] = false;
			cellB.Walls[1] = false;
			return;
		}
		if (cellA.col == cellB.col-1) { // B right A
			cellA.Walls[1] = false;
			cellB.Walls[3] = false;
			return;
		}
	}
	public int getCellSize() {
		return CELL_SIZE;
	}

	public void setCellSize(int cellSize) {
		CELL_SIZE = cellSize;
	}
	public void mazeAlgorithm(Graphics g) {
		if(running) {
			if(countVisited <= w*w) current.drawBox(g,new Color(0,255,0,100));
			update();
			System.out.println(countVisited);
		}
	}
	public void update() {
		if(running == false) {
			return;
		}
		next = getOneRandomNeighbor(current);
		if(next != null) {
			visitedStack.push(next);
			countVisited ++ ;
			next.visited = true;
			wallBreaker(current, next);
			
		}else {
			while(!hasNeighbor(current)) {
				if(!visitedStack.empty()) {
					current = visitedStack.pop();
				}else {
					running = false;
					countVisited ++;
					return;
					
				}
			}
			update();
		}
	}
	
	public void breadthFirstSearch(Graphics g) {
		
	}
	public void depthFirstSearch(Graphics g) {
		
	}
	public void aStarSearch(Graphics g) {
		
	}
	public void dijkstraSearch(Graphics g) {
		
	}
public void initStartAndEnd() {
		start = grids[new Random().nextInt(w)][new Random().nextInt(w)];
		end = grids[new Random().nextInt(w)][new Random().nextInt(w)];
		if (start == end){
			end = grids[new Random().nextInt(w)][new Random().nextInt(w)];
		}
		for(int i = 0;i<w;i++) {
			for(int j = 0;j<w;j++) {
				grids[i][j].visited = false;
			}
		}		
		start.visited = true;
		end.visited = true;
		visitedQueue = new LinkedList<Cell>();
		visitedS = new Stack<Cell>();
		pathsFromAtoB = new ArrayList<Cell>();
		current = start;
		next = current;
	}
}
