package maze.mazeElements.walls;

import maze.IMazeElement;

public interface IWall extends IMazeElement {
    static int getInitialHealth() {
        return 1;
    }
    static boolean isBreakable() {
        return true;
    }
    int getHealth();

    void setHealth(int health);
}
