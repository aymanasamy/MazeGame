package maze.mazeElements.bombs;

import maze.Maze;

public interface IBombFactory {
    IBomb generate(Maze maze);
    void addBomb(Class<? extends IBomb> bomb);
}
