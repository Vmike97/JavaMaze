package Dungeon.Cell;

public class Cell {
	private int x;
	private int y;
	private int distance;
	private Cell parent;
	private String direction;
	
	public Cell(int column, int row, int distance, Cell parent){
		this.x = column;
		this.y = row;
		this.distance = distance;
		this.parent = parent;
	}
	
	public Cell(int column, int row, int distance, Cell parent, String direction){
		this(column, row, distance, parent);
		this.direction = direction;	
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Cell getParent() {
		return parent;
	}

	public void setParent(Cell parent) {
		this.parent = parent;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	
	
}
