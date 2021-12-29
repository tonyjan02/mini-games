package org.cis120;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.*;


public class GameCourt extends JPanel {
	
	private Grid grid;
	private JLabel status;
	private boolean playing = false;
	private int score;
	private LinkedList<Integer> moves = new LinkedList<Integer>();
	public static final int WIDTH = 690;
	public static final int HEIGHT = 690;
	public static final int INTERVAL = 110;
	
	
	public GameCourt(JLabel status) {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();
		
		setFocusable(true);
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					if (grid.getDirection() != 2 && grid.getDirection() != 1) {
						moves.add(1);
					}
				} else if (e.getKeyCode() == KeyEvent.VK_S) {
					if (grid.getDirection() != 1 && grid.getDirection() != 2) {
						moves.add(2);
					}
				} else if (e.getKeyCode() == KeyEvent.VK_A) {
					if (grid.getDirection() != 4 && grid.getDirection() != 3) {
						moves.add(3);
					}
				} else if (e.getKeyCode() == KeyEvent.VK_D) {
					if (grid.getDirection() != 3 && grid.getDirection() != 4) {
						moves.add(4);
					}				
				} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					grid.space();
				}
			}
		});
		
		this.status = status;
	}
	
	public void reset() {
		grid = new Grid();
		grid.generateApple();
		playing = true;
		score = 0;
		status.setText("Eat an apple to start!");
		requestFocusInWindow();
	}
	
	public String save() {			
		try {
			int[][] savedGrid = grid.getGrid();
			BufferedWriter bw = new BufferedWriter(new FileWriter("save.txt"));
			// ordering of saved file: current score, snake, grid status
			bw.write(score);
			bw.write(grid.getSnake().size());
			for (int i = 0; i < grid.getSnake().size(); i++) {
				bw.write(grid.getSnake().get(i));
			}
			for (int i = savedGrid.length - 1; i > 0; i--) {
				for (int j = 0; j < savedGrid[0].length; j++) {
					bw.write(savedGrid[i][j] + "");
					System.out.print(savedGrid[i][j]);
				}
				System.out.println("");
			}
			bw.close();
			System.out.println("End save");
		} catch (IOException e) {
			return "Error: Unexpected error with the grid!";
		}
		return "Game successfully saved!";
	}
	
	public String load() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("save.txt"));
			try {
				score = br.read();
				int length = br.read();
				System.out.println(length);
				LinkedList<Integer> tempSnake = new LinkedList<Integer>();
				for (int i = 0; i < length; i++) {
					tempSnake.add(br.read());
				}
				grid.setSnake(tempSnake);
				for (int i = grid.dimension - 1; i > 0; i--) {
					for (int j = 0; j < grid.dimension; j++) {
						this.grid.setGrid(i, j, br.read() - 48);
						System.out.print(grid.getGrid()[i][j]);
					}
					System.out.println("");
				}
				br.close();
				System.out.println("End load");
				System.out.println(playing);
				playing = true;
				grid.move(0);
				repaint();
				requestFocusInWindow();
			} catch (IOException e) {
				return "Error: Unexpected IO Exception, please make sure the file is correct!";
			}
		} catch (FileNotFoundException e) {
			return "Error: File was not found!";
		}
		return "Save successfully loaded!";
	}
	
	void tick() {
		if (playing) {
			if (!moves.isEmpty()) {
				grid.move(moves.pop());
			}
			if (grid.getDirection() == 1) {
				// up
				if (grid.getHead() / grid.dimension + 1 >= grid.dimension || this.grid.getGrid()[grid.getHead() / grid.dimension + 1][grid.getHead() % grid.dimension] == 2) {
					// crash into top wall or itself
					playing = false;
					status.setText("You lose!");
				} else if (this.grid.getGrid()[grid.getHead() / grid.dimension + 1][grid.getHead() % grid.dimension] == 1) {
					// eats apple
					grid.moveUp();
					grid.setHead(grid.getHead() + grid.dimension);
					grid.generateApple();
					score++;
					status.setText("Your score: " + score);
				} else {
					// regular move
					grid.moveUp();
					grid.setHead(grid.getHead() + grid.dimension);
					grid.deleteTail();
				}
			} else if (grid.getDirection() == 2) {
				// down
				if (grid.getHead() / grid.dimension - 1 < 0 || this.grid.getGrid()[grid.getHead() / grid.dimension - 1][grid.getHead() % grid.dimension] == 2) {
					// crash into bottom wall or itself
					playing = false;
					status.setText("You lose!");
				} else if (this.grid.getGrid()[grid.getHead() / grid.dimension - 1][grid.getHead() % grid.dimension] == 1) {
					// eats apple
					grid.moveDown();
					grid.setHead(grid.getHead() - grid.dimension);
					grid.generateApple();
					score++;
					status.setText("Your score: " + score);
				} else {
					// regular move
					grid.moveDown();
					grid.setHead(grid.getHead() - grid.dimension);
					grid.deleteTail();
				}
			} else if (grid.getDirection() == 3) {
				// left
				if (grid.getHead() % grid.dimension - 1 < 0 || this.grid.getGrid()[grid.getHead() / grid.dimension][grid.getHead() % grid.dimension - 1] == 2) {
					// crash into left wall or itself
					playing = false;
					status.setText("You lose!");
				} else if (this.grid.getGrid()[grid.getHead() / grid.dimension][grid.getHead() % grid.dimension - 1] == 1) {
					// eats apple
					grid.moveLeft();
					grid.setHead(grid.getHead() - 1);
					grid.generateApple();
					score++;
					status.setText("Your score: " + score);
				} else {
					// regular move
					grid.moveLeft();
					grid.setHead(grid.getHead() - 1);
					grid.deleteTail();
				}
			} else if (grid.getDirection() == 4) {
				// right
				if (grid.getHead() % grid.dimension + 1 >= grid.dimension || this.grid.getGrid()[grid.getHead() / grid.dimension][grid.getHead() % grid.dimension + 1] == 2) {
					// crash into right wall or itself
					playing = false;
					status.setText("You lose!");
				} else if (this.grid.getGrid()[grid.getHead() / grid.dimension][grid.getHead() % grid.dimension + 1] == 1) {
					// eats apple
					grid.moveRight();
					grid.setHead(grid.getHead() + 1);
					grid.generateApple();
					score++;
					status.setText("Your score: " + score);
				} else {
					// regular move
					grid.moveRight();
					grid.setHead(grid.getHead() + 1);
					grid.deleteTail();
				}
			}
			repaint();
		}
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// draw grid lines
		for (int i = 0; i <= grid.dimension; i++) {
			g.drawLine(690 / grid.dimension * i, 0, 690 / grid.dimension * i, 690);
			g.drawLine(0, 690 / grid.dimension * i, 690, 690 / grid.dimension * i);
		}
		
		grid.draw(g);
				
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}
}
