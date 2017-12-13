package maze.mazeElements.bullets;

import maze.Maze;
import maze.mazeElements.Direction;

public interface IBulletsFactory {
    IBullet generate(int damage, Maze maze, Direction direction);
}
