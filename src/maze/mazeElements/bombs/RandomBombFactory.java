package maze.mazeElements.bombs;

import maze.Maze;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBombFactory implements IBombFactory {
    private static final Logger logger = LogManager.getLogger(RandomBombFactory.class);
    private static RandomBombFactory instance;
    private List<Class<? extends IBomb>> bombClasses;

    private RandomBombFactory() {
        bombClasses = new ArrayList<Class<? extends IBomb>>();
    }

    public static RandomBombFactory getInstance() {
        if (instance == null) {
            instance = new RandomBombFactory();
        }
        return instance;
    }

    @Override
    public IBomb generate(Maze maze) {
        logger.debug("Generating a random bomb");
        IBomb generatedBomb = null;
        if (!bombClasses.isEmpty()) {
            Random rand = new Random();
            Class<? extends IBomb> bombType = bombClasses.get(rand.nextInt(bombClasses.size()));
            try {
                generatedBomb = bombType.newInstance();
                generatedBomb.setMaze(maze);
                logger.debug("Generated a bomb of type {}", generatedBomb.getClass().getSimpleName());
            } catch (InstantiationException e) {
                logger.error(e.toString());
            } catch (IllegalAccessException e) {
                logger.error(e.toString());
            }
        }
        return generatedBomb;
    }

    @Override
    public void addBomb(Class<? extends IBomb> bomb) {
        bombClasses.add(bomb);
        logger.debug("Added a bomb of type {}", bomb.getSimpleName());
    }
}
