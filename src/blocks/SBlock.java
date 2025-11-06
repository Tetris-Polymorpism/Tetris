package blocks;

public class SBlock extends Block {
    public SBlock() {
        super();
        shape = new int[][]{{0, 1, 1}, {1, 1, 0}};
        color = "\u001B[32m"; // Green
    }
}
