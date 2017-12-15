package maze.mazeElements.gifts;

import maze.IMazeElement;
import maze.mazeElements.mazeRunner.MazeRunner;

/**
 * Arms the Maze Runner with two additional bullets
 */
public class AmmoGift implements IGift {
    private static final int width = 1;
    private static final int height = 1;
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void affect(IMazeElement element) {
        if (element instanceof MazeRunner) {
            MazeRunner runner = (MazeRunner) element;
            runner.setBullets(runner.getBullets() + 2);
        }
    }
}
