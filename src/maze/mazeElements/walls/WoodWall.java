package maze.mazeElements.walls;

import maze.IMazeElement;
import maze.Maze;
import maze.mazeElements.bullets.IBullet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WoodWall implements IWall {
    private static final Logger logger = LogManager.getLogger(WoodWall.class);
    private Maze maze;
    private static int width = 1;
    private static int height = 1;
    private int health = getInitialHealth();

    public int getInitialHealth() {
        return 1;
    }

    public boolean isBreakable() {
        return true;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
        if (this.health <= 0) {
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
    public boolean exist() {
        return getHealth() > 0;
    }

    @Override
    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void affect(IMazeElement element) {
        if(element instanceof IBullet) {
            ((IBullet) element).destroy();
            logger.debug("WoodWall {} is affecting a bullet", this.hashCode());
        }
    }
}
