package maze.mazeElements.mazeRunner;

import maze.Maze;
import maze.mazeElements.Direction;

import java.awt.*;

public class MazeRunnerPoisionedState implements IMazeRunnerState {
    private MazeRunner mazeRunner;
    public MazeRunnerPoisionedState(MazeRunner mazeRunner){
        this.mazeRunner = mazeRunner;
    }
    @Override
    public void moveRight() {
        MazeRunnerMover.moveLeft(mazeRunner);
    }
    @Override
    public void moveLeft() {
        MazeRunnerMover.moveRight(mazeRunner);
    }

    @Override
    public void moveUp() {
        MazeRunnerMover.moveDown(mazeRunner);
    }

    @Override
    public void moveDown() {
        MazeRunnerMover.moveUp(mazeRunner);
    }
}
