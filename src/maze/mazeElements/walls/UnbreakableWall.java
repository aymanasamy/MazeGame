package maze.mazeElements.walls;

import maze.IMazeElement;

public class UnbreakableWall implements IWall {
    static int getInitialHealth() {
        return 0;
    }
    static boolean isBreakable() {
        return false;
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
