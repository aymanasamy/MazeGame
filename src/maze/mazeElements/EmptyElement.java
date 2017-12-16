package maze.mazeElements;

import maze.IMazeElement;
import maze.Maze;

public class EmptyElement implements IMazeElement {
    private Maze maze;

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    @Override
    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void affect(IMazeElement element) {

    }
}
