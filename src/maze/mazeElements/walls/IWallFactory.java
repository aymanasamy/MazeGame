package maze.mazeElements.walls;

import maze.Maze;

public interface IWallFactory {
    public IWall generate(Maze maze, boolean isBreakable, int initialHealth);

    public void addWall(Class<? extends IWall> monster, boolean isBreakable, int initialHealth);
}
