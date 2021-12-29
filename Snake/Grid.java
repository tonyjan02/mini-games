package org.cis120;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.util.*;

public class Grid {
	
	private int[][] grid;
	private int score;
	private LinkedList<Integer> snake;
	private int direction;
	public final int dimension = 15;
	
	public Grid() {
		this.grid = new int[dimension][dimension];
		this.snake = new LinkedList<Integer>();
		snake.add(7 * dimension + 4);
		this.score = 0;
		grid[7][4] = 2;
		this.direction = 0;
	}
	
	public LinkedList<Integer> getSnake() {
		return this.snake;
	}
	
	public void setSnake(LinkedList<Integer> snake) {
		this.snake = snake;
	}
	
	public int[][] getGrid() {
		return this.grid;
	}
	public int getHead() {
		return snake.getFirst();
	}
	
	public int getTail() {
		return snake.getLast();
	}
	
	public void setHead(int head) {
		this.snake.addFirst(head);
	}
	
	public void moveUp() {
		this.grid[getHead() / dimension + 1][getHead() % dimension] = 2;
	}
	
	public void moveDown() {
		this.grid[getHead() / dimension - 1][getHead() % dimension] = 2;
	}
	
	public void moveLeft() {
		this.grid[getHead() / dimension][getHead() % dimension - 1] = 2;
	}
	
	public void moveRight() {
		this.grid[getHead() / dimension][getHead() % dimension + 1] = 2;
	}
	
	public void deleteTail() {
		this.grid[getTail() / dimension][getTail() % dimension] = 0;
		snake.removeLast();
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void addPoint() {
		this.score++;
	}
	
	public void move(int direction) {
		this.direction = direction;
	}
	
	public void space() {
		this.direction = 0;
	}
	
	public int getDirection() {
		return this.direction;
	}
	
	public void setGrid(int i, int j, int value) {
		this.grid[i][j] = value;
	}
	
	public void generateApple() {
		List<Integer> emptySpaces = new ArrayList<Integer>();
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[0].length; j++) {
				if (grid[i][j] == 0) {
					emptySpaces.add(dimension * i + j);
				}
			}
		}
		int randomSpace = (int) (Math.random() * emptySpaces.size());
		int location = emptySpaces.get(randomSpace);
		grid[location / dimension][location % dimension] = 1;
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[0].length; j++) {
				if (grid[i][j] == 1) {
					g.setColor(Color.RED);
					g.fillRect(690 / dimension * j, 690 - 690 / dimension * i + 1 - 46, 690 / dimension, 690 / dimension);
				} else if (grid[i][j] == 2) {
					g.setColor(Color.BLUE);
					g.fillRect(690 / dimension * j, 690 - 690 / dimension * i + 1 - 46, 690 / dimension, 690 / dimension);
				}
			}
		}
	}
	

	
		
}