package maze.mazeElements.monsters;

import maze.IMazeElement;
import maze.Maze;
import maze.ScoreHandler;
import maze.mazeElements.Direction;
import maze.mazeElements.DirectionableMover;
import maze.mazeElements.Moveable;
import maze.mazeElements.bullets.IBullet;
import maze.mazeElements.mazeRunner.MazeRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class VampireMonster implements IMonster {

    private static final Logger logger = LogManager.getLogger(VampireMonster.class);

    private static final int width = 1;
    private static final int height = 1;
    private static final int initialHealth = 1;
    private static final int MONSTER_KILL_REWARD = 3;

    private Direction direction;
    private int health = getInitialHealth();
    private Maze maze;
    private List<IMonsterObserver> observerList;
    private IMove iMove;

    public VampireMonster() {
        logger.debug("Created VampireMonster");
        observerList = new ArrayList<>();
        direction = Direction.Up; // Initial direction
        iMove = new RandomMove();
    }

    public int getInitialHealth() {
        return initialHealth;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
        if (this.health <= 0) {
            logger.debug("Monster {} of type VampireMonster died", this.hashCode());
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
        logger.debug("Adding observer {}", observer.toString());
        observerList.add(observer);
    }

    @Override
    public void removeObserver(IMonsterObserver observer) {
        logger.debug("Removing observer {}", observer.toString());
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
    public boolean exist() {
        return getHealth() > 0;
    }

    @Override
    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void affect(IMazeElement element) {
        if (element instanceof MazeRunner) {
            logger.info("MazeRunner touched VampireMonster");
            MazeRunner runner = (MazeRunner) element;
            runner.setHealth(runner.getHealth() - 1);

            Iterator<IMonsterObserver> iterator = observerList.iterator();
            while (iterator.hasNext()){
                iterator.next().affect();
            }
        }
        if(element instanceof IBullet) {
            ((IBullet) element).destroy();
            ScoreHandler.addToScore(MONSTER_KILL_REWARD);
            ScoreHandler.incrementKilledMonsters();
        }
    }

    @Override
    public void move() {
        Direction dir = iMove.move();
        DirectionableMover.moveInDirection(this,dir);
        logger.debug("VampireMonster {} moved to the {}", this.hashCode(), dir.toString());
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
        logger.debug("Notifying observers that {} moved to the {}", this.hashCode(), dir.toString());
        Iterator<IMonsterObserver> iterator = observerList.iterator();
        while (iterator.hasNext()){
            iterator.next().moveIn(dir);
        }
    }
}
