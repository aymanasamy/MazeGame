package maze.mazeElements.walls;

import maze.IMazeElement;
import maze.mazeElements.Healthable;

public interface IWall extends IMazeElement, Healthable {
    static int getInitialHealth() {
        return 1;
    }
    static boolean isBreakable() {
        return true;
    }
    int getHealth();

    void setHealth(int health);
}
