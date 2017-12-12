package maze.mazeElements.bullets;

import maze.mazeElements.monsters.IMonster;

public interface IBulletsFactory {
    public IBullet generate(int damage);
}
