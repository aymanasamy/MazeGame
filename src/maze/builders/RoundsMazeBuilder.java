package maze.builders;

import maze.Maze;
import maze.saving.Saver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoundsMazeBuilder implements IMazeBuilder {
    private static final Logger logger = LogManager.getLogger(RoundsMazeBuilder.class);

    @Override
    public Maze generate(int level, Difficulty difficulty) {
        logger.info("Generating a rounds maze with a difficulty of {} and level {}",
                difficulty.toString(), level);
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
