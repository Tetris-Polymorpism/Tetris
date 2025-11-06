package main;

import blocks.Block;

public class Movement {

    public void moveDown(Block block) {
        block.setY(block.getY() + 1);
    }

    public void moveLeft(Block block) {
        block.setX(block.getX() - 1);
    }

    public void moveRight(Block block) {
        block.setX(block.getX() + 1);
    }

    public void rotate(Block block) {
        int[][] shape = block.getShape();
        int rows = shape.length;
        int cols = shape[0].length;
        int[][] rotated = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[j][rows - 1 - i] = shape[i][j];
            }
        }
        block.setShape(rotated);
    }
}