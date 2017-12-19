package maze.mazeElements.monsters;

import maze.mazeElements.IDirectionalbeObserver;

public interface IMonsterObserver extends IDirectionalbeObserver{
    void destroy();
    void setHealth(int health);
    void affect();
}
