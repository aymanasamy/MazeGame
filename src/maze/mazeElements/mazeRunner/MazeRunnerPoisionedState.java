package maze.mazeElements.mazeRunner;

import maze.mazeElements.DirectionableMover;

public class MazeRunnerPoisionedState implements IMazeRunnerState {
    private MazeRunner mazeRunner;

    public MazeRunnerPoisionedState(MazeRunner mazeRunner) {
        this.mazeRunner = mazeRunner;
    }

    @Override
    public void moveRight() {
        DirectionableMover.moveLeft(mazeRunner);
    }

    @Override
    public void moveLeft() {
        DirectionableMover.moveRight(mazeRunner);
    }

    @Override
    public void moveUp() {
        DirectionableMover.moveDown(mazeRunner);
    }

    @Override
    public void moveDown() {
        DirectionableMover.moveUp(mazeRunner);
    }

}
