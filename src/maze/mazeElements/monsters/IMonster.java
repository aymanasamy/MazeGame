package maze.mazeElements.monsters;

import maze.IMazeElement;
import maze.mazeElements.Healthable;
import maze.mazeElements.Directionable;

public interface IMonster extends Directionable, Healthable {
    static int getInitialHealth() {
        return 1;
    }

    int getHealth();

    void setHealth(int health);

    void addObserver(IMonsterObserver observer);
    void removeObserver(IMonsterObserver observer);
}
