package maze.mazeElements.mazeRunner;

import maze.IMazeElement;
import maze.Maze;
import maze.mazeElements.Direction;
import maze.mazeElements.Healthable;
import maze.mazeElements.bombs.IBomb;
import maze.mazeElements.bullets.BulletFactory;
import maze.mazeElements.bullets.IBulletsFactory;
import maze.mazeElements.gifts.IGift;
import maze.mazeElements.monsters.IMonster;

public class MazeRunner implements IMazeElement, Healthable {
    private int health;
    private int lives;
    private int bullets;
    private Maze maze;
    private Direction direction;
    private IMazeRunnerState state;

    private final static int initialLives = 3;
    private final static int initialHealth = 10;
    private final static int initialBullets = 6;
    private final static Direction initialDirection = Direction.Up;
    private final static int bulletDamage = 1;

    private final static int width = 1;
    private final static int height = 1;

    public MazeRunner(IMazeRunnerState state,Maze maze){
        this.state = state;
        this.maze = maze;
        this.lives = initialLives;
        this.bullets = initialBullets;
        this.health = initialHealth;
        this.direction = initialDirection;
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
        if(health <= 0)
            setLives(getLives()-1);
        else
            this.health = health;
    }

    public void setLives(int lives) {
        if(lives <= 0)
            this.lives = 0;
        else
            this.lives = lives;
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
    public void affect(IMazeElement element) {
        if(element instanceof IMonster) {
            ((IMonster) element).setHealth(0);
            maze.removeElement(element);
        }
        else if(element instanceof IBomb)
            maze.removeElement(element);
        else if(element instanceof IGift)
            maze.removeElement(element);
    }

    public void fire() {
        if(bullets <= 0)
            return;
        IBulletsFactory bulletsFactory = BulletFactory.getInstance();
        bulletsFactory.generate(bulletDamage,maze,direction);
    }

    public Maze getMaze() {
        return maze;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
