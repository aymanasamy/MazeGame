package maze.mazeElements.mazeRunner;

import maze.mazeElements.IDirectionalbeObserver;

import java.io.Serializable;

public interface IMazeRunnerObserver extends DirectionalbeObserver, Serializable{
    void fire();
    void destroy();
    void setHealth(int health);
    void setLives(int lives);
    void setBullets(int bullets);
    void affect();
}
