package maze.mazeElements.monsters;

import maze.mazeElements.Direction;

public class SquareMove implements IMove {
    Direction[] movements = {Direction.Up, Direction.Up, Direction.Right, Direction.Right, Direction.Right,
            Direction.Down, Direction.Down, Direction.Down, Direction.Left, Direction.Left, Direction.Left,};
    int count = 0;

    @Override
    public Direction move() {
        count++;
        return movements[count % movements.length];
    }
}
