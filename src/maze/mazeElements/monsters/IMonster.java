package maze.mazeElements.monsters;

import maze.IMazeElement;
import maze.mazeElements.Healthable;
import maze.mazeElements.Directionable;
import maze.mazeElements.Moveable;

public interface IMonster extends Directionable, Healthable, Moveable {
    int getInitialHealth();
    int getHealth();

    void setHealth(int health);

    void addObserver(IMonsterObserver observer);
    void removeObserver(IMonsterObserver observer);
}
