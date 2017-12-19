package maze.builders;

import maze.Maze;

public class RoundsMazeBuilder implements IMazeBuilder {
    @Override
    public Maze generate(int level, Difficulty difficulty) {
        // Check for the maze if saved
        // if saved : load it
        // else : Build it using EndlessMazeBuilder then save it
        // Todo
        return null;
    }

    @Override
    public IMazeBuilder getInstance() {
        return null;
    }
}
