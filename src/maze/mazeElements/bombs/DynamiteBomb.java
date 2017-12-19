package maze.mazeElements.bombs;

import maze.IMazeElement;
import maze.Maze;
import maze.mazeElements.bullets.IBullet;
import maze.mazeElements.mazeRunner.MazeRunner;

/**
 * A bomb that always kills whenever touched.
 */
public class DynamiteBomb implements IBomb {
    private static final int width = 1;
    private static final int height = 1;
    private Maze maze;
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
        if (element instanceof MazeRunner) {
            MazeRunner runner = (MazeRunner) element;
            runner.setHealth(0);
        }
        if(element instanceof IBullet) {
            ((IBullet) element).destroy();
        }
    }

    @Override
    public void destroy() {
        maze.removeElement(this);
    }
}
