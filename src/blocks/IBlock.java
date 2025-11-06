package blocks;

public class IBlock extends Block {
    public IBlock() {
        super();
        shape = new int[][]{{1, 1, 1, 1}};
        color = "\u001B[36m"; // Cyan
    }
}
