package maze.mazeElements.bombs;

import maze.IMazeElement;
import maze.ScoreHandler;
import maze.mazeElements.bullets.IBullet;
import maze.mazeElements.mazeRunner.MazeRunner;

/**
 * A bomb that always kills whenever touched.
 */
public class DynamiteBomb extends IBomb {
    public DynamiteBomb() {
        width = 1;
        height = 1;
    }

    @Override
    public void affect(IMazeElement element) {
        if (element instanceof MazeRunner) {
            MazeRunner runner = (MazeRunner) element;
            runner.setHealth(0);
            ScoreHandler.subtractFromScore(10);
        }
        if (element instanceof IBullet) {
            ((IBullet) element).destroy();
            ScoreHandler.addToScore(10);
        }
    }
}
