/*
 * Name: Tony An
 * PennKey: tonyjan
 * Execution: java Game
 * 
 * Description: Game class that represents the 2048 game played
 * 
 */
public class Game {
    
    public static void main(String[] args) {
        Grid grid = new Grid();
        PennDraw.clear(87, 89, 93);
        boolean firstTime = true;
        PennDraw.enableAnimation(30);
        
        while (true) {
            
            PennDraw.clear(87, 89, 93);
            grid.draw();

            if (firstTime) {
                grid.generate();
                grid.generate();
                firstTime = false;
            }
            
            // key pressed
            if (PennDraw.hasNextKeyTyped()) {
                char letter = PennDraw.nextKeyTyped();
                if (letter == 'w' && grid.validUp()) {
                    grid.getGrid().up();
                    grid.move();
                    grid.generate();
                }
                if (letter == 's' && grid.validDown()) {
                    grid.getGrid().down();
                    grid.move();
                    grid.generate();
                }
                if (letter == 'a' && grid.validLeft()) {
                    grid.getGrid().left();
                    grid.move();
                    grid.generate();
                }
                if (letter == 'd' && grid.validRight()) {
                    grid.getGrid().right();
                    grid.move();
                    grid.generate();
                }
            }
            
            // win
            if (grid.win()) {
                PennDraw.setFontSize(30);
                PennDraw.text(0.5, 0.5, "Congratulations, you won in " + 
                              grid.getMoves() + " moves!");
                break;
            }
            
            // lose
            if (grid.lose()) {
                PennDraw.setFontSize(30);
                PennDraw.text(0.5, 0.5, "You lost in " + 
                              grid.getMoves() + " moves!");
            }
            PennDraw.advance();
        }
    }
}