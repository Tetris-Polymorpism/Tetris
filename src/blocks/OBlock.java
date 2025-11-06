package blocks;

public class OBlock extends Block {
    public OBlock() {
        super();
        shape = new int[][]{{1, 1}, {1, 1}};
        color = "\u001B[33m"; // Yellow
    }

    @Override
    public int[][] getRotatedShape() {
        return shape; // Return the same shape
    }
}
