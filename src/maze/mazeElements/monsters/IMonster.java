package maze.mazeElements.monsters;

import maze.IMazeElement;
import maze.mazeElements.Healthable;
import maze.mazeElements.Directionable;

public interface IMonster extends Directionable, Healthable {
    int getInitialHealth();
    int getHealth();

    void setHealth(int health);

    void addObserver(IMonsterObserver observer);
    void removeObserver(IMonsterObserver observer);
}
