package maze.testers;

import maze.IMazeElement;
import maze.Maze;
import maze.mazeElements.Direction;
import maze.mazeElements.EmptyElement;
import maze.mazeElements.bombs.IBomb;
import maze.mazeElements.bullets.BulletFactory;
import maze.mazeElements.bullets.GunBullet;
import maze.mazeElements.bullets.IBullet;
import maze.mazeElements.gifts.IGift;
import maze.mazeElements.monsters.IMonster;
import maze.mazeElements.monsters.IMonsterObserver;
import maze.mazeElements.monsters.IMonsterObserver;
import maze.mazeElements.walls.IWall;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GunBulletTester {
    @Test
    public void testMove() {
        // Up
        Maze maze = new Maze(10,10);
        IBullet bullet = BulletFactory.getInstance().generate(maze,1,Direction.Up);
        maze.setElement(new Point(5,5),bullet);
        assertEquals(new Point(5,5),maze.getPosition(bullet));
        bullet.move();
        assertEquals(new Point(5,6),maze.getPosition(bullet));
        bullet.move();
        assertEquals(new Point(5,7),maze.getPosition(bullet));

        // Down
        maze = new Maze(10,10);
        bullet = BulletFactory.getInstance().generate(maze,1,Direction.Down);
        maze.setElement(new Point(5,5),bullet);
        assertEquals(new Point(5,5),maze.getPosition(bullet));
        bullet.move();
        assertEquals(new Point(5,4),maze.getPosition(bullet));
        bullet.move();
        assertEquals(new Point(5,3),maze.getPosition(bullet));

        // Right
        maze = new Maze(10,10);
        bullet = BulletFactory.getInstance().generate(maze,1,Direction.Right);
        maze.setElement(new Point(5,5),bullet);
        assertEquals(new Point(5,5),maze.getPosition(bullet));
        bullet.move();
        assertEquals(new Point(6,5),maze.getPosition(bullet));
        bullet.move();
        assertEquals(new Point(7,5),maze.getPosition(bullet));

        // Left
        maze = new Maze(10,10);
        bullet = BulletFactory.getInstance().generate(maze,1,Direction.Left);
        maze.setElement(new Point(5,5),bullet);
        assertEquals(new Point(5,5),maze.getPosition(bullet));
        bullet.move();
        assertEquals(new Point(4,5),maze.getPosition(bullet));
        bullet.move();
        assertEquals(new Point(3,5),maze.getPosition(bullet));
    }

    @Test
    public void testAffect() {
        // Wall
        Maze maze = new Maze(10,10);
        IBullet bullet = BulletFactory.getInstance().generate(maze,1,Direction.Up);
        IWall wall  = new TestWall();
        wall.setHealth(10);
        assertEquals(10,wall.getHealth());
        bullet.affect(wall);
        assertEquals(9,wall.getHealth());
        bullet.affect(wall);
        assertEquals(8,wall.getHealth());

        // Gift
        maze = new Maze(10,10);
        bullet = BulletFactory.getInstance().generate(maze,1,Direction.Up);
        IGift gift = new TestGift();
        Point position = new Point(0,0);
        maze.setElement(position,gift);
        assertTrue(maze.getElement(position) instanceof IGift);
        bullet.affect(gift);
        assertTrue(maze.getElement(position) instanceof EmptyElement);

        // Bomb
        maze = new Maze(10,10);
        bullet = BulletFactory.getInstance().generate(maze,1,Direction.Up);
        IBomb bomb = new TestBomb();
        position = new Point(0,0);
        maze.setElement(position,bomb);
        assertTrue(maze.getElement(position) instanceof IBomb);
        bullet.affect(bomb);
        assertTrue(maze.getElement(position) instanceof EmptyElement);

        // Monster
        maze = new Maze(10,10);
        bullet = BulletFactory.getInstance().generate(maze,1,Direction.Up);
        IMonster monster = new TestMonster();
        monster.setHealth(10);
        assertEquals(10,monster.getHealth());
        bullet.affect(monster);
        assertEquals(9,monster.getHealth());
        bullet.affect(monster);
        assertEquals(8,monster.getHealth());
    }

    private class TestWall implements IWall {
        private int health;
        private Maze maze;

        @Override
        public int getInitialHealth() {
            return 1;
        }

        @Override
        public boolean isBreakable() {
            return true;
        }

        @Override
        public int getHealth() {
            return health;
        }

        @Override
        public void setHealth(int health) {
            this.health = health;
        }

        @Override
        public int getWidth() {
            return 1;
        }

        @Override
        public int getHeight() {
            return 1;
        }

        @Override
        public void setMaze(Maze maze) {
            this.maze = maze;
        }

        @Override
        public void affect(IMazeElement element) {

        }
    }

    private class TestGift implements IGift{

        private Maze maze;
        @Override
        public void setMaze(Maze maze) {
            this.maze = maze;
        }
        @Override
        public int getWidth() {
            return 1;
        }

        @Override
        public int getHeight() {
            return 1;
        }

        @Override
        public void affect(IMazeElement element) {

        }

        @Override
        public void destroy() {

        }
    }

    private class TestBomb implements IBomb {
        private Maze maze;
        @Override
        public void setMaze(Maze maze) {
            this.maze = maze;
        }
        @Override
        public int getWidth() {
            return 1;
        }

        @Override
        public int getHeight() {
            return 1;
        }

        @Override
        public void affect(IMazeElement element) {

        }

        @Override
        public void destroy() {

        }
    }
    private class TestMonster implements IMonster{
        private int health;

        @Override
        public int getInitialHealth() {
            return 1;
        }

        @Override
        public int getHealth() {
            return health;
        }

        @Override
        public void setHealth(int health) {
            this.health = health;
        }

        @Override
        public void addObserver(IMonsterObserver observer) {

        }

        @Override
        public void removeObserver(IMonsterObserver observer) {

        }

        @Override
        public int getWidth() {
            return 1;
        }

        @Override
        public int getHeight() {
            return 1;
        }

        private Maze maze;
        @Override
        public void setMaze(Maze maze) {
            this.maze = maze;
        }

        @Override
        public void affect(IMazeElement element) {

        }


        @Override
        public Direction getDirection() {
            return null;
        }

        @Override
        public void setDirection(Direction dir) {

        }

        @Override
        public Maze getMaze() {
            return maze;
        }

        @Override
        public void notifyMoveIn(Direction dir) {
            // TODO
        }
    }
}
