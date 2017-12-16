package maze.mazeElements.bullets;

import maze.IMazeElement;
import maze.mazeElements.Direction;
import maze.mazeElements.Moveable;

public interface IBullet extends IMazeElement, Moveable {
    int getDamage();
    void destroy();
    void setDirection(Direction direction);
}
