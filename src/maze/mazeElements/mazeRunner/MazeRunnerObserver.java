package maze.mazeElements.mazeRunner;

import maze.mazeElements.DirectionalbeObserver;

public interface MazeRunnerObserver extends DirectionalbeObserver{
    void fire();
    void destroy();
    void setHealth(int health);
    void setLives(int lives);
    void setBullets(int bullets);
}
