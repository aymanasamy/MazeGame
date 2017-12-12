package maze.mazeElements.monsters;

import maze.IMazeElement;
import maze.mazeElements.Movable;

public interface IMonster extends IMazeElement, Movable {
    static int getInitialHealth() {
        return 1;
    }

    int getHealth();

    void setHealth(int health);
}
