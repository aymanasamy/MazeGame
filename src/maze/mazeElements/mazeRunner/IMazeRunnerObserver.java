package maze.mazeElements.mazeRunner;

import maze.mazeElements.IDirectionalbeObserver;

public interface IMazeRunnerObserver extends IDirectionalbeObserver {
    void fire();
    void destroy();
    void setHealth(int health);
    void setLives(int lives);
    void setBullets(int bullets);
}
