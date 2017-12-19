package maze.mazeElements.mazeRunner;

import maze.mazeElements.DirectionableMover;

public class MazeRunnerNormalState implements IMazeRunnerState {
    private MazeRunner mazeRunner;
    public MazeRunnerNormalState(MazeRunner mazeRunner){
        this.mazeRunner = mazeRunner;
    }
    @Override
    public void moveRight() {
        DirectionableMover.moveRight(mazeRunner);
    }
    @Override
    public void moveLeft() {
        DirectionableMover.moveLeft(mazeRunner);
    }

    @Override
    public void moveUp() {
        DirectionableMover.moveUp(mazeRunner);
    }

    @Override
    public void moveDown() {
        DirectionableMover.moveDown(mazeRunner);
    }


}
