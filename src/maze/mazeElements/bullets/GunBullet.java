package maze.mazeElements.bullets;

import maze.IMazeElement;
import maze.mazeElements.Movable;

public class GunBullet implements IBullet, Movable {
    public static int getDamage() {
        return 1;
    }
    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void affect(IMazeElement element) {

    }

    @Override
    public void move() {

    }
}
