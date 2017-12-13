package maze.mazeElements.monsters;

import maze.IMazeElement;
import maze.mazeElements.Healthable;
import maze.mazeElements.Movable;

public interface IMonster extends IMazeElement, Movable, Healthable {
    static int getInitialHealth() {
        return 1;
    }

    int getHealth();

    void setHealth(int health);
}
