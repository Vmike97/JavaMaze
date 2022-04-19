package Dungeon.Main;
import Dungeon.MazeGeneration.MazeGenerator;

public class Main {

	public static void main(String[] args) {
		//                          (width, length, obstacle#, start, end)
		MazeGenerator maze = new MazeGenerator(10,10,20,new int[]{0,0},new int[]{9,9});
		maze.display();
		System.out.println();
		System.out.println(maze.solveMinDistance());	
	}
}
