package org.cis120;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

public class SnakeTest {
	
	@Test
	public void testGrid() {
		Grid grid = new Grid();
		assertEquals(2, grid.getGrid()[7][4]);
	}	
	
	@Test
	public void testUp() {
		Grid grid = new Grid();
		grid.moveUp();
		assertEquals(2, grid.getGrid()[8][4]);
	}
	
	@Test
	public void testDown() {
		Grid grid = new Grid();
		grid.moveDown();
		assertEquals(2, grid.getGrid()[6][4]);
	}
	
	@Test
	public void testLeft() {
		Grid grid = new Grid();
		grid.moveLeft();
		assertEquals(2, grid.getGrid()[7][3]);
	}
	
	@Test
	public void testRight() {
		Grid grid = new Grid();
		grid.moveRight();
		assertEquals(2, grid.getGrid()[7][5]);
	}
	
	@Test
	public void testGetHead() {
		Grid grid = new Grid();
		assertEquals(7 * 15 + 4, grid.getHead());
	}

	@Test
	public void testGetTail() {
		Grid grid = new Grid();
		assertEquals(7 * 15 + 4, grid.getTail());
	}
	
	@Test
	public void testSetHead() {
		Grid grid = new Grid();
		grid.setHead(5 * 15 + 1);
		assertEquals((int) grid.getSnake().getFirst(), grid.getHead());
		assertEquals(5 * 15 + 1, grid.getHead());
	}
	
	@Test
	public void testSetSnake() {
		Grid grid = new Grid();
		LinkedList<Integer> snake = new LinkedList<Integer>();
		snake.add(1 * 15 + 1);
		snake.add(2 * 15 + 2);
		grid.setSnake(snake);
		assertEquals(1 * 15 + 1, grid.getHead());
		assertEquals(2 * 15 + 2, grid.getTail());
	}
	
	@Test
	public void testGetScore() {
		Grid grid = new Grid();
		assertEquals(0, grid.getScore());
	}
	
	@Test
	public void testAddPoint() {
		Grid grid = new Grid();
		grid.addPoint();
		assertEquals(1, grid.getScore());
		grid.addPoint();
		assertEquals(2, grid.getScore());
	}
	
	@Test
	public void testGenerateApple() {
		Grid grid = new Grid();
		grid.generateApple();
		boolean containsApple = false;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (grid.getGrid()[i][j] == 1) {
					containsApple = true;
				}
			}
		}
		assertTrue(containsApple);
	}
	
	@Test
	public void testSpace() {
		Grid grid = new Grid();
		grid.space();
		assertEquals(0, grid.getDirection());
	}
	
	/*
	 * Although I would've wanted to test more of the functionality,
	 * I wasn't able to as we were told not to test the functionality
	 * of the GameCourt (GUI) component of the class. I asked on
	 * Piazza and got the okay that we didn't have to test these methods.
	 */
	
	
}