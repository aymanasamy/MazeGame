package maze.mazeElements.mazeRunner;

import maze.mazeElements.DirectionalbeObserver;

import java.io.Serializable;

public interface MazeRunnerObserver extends DirectionalbeObserver, Serializable{
    void fire();
    void destroy();
    void setHealth(int health);
    void setLives(int lives);
    void setBullets(int bullets);
}
