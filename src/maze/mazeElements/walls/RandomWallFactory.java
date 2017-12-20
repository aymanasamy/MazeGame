package maze.mazeElements.walls;

import maze.Maze;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class RandomWallFactory implements IWallFactory {
    private static final Logger logger = LogManager.getLogger(RandomWallFactory.class);
    private static RandomWallFactory instance;
    private Map<Integer, List<Class<? extends IWall>>> breakableWallClasses;
    private Map<Integer, List<Class<? extends IWall>>> unbreakableWallClasses;

    private RandomWallFactory() {
        breakableWallClasses = new HashMap<>();
        unbreakableWallClasses = new HashMap<>();
    }

    public static RandomWallFactory getInstance() {
        if (instance == null) {
            instance = new RandomWallFactory();
        }
        return instance;
    }

    @Override
    public IWall generate(Maze maze, boolean isBreakable, int initialHealth) {
        List<Class<? extends IWall>> wallClasses;
        if (isBreakable) {
            wallClasses = breakableWallClasses.get(initialHealth);
        } else {
            initialHealth = 1;
            wallClasses = unbreakableWallClasses.get(initialHealth);
        }
        IWall generatedWall = null;
        if(wallClasses != null) {
            if (!wallClasses.isEmpty()) {
                Random rand = new Random();
                Class<? extends IWall> WallType = wallClasses.get(rand.nextInt(wallClasses.size()));
                try {
                    generatedWall = WallType.newInstance();
                    generatedWall.setMaze(maze);
                    logger.debug("Generated wall of type {}", WallType.getSimpleName());
                } catch (InstantiationException e) {
                    logger.error(e.toString());
                } catch (IllegalAccessException e) {
                    logger.error(e.toString());
                }
            }
        }
        return generatedWall;
    }

    @Override
    public void addWall(Class<? extends IWall> wall, boolean isBreakable, int initialHealth) {
        Map<Integer, List<Class<? extends IWall>>> wallClasses;
        if (isBreakable) {
            wallClasses = breakableWallClasses;
        } else {
            wallClasses = unbreakableWallClasses;
            initialHealth = 1;
        }

        if (wallClasses.get(initialHealth) == null) {
            wallClasses.put(initialHealth, new ArrayList<Class<? extends IWall>>());
        }
        wallClasses.get(initialHealth).add(wall);
        logger.debug("Added wall type {}", wall.getSimpleName());
        logger.debug("Number of available walls of this type: {}", wallClasses.get(initialHealth).size());
    }
}
