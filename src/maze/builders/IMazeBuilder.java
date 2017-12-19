package maze.builders;

import maze.Maze;

public interface IMazeBuilder {
    Maze generate(int level, Difficulty difficulty);
}
