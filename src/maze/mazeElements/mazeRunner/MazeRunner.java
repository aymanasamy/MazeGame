package maze.mazeElements.mazeRunner;

import maze.IMazeElement;
import maze.Maze;
import maze.ScoreHandler;
import maze.mazeElements.Direction;
import maze.mazeElements.Directionable;
import maze.mazeElements.Healthable;
import maze.mazeElements.bombs.IBomb;
import maze.mazeElements.bullets.BulletFactory;
import maze.mazeElements.bullets.IBullet;
import maze.mazeElements.bullets.IBulletsFactory;
import maze.mazeElements.gifts.IGift;
import maze.mazeElements.monsters.IMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MazeRunner implements Healthable, Directionable, Serializable {
    private static final Logger logger = LogManager.getLogger(MazeRunner.class);
    private final static int initialLives = 3;
    private final static int initialHealth = 10;
    private final static int initialBullets = 6;
    private final static Direction initialDirection = Direction.Up;
    private final static int bulletDamage = 1;
    private final static int width = 1;
    private final static int height = 1;

    private int health;
    private int lives;
    private int bullets;
    private Maze maze;
    private Direction direction;
    private IMazeRunnerState state;
    private List<IMazeRunnerObserver> observerList;

    public MazeRunner(){
        this.state = new MazeRunnerNormalState(this);
        this.lives = initialLives;
        this.bullets = initialBullets;
        this.health = initialHealth;
        this.direction = initialDirection;
        observerList = new ArrayList<>();
    }
    public void moveRight() {
        state.moveRight();
    }

    public void moveLeft() {
        state.moveLeft();
    }

    public void moveUp() {
        state.moveUp();
    }

    public void moveDown() {
        state.moveDown();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (state instanceof MazeRunnerWinState) {
            return;
        }
        if(health <= 0)
            setLives(getLives()-1);
        else
            this.health = health;
        notifyLives();
    }

    private void notifyLives() {
        Iterator<IMazeRunnerObserver> iterator = observerList.iterator();
        while (iterator.hasNext()){
            iterator.next().setLives(getLives());
        }
    }

    private void setLives(int lives) {
        if(lives <= 0) {
            logger.info("Player died");
            this.lives = 0;
            maze.removeElement(this);
            Iterator<IMazeRunnerObserver> iterator = observerList.iterator();
            while (iterator.hasNext()){
                iterator.next().destroy();
            }
        }
        else {
            this.lives = lives;
            Iterator<IMazeRunnerObserver> iterator = observerList.iterator();
            while (iterator.hasNext()){
                iterator.next().setLives(getLives());
            }
        }

        ScoreHandler.setRemainingLives(this.lives);
    }

    public int getLives() {
        return lives;
    }

    public int getBullets() {
        return bullets;
    }

    public void setBullets(int bullets) {
        if(bullets <= 0)
            this.bullets = 0;
        else
            this.bullets = bullets;
        logger.info("Player has {} bullets", this.bullets);
        Iterator<IMazeRunnerObserver> iterator = observerList.iterator();
        while (iterator.hasNext()){
            iterator.next().setBullets(getBullets());
        }
    }
    @Override
    public boolean exist() {
        return getLives() > 0;
    }
    public IMazeRunnerState getState() {
        return state;
    }

    public void setState(IMazeRunnerState state) {
        logger.debug("Player state set to {}", state.getClass().getSimpleName());
        if (state instanceof MazeRunnerWinState) {
            logger.info("Player won");
        }
        this.state = state;
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
        if(element instanceof IMonster) {
            ((IMonster) element).setHealth(0);
        }
        else if(element instanceof IBomb) {
            logger.info("Player touched {}", element.getClass().getSimpleName());
            ((IBomb) element).destroy();
        }
        else if(element instanceof IGift) {
            logger.info("Player touched {}", element.getClass().getSimpleName());
            ((IGift) element).destroy();
        }
        else if(element instanceof IBullet) {
            logger.info("Player touched {}", element.getClass().getSimpleName());
            ((IBullet) element).destroy();
        }
    }

    public void fire() {
        if(bullets <= 0)
            return;
        IBulletsFactory bulletsFactory = BulletFactory.getInstance();
        logger.info("Firing a bullet of {} damage in {} direction", bulletDamage, direction.toString());
        IBullet bullet = bulletsFactory.generate(maze,bulletDamage,direction);
        Point bulletPos = getBulletPos();
        maze.setElement(bulletPos,bullet);
        setBullets(getBullets()-1);
        while (bullet.exist()) {
            bullet.move();
        }
        Iterator<IMazeRunnerObserver> iterator = observerList.iterator();
        while (iterator.hasNext()){
            iterator.next().fire();
        }
    }

    private Point getBulletPos() {
        Point bulletPos = new Point(maze.getPosition(this));
        if(direction.equals(Direction.Right))
            bulletPos.y++;
        else if(direction.equals(Direction.Left))
            bulletPos.y--;
        else if(direction.equals(Direction.Down))
            bulletPos.x++;
        else if(direction.equals(Direction.Up))
            bulletPos.x--;

        return bulletPos;
    }

    public Maze getMaze() {
        return maze;
    }

    @Override
    public void notifyMoveIn(Direction dir) {
        Iterator<IMazeRunnerObserver> iterator = observerList.iterator();
        while (iterator.hasNext()){
            iterator.next().moveIn(direction);
        }
    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        Iterator<IMazeRunnerObserver> iterator = observerList.iterator();
        while (iterator.hasNext()){
            iterator.next().setDirection(direction);
        }
    }

    public void addObserver(IMazeRunnerObserver observer){
        observerList.add(observer);
    }

    public void removeObserver(IMazeRunnerObserver observer){
        observerList.remove(observer);
    }
}
