package maze.mazeElements.walls;

import maze.IMazeElement;
import maze.mazeElements.Healthable;

public interface IWall extends IMazeElement, Healthable {
    int getInitialHealth();
    boolean isBreakable();
    int getHealth();

    void setHealth(int health);
}
