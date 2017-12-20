package maze.mazeElements.bombs;

import maze.IMazeElement;
import maze.Maze;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class IBomb implements IMazeElement {
    protected int width = 1;
    protected int height = 1;
    protected Maze maze = null;
    private List<IBombObserver> observerList;

    public IBomb() {
        observerList = new ArrayList<>();
    }

    private void notifyDestroyed() {
        Iterator<IBombObserver> iterator = observerList.iterator();
        while (iterator.hasNext()) {
            iterator.next().destroy();
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public void destroy() {
        maze.removeElement(this);
        notifyDestroyed();
    }

}
