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
    public IBullet generate(Maze maze, int damage, Direction direction) {
        IBullet bullet;
        if(damage == 1)
            bullet = new GunBullet();
        else
            return null;
        bullet.setDirection(direction);
        bullet.setMaze(maze);
        return bullet;
    }
}
