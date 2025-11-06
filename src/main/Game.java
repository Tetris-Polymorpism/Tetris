package main;

import blocks.*;
import org.jline.terminal.Terminal;
import org.jline.utils.NonBlockingReader;

import java.io.IOException;
import java.util.Random;

public class Game {
    private final Board board;
    private final GameView gameView;
    private final Movement movement;
    private final Terminal terminal;
    private Block currentBlock;
    private int score;
    private boolean gameOver;

    public Game(Terminal terminal) {
        this.terminal = terminal;
        this.board = new Board();
        this.gameView = new GameView(terminal);
        this.movement = new Movement();
        this.score = 0;
        this.gameOver = false;
    }

    public void start() throws IOException {
        spawnBlock();

        // Game loop in a separate thread
        Thread gameLoop = new Thread(() -> {
            try {
                while (!gameOver) {
                    synchronized (this) {
                        moveBlockDown();
                        gameView.printBoard(board, currentBlock, score);
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        gameLoop.start();

        // Input handling loop
        NonBlockingReader reader = terminal.reader();
        while (!gameOver) {
            int ch = reader.read(100);
            if (ch >= 0) {
                synchronized (this) {
                    handleInput(ch);
                    gameView.printBoard(board, currentBlock, score);
                }
            }
        }
        
        gameLoop.interrupt(); // Stop the game loop thread
        gameView.printGameOver(score);
    }

    private void spawnBlock() {
        Random rand = new Random();
        int idx = rand.nextInt(7);
        switch (idx) {
            case 0: currentBlock = new IBlock(); break;
            case 1: currentBlock = new OBlock(); break;
            case 2: currentBlock = new TBlock(); break;
            case 3: currentBlock = new SBlock(); break;
            case 4: currentBlock = new ZBlock(); break;
            case 5: currentBlock = new JBlock(); break;
            case 6: currentBlock = new LBlock(); break;
        }

        if (!board.canPlace(currentBlock.getX(), currentBlock.getY(), currentBlock.getShape())) {
            gameOver = true;
        }
    }

    private void handleInput(int ch) {
        if (ch == 'a' || ch == 'A') {
            moveBlockLeft();
        } else if (ch == 'd' || ch == 'D') {
            moveBlockRight();
        } else if (ch == 's' || ch == 'S') {
            moveBlockDown();
        } else if (ch == 'w' || ch == 'W') {
            rotateBlock();
        }
    }

    private void moveBlockLeft() {
        if (board.canPlace(currentBlock.getX() - 1, currentBlock.getY(), currentBlock.getShape())) {
            movement.moveLeft(currentBlock);
        }
    }

    private void moveBlockRight() {
        if (board.canPlace(currentBlock.getX() + 1, currentBlock.getY(), currentBlock.getShape())) {
            movement.moveRight(currentBlock);
        }
    }

    private void moveBlockDown() {
        if (board.canPlace(currentBlock.getX(), currentBlock.getY() + 1, currentBlock.getShape())) {
            movement.moveDown(currentBlock);
        } else {
            board.placeBlock(currentBlock);
            int linesCleared = board.clearLines();
            score += linesCleared * 100;
            spawnBlock();
        }
    }

    private void rotateBlock() {
        int[][] rotatedShape = currentBlock.getRotatedShape();
        if (board.canPlace(currentBlock.getX(), currentBlock.getY(), rotatedShape)) {
            movement.rotate(currentBlock);
        }
    }
}
