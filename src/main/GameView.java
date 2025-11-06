package main;

import blocks.Block;
import org.jline.terminal.Terminal;

import java.util.Arrays;

public class GameView {
    private final Terminal terminal;

    public GameView(Terminal terminal) {
        this.terminal = terminal;
    }

    public void clearConsole() {
        terminal.writer().print("\033[H\033[2J");
        terminal.flush();
    }

    public void printBoard(Board board, Block currentBlock, int score) {
        clearConsole();
        terminal.writer().println("Score: " + score);

        char[][] tempBoard = new char[Board.HEIGHT][Board.WIDTH];
        String[][] tempColor = new String[Board.HEIGHT][Board.WIDTH];

        // Copy existing board state
        for (int i = 0; i < Board.HEIGHT; i++) {
            for (int j = 0; j < Board.WIDTH; j++) {
                tempBoard[i][j] = board.getCell(i, j);
                tempColor[i][j] = board.getColor(i, j);
            }
        }

        // Draw the current block onto the temp board
        if (currentBlock != null) {
            int[][] shape = currentBlock.getShape();
            for (int i = 0; i < shape.length; i++) {
                for (int j = 0; j < shape[i].length; j++) {
                    if (shape[i][j] == 1) {
                        int y = currentBlock.getY() + i;
                        int x = currentBlock.getX() + j;
                        if (y >= 0 && y < Board.HEIGHT && x >= 0 && x < Board.WIDTH) {
                            tempBoard[y][x] = board.getBlockChar();
                            tempColor[y][x] = currentBlock.getColor();
                        }
                    }
                }
            }
        }

        // Print the final board
        for (int i = 0; i < Board.HEIGHT; i++) {
            terminal.writer().print("|");
            for (int j = 0; j < Board.WIDTH; j++) {
                char cell = tempBoard[i][j];
                String color = tempColor[i][j];
                if (cell == board.getBlockChar() && color != null && !color.isEmpty()) {
                    terminal.writer().print(color + cell + "\u001B[0m");
                } else {
                    terminal.writer().print(cell);
                }
            }
            terminal.writer().println("|");
        }

        terminal.writer().print("-");
        for (int i = 0; i < Board.WIDTH; i++) terminal.writer().print("-");
        terminal.writer().println("-");
        terminal.flush();
    }
    
    public void printGameOver(int score) {
        terminal.writer().println("Game Over! Total Score: " + score);
        terminal.flush();
    }
}
