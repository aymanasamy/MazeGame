package maze.mazeElements.bullets;

import maze.Maze;
import maze.mazeElements.Direction;

public class BulletFactory implements IBulletsFactory {
    private static BulletFactory ourInstance = new BulletFactory();

    public static BulletFactory getInstance() {
        return ourInstance;
    }

    private BulletFactory() { }

    @Override
    public IBullet generate(int damage, Maze maze, Direction direction) {
        if(damage == 1)
            return new GunBullet(maze,direction);
        else
            return null;
    }
}
