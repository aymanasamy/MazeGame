package maze.mazeElements.bullets;

import maze.IMazeElement;
import maze.Maze;
import maze.mazeElements.Direction;
import maze.mazeElements.Directionable;
import maze.mazeElements.Healthable;
import maze.mazeElements.Moveable;
import maze.mazeElements.bombs.IBomb;
import maze.mazeElements.bombs.IBombObserver;
import maze.mazeElements.gifts.IGift;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class IBullet implements IMazeElement, Moveable, Directionable {
    protected int width = 1;
    protected int height = 1;
    protected int damage = 1;
    protected Direction direction;
    protected Maze maze;
    private List<IBulletObserver> observerList;

    public IBullet() {
        observerList = new ArrayList<>();
    }

    private void notifyDestroyed() {
        Iterator<IBulletObserver> iterator = observerList.iterator();
        while (iterator.hasNext()) {
            iterator.next().destroy();
        }
    }

    public int getDamage() {
        return damage;
    }

    public void destroy() {
        maze.removeElement(this);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void move() {
        Point initialPosition = maze.getPosition(this);
        maze.removeElement(this);
        if (direction.equals(Direction.Right)) {
            Point nextPosition = new Point(initialPosition.x + 1, initialPosition.y);
            maze.setElement(nextPosition, this);
        } else if (direction.equals(Direction.Left)) {
            Point nextPosition = new Point(initialPosition.x - 1, initialPosition.y);
            maze.setElement(nextPosition, this);
        } else if (direction.equals(Direction.Up)) {
            Point nextPosition = new Point(initialPosition.x, initialPosition.y + 1);
            maze.setElement(nextPosition, this);
        } else if (direction.equals(Direction.Down)) {
            Point nextPosition = new Point(initialPosition.x, initialPosition.y - 1);
            maze.setElement(nextPosition, this);
        }
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Maze getMaze() {
        return maze;
    }

    @Override
    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public void notifyMoveIn(Direction dir) {
        Iterator<IBulletObserver> iterator = observerList.iterator();
        while (iterator.hasNext()) {
            iterator.next().moveIn(dir);
        }
    }
}
