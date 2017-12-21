package maze.mazeElements.bombs;

import maze.IMazeElement;
import maze.mazeElements.bullets.IBullet;
import maze.mazeElements.mazeRunner.MazeRunner;

/**
 * A bomb that decrease the health by 1
 */
public class SimpleBomb extends IBomb {
    public SimpleBomb() {
        width = 1;
        height = 1;
    }

    @Override
    public void affect(IMazeElement element) {
        if (element instanceof MazeRunner) {
            MazeRunner runner = (MazeRunner) element;
            runner.setHealth(runner.getHealth()-1);
        }
        if (element instanceof IBullet) {
            ((IBullet) element).destroy();
        }
    }
}
