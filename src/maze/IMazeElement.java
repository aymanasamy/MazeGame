package maze;

import java.io.Serializable;

public interface IMazeElement extends Serializable {
    public int getWidth();

    public int getHeight();

    void setMaze(Maze maze);

    void affect(IMazeElement element);
}
