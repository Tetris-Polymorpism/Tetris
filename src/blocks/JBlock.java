package blocks;

public class JBlock extends Block {
    public JBlock() {
        super();
        shape = new int[][]{{1, 0, 0}, {1, 1, 1}};
        color = "\u001B[34m"; // Blue
    }
}
