package maze.testers;

import maze.IMazeElement;
import maze.Maze;
import maze.mazeElements.EmptyElement;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MazeTester {
    @Test
    public void testgetElement() {
        Maze maze = new Maze(0,0);
        for(int i = -10;i < 10;i++) {
            for(int j = -10;j < 10;j++) {
                assertEquals(null,maze.getElement(new Point(i,j)));
            }
        }

        maze = new Maze(10,0);
        for(int i = -10;i < 10;i++) {
            for(int j = -10;j < 10;j++) {
                assertEquals(null,maze.getElement(new Point(i,j)));
            }
        }

        maze = new Maze(10,10);
        for(int i = -10;i < 0;i++) {
            for(int j = -10;j < 0;j++) {
                assertEquals(null,maze.getElement(new Point(i,j)));
            }
        }
        for(int i = 0;i < 10;i++) {
            for(int j = 0;j < 10;j++) {
                IMazeElement element = maze.getElement(new Point(i,j));
                assertTrue(element instanceof EmptyElement);
            }
        }
        for(int i = 10;i < 20;i++) {
            for(int j = 20;j < 20;j++) {
                assertEquals(null,maze.getElement(new Point(i,j)));
            }
        }
    }

    @Test
    public void testsetElement() {
        Maze maze = new Maze(0,0);
        for(int i = -10;i < 10;i++) {
            for(int j = -10;j < 10;j++) {
                for(int k = 0;k < 10;k++) {
                    assertFalse(maze.setElement(new Point(i,j),new TestMazeElement(k,k)));
                }
            }
        }

        maze = new Maze(10,0);
        for(int i = -10;i < 10;i++) {
            for(int j = -10;j < 10;j++) {
                for(int k = 0;k < 10;k++) {
                    assertFalse(maze.setElement(new Point(i,j),new TestMazeElement(k,k)));
                }
            }
        }

        maze = new Maze(10,10);
        for(int i = -10;i < 10;i++) {
            for(int j = -10;j < 0;j++) {
                for(int k = 0;k < 0;k++) {
                    assertFalse(maze.setElement(new Point(i,j),new TestMazeElement(k,k)));
                }
            }
        }
        for(int i = 0;i < 9;i++) {
            for (int j = 0; j < 9; j++) {
                IMazeElement element = new TestMazeElement(1,1);
                Point point = new Point(i,j);
                assertTrue(maze.setElement(point,element));
                assertEquals(element,maze.getElement(point));
            }
        }
        for(int i = 9;i < 19;i++) {
            for (int j = 9; j < 19; j++) {
                assertFalse(maze.setElement(new Point(i,j),new TestMazeElement(1,1)));
            }
        }
    }

    @Test
    public void testgetPosition() {
        Maze maze = new Maze(10,10);
        for(int i = 0;i < 9;i++) {
            for (int j = 0; j < 9; j++) {
                IMazeElement element = new TestMazeElement(1,1);
                Point point = new Point(i,j);
                maze.setElement(point,element);
                assertEquals(point,maze.getPosition(element));
            }
        }
        for(int i = 0;i < 9;i++) {
            for (int j = 0; j < 9; j++) {
                for(int k = 1;k+i < 10;k++) {
                    for(int h = 1;h+j < 10;h++) {
                        maze = new Maze(10,10);
                        IMazeElement element = new TestMazeElement(k,h);
                        Point point = new Point(i,j);
                        maze.setElement(point,element);
                        assertEquals(point,maze.getPosition(element));
                    }
                }
            }
        }
    }

    private class TestMazeElement implements IMazeElement {
        private int width;
        private int height;
        @Override
        public int getWidth() {
            return width;
        }

        @Override
        public int getHeight() {
            return height;
        }

        private Maze maze;
        @Override
        public void setMaze(Maze maze) {
            this.maze = maze;
        }

        public TestMazeElement(int width,int height) {
            this.width = width;
            this.height = height;
        }
        @Override
        public void affect(IMazeElement element) {

        }
    }
}
