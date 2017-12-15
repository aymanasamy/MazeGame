package maze.mazeElements.bombs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBombFactory implements IBombFactory {
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
    public IBomb generate() {
        IBomb generatedBomb = null;
        if (!bombClasses.isEmpty()) {
            Random rand = new Random();
            Class<? extends IBomb> bombType = bombClasses.get(rand.nextInt(bombClasses.size()));
            try {
                generatedBomb = bombType.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return generatedBomb;
    }

    @Override
    public void addBomb(Class<? extends IBomb> bomb) {
        bombClasses.add(bomb);
    }
}
