package maze.mazeElements.walls;

public class RandomWallFactory implements IWallFactory {
    @Override
    public IWall generate(boolean isBreakable, int initialHealth) {
        return null;
    }

    @Override
    public void addWall(Class<? extends IWall> monster, boolean isBreakable, int initialHealth) {
    }
}
