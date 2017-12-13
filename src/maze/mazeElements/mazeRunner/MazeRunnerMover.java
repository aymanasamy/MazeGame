package maze.mazeElements.mazeRunner;

import maze.Maze;
import maze.mazeElements.Direction;

import java.awt.*;

public class MazeRunnerMover {

    public static void moveRight(MazeRunner mazeRunner) {
        if(mazeRunner.getDirection().equals(Direction.Right)) {
            Maze maze = mazeRunner.getMaze();
            Point intialPosition = maze.getPosition(mazeRunner);
            Point nextPosition = new Point(intialPosition.x+1,intialPosition.y);
            maze.removeElement(mazeRunner);
            if(!maze.setElement(nextPosition,mazeRunner)) {
                maze.setElement(intialPosition,mazeRunner);
            }
        }
        else
            mazeRunner.setDirection(Direction.Right);
    }

    public static void moveLeft(MazeRunner mazeRunner) {
        if(mazeRunner.getDirection().equals(Direction.Left)) {
            Maze maze = mazeRunner.getMaze();
            Point intialPosition = maze.getPosition(mazeRunner);
            Point nextPosition = new Point(intialPosition.x-1,intialPosition.y);
            maze.removeElement(mazeRunner);
            if(!maze.setElement(nextPosition,mazeRunner)) {
                maze.setElement(intialPosition,mazeRunner);
            }
        }
        else
            mazeRunner.setDirection(Direction.Left);
    }

    public static void moveUp(MazeRunner mazeRunner) {
        if(mazeRunner.getDirection().equals(Direction.Up)) {
            Maze maze = mazeRunner.getMaze();
            Point intialPosition = maze.getPosition(mazeRunner);
            Point nextPosition = new Point(intialPosition.x,intialPosition.y+1);
            maze.removeElement(mazeRunner);
            if(!maze.setElement(nextPosition,mazeRunner)) {
                maze.setElement(intialPosition,mazeRunner);
            }
        }
        else
            mazeRunner.setDirection(Direction.Up);
    }

    public static void moveDown(MazeRunner mazeRunner) {
        if(mazeRunner.getDirection().equals(Direction.Up)) {
            Maze maze = mazeRunner.getMaze();
            Point intialPosition = maze.getPosition(mazeRunner);
            Point nextPosition = new Point(intialPosition.x,intialPosition.y-1);
            maze.removeElement(mazeRunner);
            if(!maze.setElement(nextPosition,mazeRunner)) {
                maze.setElement(intialPosition,mazeRunner);
            }
        }
        else
            mazeRunner.setDirection(Direction.Up);
    }
}
