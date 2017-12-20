package maze;

import java.io.Serializable;

public interface IMazeElement extends Serializable {
    int getWidth();

    int getHeight();

    boolean exist();

    void setMaze(Maze maze);

    void affect(IMazeElement element);
}
