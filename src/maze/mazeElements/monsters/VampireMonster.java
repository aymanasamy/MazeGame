package maze.mazeElements.monsters;

import maze.IMazeElement;
import maze.Maze;
import maze.mazeElements.Direction;
import maze.mazeElements.DirectionableMover;
import maze.mazeElements.Moveable;
import maze.mazeElements.mazeRunner.MazeRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class VampireMonster implements IMonster, Moveable {

    private static final int width = 1;
    private static final int height = 1;
    private static final int initialHealth = 1;

    private Direction direction;
    private int health = getInitialHealth();
    private Maze maze;
    private List<IMonsterObserver> observerList;

    public VampireMonster() {
        observerList = new ArrayList<>();
        direction = Direction.Up; // Initial direction
    }

    public static int getInitialHealth() {
        return initialHealth;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
        if (this.health < 0) {
            health = 0;
            maze.removeElement(this);
            Iterator<IMonsterObserver> iterator = observerList.iterator();
            while (iterator.hasNext()){
                iterator.next().destroy();
            }
        }
        else {
            Iterator<IMonsterObserver> iterator = observerList.iterator();
            while (iterator.hasNext()){
                iterator.next().setHealth(getHealth());
            }
        }
    }

    @Override
    public void addObserver(IMonsterObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(IMonsterObserver observer) {
        observerList.remove(observer);
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
        if (element instanceof MazeRunner) {
            MazeRunner runner = (MazeRunner) element;
            runner.setHealth(runner.getHeight() - 1);
            Iterator<IMonsterObserver> iterator = observerList.iterator();
            while (iterator.hasNext()){
                iterator.next().affect();
            }
        }
    }

    @Override
    public void move() {
        Random rand = new Random();
        Direction dir = Direction.values()[rand.nextInt(4)];
        DirectionableMover.moveInDirection(this,dir);
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction dir) {
        direction = dir;
        Iterator<IMonsterObserver> iterator = observerList.iterator();
        while (iterator.hasNext()){
            iterator.next().setDirection(dir);
        }
    }

    @Override
    public Maze getMaze() {
        return maze;
    }

    @Override
    public void notifyMoveIn(Direction dir) {
        Iterator<IMonsterObserver> iterator = observerList.iterator();
        while (iterator.hasNext()){
            iterator.next().moveIn(dir);
        }
    }
}
