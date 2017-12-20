package maze.mazeElements;

import maze.Maze;

import java.awt.*;

public class DirectionableMover {

    public static void moveRight(Directionable element) {
        if(element.getDirection().equals(Direction.Right)) {
            Maze maze = element.getMaze();
            Point intialPosition = maze.getPosition(element);
            Point nextPosition = new Point(intialPosition.x,intialPosition.y+1);
            maze.removeElement(element);
            if(!maze.setElement(nextPosition,element)) {
                maze.setElement(intialPosition,element);
            }
            else {
                element.notifyMoveIn(Direction.Right);
            }
        }
        else
            element.setDirection(Direction.Right);
    }

    public static void moveLeft(Directionable element) {
        if(element.getDirection().equals(Direction.Left)) {
            Maze maze = element.getMaze();
            Point intialPosition = maze.getPosition(element);
            Point nextPosition = new Point(intialPosition.x,intialPosition.y-1);
            maze.removeElement(element);
            if(!maze.setElement(nextPosition,element)) {
                maze.setElement(intialPosition,element);
            }
            else {
                element.notifyMoveIn(Direction.Left);
            }
        }
        else
            element.setDirection(Direction.Left);
    }

    public static void moveUp(Directionable element) {
        if(element.getDirection().equals(Direction.Up)) {
            Maze maze = element.getMaze();
            Point intialPosition = maze.getPosition(element);
            Point nextPosition = new Point(intialPosition.x-1,intialPosition.y);
            maze.removeElement(element);
            if(!maze.setElement(nextPosition,element)) {
                maze.setElement(intialPosition,element);
            }
            else {
                element.notifyMoveIn(Direction.Up);
            }
        }
        else
            element.setDirection(Direction.Up);
    }

    public static void moveDown(Directionable element) {
        if(element.getDirection().equals(Direction.Down)) {
            Maze maze = element.getMaze();
            Point intialPosition = maze.getPosition(element);
            Point nextPosition = new Point(intialPosition.x+1,intialPosition.y);
            maze.removeElement(element);
            if(!maze.setElement(nextPosition,element)) {
                maze.setElement(intialPosition,element);
            }
            else {
                element.notifyMoveIn(Direction.Down);
            }
        }
        else
            element.setDirection(Direction.Down);
    }

    public static void moveInDirection(Directionable element, Direction direction) {
        if(direction.equals(Direction.Right))
            moveRight(element);
        else if(direction.equals(Direction.Left))
            moveLeft(element);
        else if(direction.equals(Direction.Up))
            moveUp(element);
        else if(direction.equals(Direction.Down))
            moveDown(element);
    }
}
