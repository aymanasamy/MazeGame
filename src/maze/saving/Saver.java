package maze.saving;

import maze.Maze;
import maze.builders.Difficulty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class Saver {
    private static Saver instance;
    private static final Logger logger = LogManager.getLogger(Saver.class);

    private Saver() {}

    public static Saver getInstance() {
        if (instance == null) {
            instance = new Saver();
        }
        return instance;
    }

    public void save(Difficulty difficulty,int level,Maze maze) {
        logger.debug("Saving maze with difficulty {} and level {}", difficulty.toString(), level);
        GameState currentState = new GameState(level, difficulty, maze);
        try {
            FileOutputStream fos = new FileOutputStream(
                    String.format("%d-%s.maze", level, difficulty.toString()));
            logger.debug("Saving to {}", String.format("%d-%s.maze", level, difficulty.toString()));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(currentState);
            oos.close();
        } catch (java.io.IOException e) {
            logger.error(e.toString());
        }
    }

    public Maze load(Difficulty difficulty,int level) {
        Maze loadedMaze = null;

        if (isSaved(difficulty, level)) {
            logger.debug("Loading maze from {}-{}.maze", difficulty.toString(), level);
            try {
                FileInputStream fis = new FileInputStream(
                        String.format("%d-%s.maze", level, difficulty.toString()));
                ObjectInputStream ois = new ObjectInputStream(fis);
                GameState loadedState = (GameState) ois.readObject();
                ois.close();
                if (loadedState.getLevel() == level && loadedState.getDifficulty() == difficulty) {
                    loadedMaze = loadedState.getMaze();
                }
            } catch (IOException e) {
                logger.error(e.toString());
            } catch (ClassNotFoundException e) {
                logger.error(e.toString());
            }
        }

        return loadedMaze;
    }

    public boolean isSaved(Difficulty difficulty,int level){
        File file = new File(
                String.format("%d-%s.maze", level, difficulty.toString()));
        return file.exists();
    }
}
