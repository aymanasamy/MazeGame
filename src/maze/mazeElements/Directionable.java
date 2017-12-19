package maze.mazeElements;

import maze.IMazeElement;
import maze.Maze;

public interface Directionable extends IMazeElement {
    Direction getDirection();
    void setDirection(Direction dir);
    Maze getMaze();
    void notifyMoveIn(Direction dir);
}
