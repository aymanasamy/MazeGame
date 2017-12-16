package maze.mazeElements.bullets;

import maze.IMazeElement;
import maze.mazeElements.Directionable;

public interface IBullet extends IMazeElement, Directionable {
    int getDamage();
}
