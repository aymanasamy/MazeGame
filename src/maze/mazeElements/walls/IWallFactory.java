package maze.mazeElements.walls;

public interface IWallFactory {
    public IWall generate(boolean isBreakable,int initialHealth);

    public void addWall(Class<? extends IWall> monster, boolean isBreakable, int initialHealth);
}
