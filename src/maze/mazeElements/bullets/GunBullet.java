package maze.mazeElements.bullets;

import maze.IMazeElement;
import maze.Maze;
import maze.mazeElements.Direction;
import maze.mazeElements.Healthable;
import maze.mazeElements.Moveable;
import maze.mazeElements.bombs.IBomb;
import maze.mazeElements.gifts.IGift;
import maze.mazeElements.mazeRunner.MazeRunner;
import maze.mazeElements.monsters.IMonster;
import maze.mazeElements.walls.IWall;

import java.awt.*;

public class GunBullet implements IBullet, Moveable {

    private final int width = 1;
    private final int height = 1;
    private final int damage = 1;

    private Direction direction;
    private Maze maze;

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void destroy() {
        maze.removeElement(this);
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
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
    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void affect(IMazeElement element) {
        if(element instanceof Healthable)
            ((Healthable) element).setHealth(((Healthable) element).getHealth()-damage);
        else if(element instanceof IBomb)
            ((IBomb) element).destroy();
        else if(element instanceof IGift)
            ((IGift) element).destroy();
    }

    @Override
    public void move() {
        Point initialPosition = maze.getPosition(this);
        maze.removeElement(this);
        if(direction.equals(Direction.Right)) {
            Point nextPosition = new Point(initialPosition.x+1,initialPosition.y);
            maze.setElement(nextPosition,this);
        }
        else if(direction.equals(Direction.Left)) {
            Point nextPosition = new Point(initialPosition.x-1,initialPosition.y);
            maze.setElement(nextPosition,this);
        }
        else if(direction.equals(Direction.Up)) {
            Point nextPosition = new Point(initialPosition.x,initialPosition.y+1);
            maze.setElement(nextPosition,this);
        }
        else if(direction.equals(Direction.Down)) {
            Point nextPosition = new Point(initialPosition.x,initialPosition.y-1);
            maze.setElement(nextPosition,this);
        }
    }
}
