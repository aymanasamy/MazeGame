package maze.mazeElements.mazeRunner;

import java.io.Serializable;

public interface IMazeRunnerState extends Serializable {
    void moveRight();

    void moveLeft();

    void moveUp();

    void moveDown();
}
