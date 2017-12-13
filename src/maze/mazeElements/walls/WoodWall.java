package maze.mazeElements.walls;

import maze.IMazeElement;

public class WoodWall implements IWall {
    static int getInitialHealth() {
        return 1;
    }
    static boolean isBreakable() {
        return true;
    }
    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public void setHealth(int health) {

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void affect(IMazeElement element) {

    }
}
