package maze.mazeElements.gifts;

import maze.IMazeElement;
import maze.Maze;
import maze.mazeElements.bullets.IBullet;
import maze.mazeElements.mazeRunner.MazeRunner;

/**
 * Arms the Maze Runner with two additional bullets
 */
public class AmmoGift implements IGift {
    private static final int width = 1;
    private static final int height = 1;
    private static final int additinalAmmo = 2;
    private Maze maze;
    private boolean exist = true;

    public void setMaze(Maze maze){
        this.maze = maze;
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
        return exist;
    }

    @Override
    public void affect(IMazeElement element) {
        if (element instanceof MazeRunner) {
            MazeRunner runner = (MazeRunner) element;
            runner.setBullets(runner.getBullets() + additinalAmmo);
        }
        if (element instanceof IBullet)
            ((IBullet) element).destroy();
    }

    @Override
    public void destroy() {
        exist = false;
        maze.removeElement(this);
    }
}
