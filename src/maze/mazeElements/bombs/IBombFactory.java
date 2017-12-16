package maze.mazeElements.bombs;

import maze.Maze;

public interface IBombFactory {
    public IBomb generate(Maze maze);

    public void addBomb(Class<? extends IBomb> bomb);

}
