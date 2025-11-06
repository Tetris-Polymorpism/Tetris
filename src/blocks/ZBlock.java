package blocks;

public class ZBlock extends Block {
    public ZBlock() {
        super();
        shape = new int[][]{{1, 1, 0}, {0, 1, 1}};
        color = "\u001B[31m"; // Red
    }
}
