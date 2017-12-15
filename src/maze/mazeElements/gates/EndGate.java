package maze.mazeElements.gates;

import maze.IMazeElement;
import maze.mazeElements.mazeRunner.MazeRunner;

public class EndGate implements IGate {
    private static int width = 1;
    private static int height = 1;
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
            // TODO: ((MazeRunner) element).getMaze().win();
        }
    }
}
