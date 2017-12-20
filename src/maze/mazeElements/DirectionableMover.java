package maze.mazeElements;

import maze.Maze;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

public class DirectionableMover {

    private static final Logger logger = LogManager.getLogger(DirectionableMover.class);

    public static void moveRight(Directionable element) {
        logger.debug("Trying to move {} to the right", element.getClass().getSimpleName());
        if(element.getDirection().equals(Direction.Right)) {
            Maze maze = element.getMaze();
            Point intialPosition = maze.getPosition(element);
            Point nextPosition = new Point(intialPosition.x,intialPosition.y+1);
            maze.removeElement(element);
            if(!maze.setElement(nextPosition,element)) {
                maze.setElement(intialPosition,element);
                logger.debug("Movement unsuccessful");
            }
            else {
                logger.debug("Movement successful");
                element.notifyMoveIn(Direction.Right);
            }
        }
        else {
            element.setDirection(Direction.Right);
            logger.debug("Direction set to right instead");
        }
    }

    public static void moveLeft(Directionable element) {
        logger.debug("Trying to move {} to the left", element.getClass().getSimpleName());
        if(element.getDirection().equals(Direction.Left)) {
            Maze maze = element.getMaze();
            Point intialPosition = maze.getPosition(element);
            Point nextPosition = new Point(intialPosition.x,intialPosition.y-1);
            maze.removeElement(element);
            if(!maze.setElement(nextPosition,element)) {
                maze.setElement(intialPosition,element);
                logger.debug("Movement unsuccessful");
            }
            else {
                logger.debug("Movement successful");
                element.notifyMoveIn(Direction.Left);
            }
        }
        else
            element.setDirection(Direction.Left);
        logger.debug("Direction set to left instead");
    }

    public static void moveUp(Directionable element) {
        logger.debug("Trying to move {} up", element.getClass().getSimpleName());
        if(element.getDirection().equals(Direction.Up)) {
            Maze maze = element.getMaze();
            Point intialPosition = maze.getPosition(element);
            Point nextPosition = new Point(intialPosition.x-1,intialPosition.y);
            maze.removeElement(element);
            if(!maze.setElement(nextPosition,element)) {
                maze.setElement(intialPosition,element);
                logger.debug("Movement unsuccessful");
            }
            else {
                logger.debug("Movement successful");
                element.notifyMoveIn(Direction.Up);
            }
        }
        else
            element.setDirection(Direction.Up);
            logger.debug("Direction set to up instead");
    }

    public static void moveDown(Directionable element) {
        logger.debug("Trying to move {} down", element.getClass().getSimpleName());
        if(element.getDirection().equals(Direction.Down)) {
            Maze maze = element.getMaze();
            Point intialPosition = maze.getPosition(element);
            Point nextPosition = new Point(intialPosition.x+1,intialPosition.y);
            maze.removeElement(element);
            if(!maze.setElement(nextPosition,element)) {
                maze.setElement(intialPosition,element);
                logger.debug("Movement unsuccessful");
            }
            else {
                logger.debug("Movement successful");
                element.notifyMoveIn(Direction.Down);
            }
        }
        else
            element.setDirection(Direction.Down);
            logger.debug("Direction set to down instead");
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
