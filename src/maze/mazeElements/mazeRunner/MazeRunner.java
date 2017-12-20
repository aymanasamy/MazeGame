package maze.mazeElements.mazeRunner;

import maze.IMazeElement;
import maze.Maze;
import maze.mazeElements.Direction;
import maze.mazeElements.Directionable;
import maze.mazeElements.Healthable;
import maze.mazeElements.bombs.IBomb;
import maze.mazeElements.bullets.BulletFactory;
import maze.mazeElements.bullets.IBullet;
import maze.mazeElements.bullets.IBulletsFactory;
import maze.mazeElements.gifts.IGift;
import maze.mazeElements.monsters.IMonster;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MazeRunner implements Healthable, Directionable, Serializable {
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
        Iterator<IMazeRunnerObserver> iterator = observerList.iterator();
        while (iterator.hasNext()){
            iterator.next().setLives(getLives());
        }
    }

    private void setLives(int lives) {
        if(lives <= 0) {
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
            ((IBomb) element).destroy();
        }
        else if(element instanceof IGift) {
            ((IGift) element).destroy();
        }
        else if(element instanceof IBullet)
            ((IBullet) element).destroy();
    }

    public void fire() {
        if(bullets <= 0)
            return;
        Iterator<IMazeRunnerObserver> iterator = observerList.iterator();
        while (iterator.hasNext()){
            iterator.next().fire();
        }
        IBulletsFactory bulletsFactory = BulletFactory.getInstance();
        bulletsFactory.generate(maze,bulletDamage,direction);
        setBullets(getBullets()-1);
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
