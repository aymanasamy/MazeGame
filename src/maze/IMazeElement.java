package maze;

public interface IMazeElement {
    public int getWidth();

    public int getHeight();

    void affect(IMazeElement element);
}
