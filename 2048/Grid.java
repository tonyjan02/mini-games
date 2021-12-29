/*
 * Name: Tony An
 * PennKey: tonyjan
 * Execution: java Grid
 * 
 * Description: Grid class that represents the 4 x 4 grid
 * 
 */

import java.util.List;
import java.util.ArrayList;
public class Grid {
    
    // instance variable
    private Block[][] grid;
    private int moves;
    
    // constructor
    public Grid() {
        grid = new Block[4][4];
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = new Block(0);
            }
        }
    }
    
    /** 
    * Output: boolean win
    * Description: Returns true if the player has won (reached 2048), false otherwise
    */
    public boolean win() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j].getValue() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /** 
    * boolean lose
    * Description: Returns true if the player has lost 
    *              (no possible moves), false otherwise
    */
    public boolean lose() {
        return !validUp() && !validDown() && !validLeft() && !validRight();
    }
    
    /** 
    * Description: Replicates a move by counting the number of total moves made
    */
    public void move() {
        moves++;
    }
    
    /** 
    * int moves
    * Description: Returns the number of total moves made
    */
    public int getMoves() {
        return moves;
    }
    
    /** 
    * Grid grid
    * Description: Returns the Grid (getter for Game class)
    */
    public Grid getGrid() {
        return this;
    }
    
    /** 
    * Description: Up move that shifts all blocks upwards
    */
    public void up() {
        shiftUp();
        mergeUp();
        shiftUp();
    }
    
    /** 
    * Description: Helper function for up method that shifts without merging
    */
    public void shiftUp() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[j][i].getValue() == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        if (grid[k][i].getValue() != 0) {
                            grid[j][i].setValue(grid[k][i].getValue());
                            grid[k][i].setValue(0);
                            break;
                        }
                    }
                }
            }
        }
    }
    
    /** 
    * Description: Helper function for up method that merges without shifting
    */
    public void mergeUp() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[j][i].getValue() == grid[j + 1][i].getValue()) {
                    grid[j][i].combine();
                    grid[j + 1][i].delete();
                }
            }
        }
    }
    
    /** 
    * Description: Determines if up is a valid move
    */    
    public boolean validUp() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                // two adjacent, non-zero values
                if (grid[j][i].getValue() == grid[j + 1][i].getValue() && 
                    grid[j][i].getValue() != 0) {
                    return true;
                }
                // two adjacent, one non-zero, one-zer values
                if (grid[j][i].getValue() == 0 && grid[j + 1][i].getValue() != 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /** 
    * Description: Down move that shifts all blocks upwards
    */    
    public void down() {
        shiftDown();
        mergeDown();
        shiftDown();
    }
    
    /** 
    * Description: Helper function for down method that shifts without merging
    */    
    public void shiftDown() {
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                if (grid[j][i].getValue() == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        if (grid[k][i].getValue() != 0) {
                            grid[j][i].setValue(grid[k][i].getValue());
                            grid[k][i].setValue(0);
                            break;
                        }
                    }
                }
            }
        }
    }
    
    /** 
    * Description: Helper function for down method that merges without shifting
    */
    public void mergeDown() {
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                if (grid[j][i].getValue() == grid[j - 1][i].getValue()) {
                    grid[j][i].combine();
                    grid[j - 1][i].delete();
                }
            }
        }
    }
    
    /** 
    * Description: Determines if down is a valid move
    */    
    public boolean validDown() {
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                // two adjacent, non-zero values
                if (grid[j][i].getValue() == grid[j - 1][i].getValue() && 
                    grid[j][i].getValue() != 0) {
                    return true;
                }
                // two adjacent, one non-zero, one-zer values
                if (grid[j][i].getValue() == 0 && grid[j - 1][i].getValue() != 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /** 
    * Description: Left move that shifts all blocks upwards
    */    
    public void left() {
        shiftLeft();
        mergeLeft();
        shiftLeft();
    }

    /** 
    * Description: Helper function for left method that shifts without merging
    */
    public void shiftLeft() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j].getValue() == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        if (grid[i][k].getValue() != 0) {
                            grid[i][j].setValue(grid[i][k].getValue());
                            grid[i][k].setValue(0);
                            break;
                        }
                    }
                }
            }
        }
    }
    
    /** 
    * Description: Helper function for left method that merges without shifting
    */
    public void mergeLeft() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j].getValue() == grid[i][j + 1].getValue()) {
                    grid[i][j].combine();
                    grid[i][j + 1].delete();
                }
            }
        }       
    }
    
    /** 
    * Description: Determines if left is a valid move
    */    
    public boolean validLeft() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                // two adjacent, non-zero values
                if (grid[i][j].getValue() == grid[i][j + 1].getValue() && 
                    grid[i][j].getValue() != 0) {
                    return true;
                }
                // two adjacent, one non-zero, one-zer values
                if (grid[i][j].getValue() == 0 && grid[i][j + 1].getValue() != 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /** 
    * Description: Down move that shifts all blocks upwards
    */    
    public void right() {
        shiftRight();
        mergeRight();
        shiftRight();
    }

    /** 
    * Description: Helper function for right method that shifts without merging
    */
    public void shiftRight() {
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                if (grid[i][j].getValue() == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        if (grid[i][k].getValue() != 0) {
                            grid[i][j].setValue(grid[i][k].getValue());
                            grid[i][k].setValue(0);
                            break;
                        }
                    }
                }
            }
        }
    }
    
    /** 
    * Description: Helper function for right method that merges without shifting
    */
    public void mergeRight() {
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                if (grid[i][j].getValue() == grid[i][j - 1].getValue()) {
                    grid[i][j].combine();
                    grid[i][j - 1].delete();
                }
            }
        }
    }
    
    /** 
    * Description: Determines if right is a valid move
    */    
    public boolean validRight() {
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                // two adjacent, non-zero values
                if (grid[i][j].getValue() == grid[i][j - 1].getValue() && 
                    grid[i][j].getValue() != 0) {
                    return true;
                }
                // two adjacent, one non-zero, one-zer values
                if (grid[i][j].getValue() == 0 && grid[i][j - 1].getValue() != 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /** 
    * Description: Randomly generates a 2 or 4 in an empty space
    */
    public void generate() {
        List<Block> emptySpaces = new ArrayList<Block>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j].getValue() == 0) {
                    emptySpaces.add(grid[i][j]);
                }
            }
        }
        int random = (int) (Math.random() * emptySpaces.size());
        double randomValue = Math.random();
        if (randomValue < 0.5) {
            randomValue = 2;
        }
        else {
            randomValue = 4;
        }
        emptySpaces.get(random).setValue((int) randomValue);
    }
    
    
    /** 
    * Description: Draws the grid and all the blocks inside the grid
    */
    public void draw() {
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j].draw(.125 + .25 * j, 1 - (.125 + .25 * i));
            }
        }
    }
    
}