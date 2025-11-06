package blocks;

public class TBlock extends Block {
    public TBlock() {
        super();
        shape = new int[][]{{0, 1, 0}, {1, 1, 1}};
        color = "\u001B[35m"; // Purple
    }
}
