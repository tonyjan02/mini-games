/*
 * Name: Tony An
 * PennKey: tonyjan
 * Execution: java Block
 * 
 * Description: Block class that represents each numbered block in 2048 game
 * 
 */
public class Block {
    
    // instance variable
    private int value;
    
    // empty block
    public Block() {
        value = 0;
    }
    
    // block with given value
    public Block(int value) {
        this.value = value;
    }
    
    /** 
    * Description: Replicates combining of blocks, doubling the value of a block
    */
    public void combine() {
        this.value *= 2;
    }
    
    /** 
    * Description: Replicates combining of blocks, 
    *              deleting one block that crashes into another
    */
    public void delete() {
        value = 0;
    }
    
    /** 
    * Output: boolean isEmpty
    * Description: Checks if the block is empty
    */
    public boolean isEmpty() {
        if (value == 0) {
            return true;
        }
        return false;
    }
    
    /** 
    * Output: int value
    * Description: Returns value of block
    */
    public int getValue() {
        return value;
    }
    
    /** 
    * Input: int value
    * Description: Sets the value of block
    */
    public void setValue(int value) {
        this.value = value;
    }
    
    /** 
    * Input: double x, double y
    * Description: Draws the block in specified position, 
    *              changing colors and numbers based on its value
    */
    public void draw(double x, double y) {
        PennDraw.setFontBold();
        
        switch (value) {
            case 0:
                PennDraw.setPenColor(211, 211, 211);
                PennDraw.filledSquare(x, y, 0.1);
                break;
            case 2: 
                PennDraw.setPenColor(241, 196, 15);
                PennDraw.filledSquare(x, y, 0.1);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(x, y, "2");
                break;
            case 4:
                PennDraw.setPenColor(230, 126, 34);
                PennDraw.filledSquare(x, y, 0.1);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(x, y, "4");
                break;
            case 8:
                PennDraw.setPenColor(231, 76, 60);
                PennDraw.filledSquare(x, y, 0.1);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(x, y, "8");
                break;
            case 16:
                PennDraw.setPenColor(78, 46, 83);
                PennDraw.filledSquare(x, y, 0.1);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(x, y, "16");
                break;
            case 32:
                PennDraw.setPenColor(127, 140, 141);
                PennDraw.filledSquare(x, y, 0.1);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(x, y, "32");
                break;
            case 64:
                PennDraw.setPenColor(149, 85, 0);
                PennDraw.filledSquare(x, y, 0.1);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(x, y, "64");
                break;
            case 128:
                PennDraw.setPenColor(52, 73, 94);
                PennDraw.filledSquare(x, y, 0.1);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(x, y, "128");
                break;
            case 256:
                PennDraw.setPenColor(155, 89, 182);
                PennDraw.filledSquare(x, y, 0.1);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(x, y, "256");
                break;
            case 512:
                PennDraw.setPenColor(52, 152, 219);
                PennDraw.filledSquare(x, y, 0.1);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(x, y, "512");
                break;
            case 1024:
                PennDraw.setPenColor(46, 204, 113);
                PennDraw.filledSquare(x, y, 0.1);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(x, y, "1024");
                break;
            case 2048:
                PennDraw.setPenColor(26, 188, 156);
                PennDraw.filledSquare(x, y, 0.1);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(x, y, "2048");
                break;
            default:
                break;
        }
    }
}