package maze.mazeElements.mazeRunner;

import maze.Maze;
import maze.mazeElements.Direction;

import java.awt.*;

public class MazeRunnerNormalState implements IMazeRunnerState {
    private MazeRunner mazeRunner;
    public MazeRunnerNormalState(MazeRunner mazeRunner){
        this.mazeRunner = mazeRunner;
    }
    @Override
    public void moveRight() {
        MazeRunnerMover.moveRight(mazeRunner);
    }
    @Override
    public void moveLeft() {
        MazeRunnerMover.moveLeft(mazeRunner);
    }

    @Override
    public void moveUp() {
        MazeRunnerMover.moveUp(mazeRunner);
    }

    @Override
    public void moveDown() {
        MazeRunnerMover.moveDown(mazeRunner);
    }
}
