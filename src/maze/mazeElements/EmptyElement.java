package maze.mazeElements;

import maze.IMazeElement;

public class EmptyElement implements IMazeElement {
    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    @Override
    public void affect(IMazeElement element) {

    }
}
