package maze.builders;

import maze.Maze;
import maze.saving.Saver;

public class RoundsMazeBuilder implements IMazeBuilder {
    @Override
    public Maze generate(int level, Difficulty difficulty) {
        // Check for the maze if saved
        // if saved : load it
        // else : Build it using EndlessMazeBuilder then save it
        Saver saver = Saver.getInstance();
        Maze generatedMaze = saver.load(difficulty, level);

        // Not saved?
        if (generatedMaze == null) {
            IMazeBuilder builder = new EndlessMazeBuilder();
            generatedMaze = builder.generate(level, difficulty);
            saver.save(difficulty, level, generatedMaze);
        }

        return generatedMaze;
    }
}
