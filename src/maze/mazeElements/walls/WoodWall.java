package maze.mazeElements.walls;

import maze.IMazeElement;
import maze.Maze;

public class WoodWall implements IWall {
    private Maze maze;
    private static int width = 1;
    private static int height = 1;
    private int health = getInitialHealth();
    static int getInitialHealth() {
        return 1;
    }
    static boolean isBreakable() {
        return true;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
        if (this.health < 0) {
            this.health = 0;
            maze.removeElement(this);
        }
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
