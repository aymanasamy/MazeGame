package maze;

public interface IMazeElement {
    public int getWidth();

    public int getHeight();

    void setMaze(Maze maze);

    void affect(IMazeElement element);
}
