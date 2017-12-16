package maze.mazeElements.walls;

import maze.IMazeElement;
import maze.Maze;

public class UnbreakableWall implements IWall {
    private static int width = 1;
    private static int height = 1;
    private Maze maze;
    static int getInitialHealth() {
        return 1;
    }
    static boolean isBreakable() {
        return false;
    }
    @Override
    public int getHealth() {
        return 1;
    }

    @Override
    public void setHealth(int health) {
        // Unbreakable, so leave health as it is
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void affect(IMazeElement element) {

    }
}
