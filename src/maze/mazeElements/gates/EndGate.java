package maze.mazeElements.gates;

import maze.IMazeElement;

public class EndGate implements IGate {
    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void affect(IMazeElement element) {

    }
}
