package blocks;

public class LBlock extends Block {
    public LBlock() {
        super();
        shape = new int[][]{{0, 0, 1}, {1, 1, 1}};
        color = "\u001B[37m"; // White
    }
}
