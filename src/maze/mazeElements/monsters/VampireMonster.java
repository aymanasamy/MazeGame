package maze.mazeElements.monsters;

import maze.IMazeElement;
import maze.Maze;
import maze.mazeElements.Direction;
import maze.mazeElements.mazeRunner.MazeRunner;

import java.awt.*;
import java.util.Random;

public class VampireMonster implements IMonster {

    private static final int width = 1;
    private static final int height = 1;
    private static final int initialHealth = 1;
    private Direction direction;
    private int health = getInitialHealth();
    private Maze maze;

    public VampireMonster(Maze m) {
        maze = m;
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
        }
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
        if (element instanceof MazeRunner) {
            MazeRunner runner = (MazeRunner) element;
            runner.setHealth(runner.getHeight() - 1);
        }
    }

    @Override
    public void move() {
        Random rand = new Random();
        Direction dir = Direction.values()[rand.nextInt(4)];

        // TODO: MOVE MONSTER!
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction dir) {
        direction = dir;
    }
}
