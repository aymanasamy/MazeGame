package maze.mazeElements.bullets;

import maze.IMazeElement;
import maze.mazeElements.Movable;

public interface IBullet extends IMazeElement, Movable {
    int getDamage();
}
