package maze.mazeElements.bullets;

        import maze.Maze;
        import maze.mazeElements.Direction;

public interface IBulletsFactory {
    IBullet generate(Maze maze, int damage, Direction direction);
}
