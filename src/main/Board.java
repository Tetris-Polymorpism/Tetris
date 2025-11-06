package main;

import blocks.Block;
import java.util.Arrays;

public class Board {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 20;
    private static final char EMPTY = ' ';
    private static final char BLOCK_CHAR = '@';

    private final char[][] board;
    private final String[][] colorBoard;

    public Board() {
        board = new char[HEIGHT][WIDTH];
        colorBoard = new String[HEIGHT][WIDTH];
        initBoard();
    }

    public void initBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            Arrays.fill(board[i], EMPTY);
            Arrays.fill(colorBoard[i], "");
        }
    }

    public boolean canPlace(int x, int y, int[][] shape) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    int newX = x + j;
                    int newY = y + i;
                    if (newX < 0 || newX >= WIDTH || newY < 0 || newY >= HEIGHT) {
                        return false;
                    }
                    if (board[newY][newX] == BLOCK_CHAR) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void placeBlock(Block block) {
        int[][] shape = block.getShape();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    int y = block.getY() + i;
                    int x = block.getX() + j;
                    if (y >= 0 && y < HEIGHT && x >= 0 && x < WIDTH) {
                        board[y][x] = BLOCK_CHAR;
                        colorBoard[y][x] = block.getColor();
                    }
                }
            }
        }
    }

    public int clearLines() {
        int linesCleared = 0;
        for (int i = HEIGHT - 1; i >= 0; i--) {
            boolean full = true;
            for (int j = 0; j < WIDTH; j++) {
                if (board[i][j] == EMPTY) {
                    full = false;
                    break;
                }
            }
            if (full) {
                linesCleared++;
                for (int k = i; k > 0; k--) {
                    board[k] = Arrays.copyOf(board[k - 1], WIDTH);
                    colorBoard[k] = Arrays.copyOf(colorBoard[k - 1], WIDTH);
                }
                Arrays.fill(board[0], EMPTY);
                Arrays.fill(colorBoard[0], "");
                i++; // Check the same line again after clearing
            }
        }
        return linesCleared;
    }

    public char getCell(int y, int x) {
        return board[y][x];
    }

    public String getColor(int y, int x) {
        return colorBoard[y][x];
    }
    
    public char getBlockChar() {
        return BLOCK_CHAR;
    }
}
