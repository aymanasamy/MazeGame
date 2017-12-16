package maze.mazeElements.bullets;

import maze.IMazeElement;
import maze.Maze;
import maze.mazeElements.Direction;
import maze.mazeElements.Directionable;
import maze.mazeElements.bombs.IBomb;
import maze.mazeElements.gifts.IGift;
import maze.mazeElements.monsters.IMonster;
import maze.mazeElements.walls.IWall;

import java.awt.*;

public class GunBullet implements IBullet, Directionable {

    private final int width = 1;
    private final int height = 1;
    private final int damage = 1;

    private Direction direction;
    private Maze maze;

    public GunBullet(Maze maze,Direction direction) {
        this.maze = maze;
        this.direction = direction;
    }

    @Override
    public int getDamage() {
        return damage;
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
    public void affect(IMazeElement element) {
        if(element instanceof IMonster)
            ((IMonster) element).setHealth(((IMonster) element).getHealth()-damage);
        else if(element instanceof IWall)
            ((IWall) element).setHealth(((IWall) element).getHealth()-damage);
        else if(element instanceof IBomb)
            maze.removeElement(element);
        else if(element instanceof IGift)
            maze.removeElement(element);
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
