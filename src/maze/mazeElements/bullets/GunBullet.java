package maze.mazeElements.bullets;

import maze.IMazeElement;
import maze.Maze;
import maze.mazeElements.Direction;
import maze.mazeElements.Healthable;
import maze.mazeElements.Moveable;
import maze.mazeElements.bombs.IBomb;
import maze.mazeElements.gifts.IGift;
import maze.mazeElements.mazeRunner.MazeRunner;
import maze.mazeElements.monsters.IMonster;
import maze.mazeElements.walls.IWall;

import java.awt.*;

public class GunBullet extends IBullet {
    @Override
    public void affect(IMazeElement element) {
        if (element instanceof Healthable)
            ((Healthable) element).setHealth(((Healthable) element).getHealth() - damage);
        else if (element instanceof IBomb)
            ((IBomb) element).destroy();
        else if (element instanceof IGift)
            ((IGift) element).destroy();
    }
}
