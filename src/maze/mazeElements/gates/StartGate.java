package maze.mazeElements.gates;

import maze.IMazeElement;
import maze.Maze;

public class StartGate implements IGate {
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
    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void affect(IMazeElement element) {
    }
}
