package maze.mazeElements.gates;

import maze.IMazeElement;
import maze.Maze;
import maze.mazeElements.mazeRunner.MazeRunner;
import maze.mazeElements.mazeRunner.MazeRunnerWinState;

public class EndGate implements IGate {
    private static int width = 1;
    private static int height = 1;
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
    public boolean exist() {
        return true;
    }

    @Override
    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void affect(IMazeElement element) {
        if (element instanceof MazeRunner) {
            ((MazeRunner) element).setState(new MazeRunnerWinState((MazeRunner) element));
        }
    }
}
