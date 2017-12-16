package maze.mazeElements.walls;

import java.util.*;

public class RandomWallFactory implements IWallFactory {
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
    public IWall generate(boolean isBreakable, int initialHealth) {
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
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
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
    }
}
