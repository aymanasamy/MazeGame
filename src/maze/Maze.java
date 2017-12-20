package maze;

import maze.helpingclasses.PointCompare;
import maze.mazeElements.EmptyElement;
import maze.mazeElements.mazeRunner.MazeRunner;
import maze.mazeElements.monsters.IMonster;

import java.awt.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Maze implements Serializable {
    private int width;
    private int height;
    private IMazeElement[][] map;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        map = new IMazeElement[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = new EmptyElement();
            }
        }
    }
    public MazeRunner getMazeRunner(){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (map[i][j] instanceof MazeRunner) {
                    return (MazeRunner) map[i][j];
                }
            }
        }
        return null;
    }
    public int getWidth() { return width; }

    public int getHeight() {
        return height;
    }

    public IMazeElement getElement(Point position) {
        if (isNotInTheMaze(position))
            return null;
        return map[position.x][position.y];
    }

    public void removeElement(IMazeElement element) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (element == map[i][j]) {
                    map[i][j] = new EmptyElement();
                }
            }
        }
    }

    public boolean setElement(Point position, IMazeElement element) {
        if (elementIsNotInTheMaze(position, element))
            return false;
        // Affect all elements in this place
        ArrayList<IMazeElement> affectedElements = new ArrayList<>();
        for (int i = position.x; i < position.x + element.getWidth(); i++) {
            for (int j = position.y; j < position.y + element.getHeight(); j++) {
                IMazeElement elementInPosition = map[i][j];
                if(!affectedElements.contains(elementInPosition)) {
                    element.affect(elementInPosition);
                    elementInPosition.affect(element);
                    affectedElements.add(elementInPosition);
                }
            }
        }
        if (!hasFreeSpace(position, element) || !element.exist())
            return false;
        for (int i = position.x; i < position.x + element.getWidth(); i++) {
            for (int j = position.y; j < position.y + element.getHeight(); j++) {
                map[i][j] = element;
            }
        }
        return true;
    }

    public Point getPosition(IMazeElement element) {
        // Add all the points that have this element
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (element == map[i][j]) {
                    points.add(new Point(i, j));
                }
            }
        }
        // Return the smallest one
        Collections.sort(points, new PointCompare());
        if (points.size() == 0)
            return null;
        else
            return points.get(0);
    }

    private boolean hasFreeSpace(Point position, IMazeElement element) {
        boolean hasFreeSpace = true;
        for (int i = position.x; i < position.x + element.getWidth(); i++) {
            for (int j = position.y; j < position.y + element.getHeight(); j++) {
                if (!(map[i][j] instanceof EmptyElement)) {
                    hasFreeSpace = false;
                    break;
                }
            }
        }
        return hasFreeSpace;
    }

    private boolean elementIsNotInTheMaze(Point position, IMazeElement element) {
        return isNotInTheMaze(position) || isNotInTheMaze(new Point(position.x + element.getWidth()-1, position.y + element.getHeight()-1));
    }

    private boolean isNotInTheMaze(Point position) {
        return position.x >= width || position.x < 0 || position.y < 0 || position.y >= height;
    }
}
