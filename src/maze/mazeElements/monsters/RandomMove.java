package maze.mazeElements.monsters;

import maze.mazeElements.Direction;
import maze.mazeElements.DirectionableMover;

import java.util.Random;

public class RandomMove implements IMove {
    @Override
    public Direction move() {
        Random rand = new Random();
        Direction dir = Direction.values()[rand.nextInt(4)];
        return dir;
    }
}
