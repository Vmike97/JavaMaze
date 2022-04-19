package Dungeon.MazeGeneration;
import java.util.*;

import Dungeon.Cell.Cell;

public class MazeGenerator {
	int length;
	int height;
	int obstacles;
	int[] start = new int[2];
	int[] end = new int[2];
	List<List<Integer>> maze = new ArrayList<>();
	//Custom matrix visuals (cannot use 0).
	int pathValue = 1;
	int obstacleVal = 9;
	int startValue = 5;
	int desValue = 3;
	
	//Create maze
	public MazeGenerator(int length, int height, int obstacals, int[] start, int[] end){
		this.length = length;
		this.height = height;
		this.obstacles = obstacals;
		if(start[0] >= length || start[1] >= height || end[0] >= length || end[1] >= height ){
			System.out.println("The start and or end points are out of bounds in maze dimenstions!");
			System.exit(0);
		}else{
			this.start = start;
			this.end = end;
			create();
		}
	}
	
	//Display Maze
	public void display(){
		for(int i = 0; i < maze.size(); i++){
			System.out.println(maze.get(i));
		}
	}
	//Create empty Maze
	private List<List<Integer>> create(){
		
		for(int i = 0; i < height; i++){
			List<Integer> lengthAdd = new ArrayList<>();
			for(int j = 0; j < length; j++){
				lengthAdd.add(pathValue);
			}
			maze.add(lengthAdd);
		}
		//Fill array with random obstacles
		fillRandomObstacles(maze, obstacles);
		//Add start and end positions
		maze.get(start[1]).set(start[0], startValue);
		maze.get(end[1]).set(end[0], desValue);
		return maze;
	}
	//Create random obstacles.
	private void fillRandomObstacles(List<List<Integer>> maze, int amount){
		int x = this.length;
		int y = this.height;
		int randx = 0;
		int randy = 0;
		//Make sure amount of obstacles is less than maze size -2 (for start and end)
		int obs = amount;
		if(obs >= x*y){
			obs = x*y - 2;
		}
		int[][] placement = new int[x][y];
		//Determine random placement of amount of obstacles
		//Determine random x, amount times and random y, amount times match x,y and place obstacle.
		for(int total = 0; total < obs; total++){
			randx = (int)(Math.random() * x);
			randy = (int)(Math.random() * y);
			//check if there already is obstacle, if so need to generate another random x,y. Make sure
			//start point(0,0) & end point(x-1,y-1) is available.
			if(placement[randx][randy] == obstacleVal){
				total--;
			}else{
				placement[randx][randy] = obstacleVal;
				maze.get(randy).set(randx, obstacleVal);
			}	
		}
	}
	//Solve the maze to get from start to end point
	public String solveMinDistance(){
			int[] start = this.start;
			int[] end = this.end;
			int length = this.length;
			int height = this.height;
			
			
			Cell[][] cells = new Cell[height][length];
			for(int y = 0; y < height; y++){
				for(int x = 0; x < length; x++){
					//If maze contains obstacleValue i.e #8 keep cells[y][x] null
					if(maze.get(y).get(x) != obstacleVal){
						cells[y][x] = new Cell(x, y, Integer.MAX_VALUE, null, "");
					}
				}
			}
			
			Cell src = cells[start[1]][start[0]];
			src.setDistance(0);
			
			Queue<Cell> queue = new LinkedList<>();
			queue.add(src);
			
			Cell pointer = null;
			while(!queue.isEmpty()){
				pointer = queue.poll();
				if(pointer.getX() == end[0] && pointer.getY() == end[1]){
					return "It will take " + pointer.getDistance() + " moves to reach destination. The path is as follows:\n"
				+ pointer.getDirection();
				}
				exploreNeighbour(cells, queue, pointer.getX(), pointer.getY(), pointer);
			}
		return "Cannot reach destination!";
	}
	private static void exploreNeighbour(Cell[][] cells, Queue<Cell> queue, int x, int y, Cell parent){
		//vectorChanges. Starting from priority up, right, down left.
		int[][] vectorChanges = new int[][]{{0,-1},{1,0},{0,1},{-1,0}};
		//Corresponding string representations matching vectorChanges array.
		String[] dir = new String[]{"^ ", "> ", "v ", "< "};
		for(int i = 0; i < vectorChanges.length; i++){
			int newx = x + vectorChanges[i][0];
			int newy = y + vectorChanges[i][1];
			//Check for out of bounds and illegal locations
			if(newx < 0 || newx >= cells[0].length || newy < 0 || newy >= cells.length || cells[newy][newx] == null){
				continue;
			}
			//Check greater distance, combine cells distance, parent, direction. Add to queue.
			Cell nextCell = cells[newy][newx];
			int dist = parent.getDistance() + 1;
			if(dist < nextCell.getDistance()){
				nextCell.setDistance(dist);
				nextCell.setParent(parent);
				nextCell.setDirection(parent.getDirection() + dir[i]);
				queue.add(nextCell);
			}
			
		}
		
	}
}
