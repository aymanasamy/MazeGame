package maze.mazeElements.walls;

import maze.IMazeElement;
import maze.Maze;
import maze.mazeElements.bullets.IBullet;

public class UnbreakableWall implements IWall {
    private static int width = 1;
    private static int height = 1;
    private Maze maze;
    public int getInitialHealth() {
        return 1;
    }
    public boolean isBreakable() {
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
    public boolean exist() {
        return true;
    }

    @Override
    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void affect(IMazeElement element) {
        if(element instanceof IBullet) {
            ((IBullet) element).destroy();
        }
    }
}
