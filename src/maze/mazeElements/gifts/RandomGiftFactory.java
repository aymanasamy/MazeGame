package maze.mazeElements.gifts;

import maze.Maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGiftFactory implements IGiftFactory {
    private static RandomGiftFactory instance;
    private List<Class<? extends IGift>> giftClasses;

    private RandomGiftFactory() {
        giftClasses = new ArrayList<Class<? extends IGift>>();
    }

    public static RandomGiftFactory getInstance() {
        if (instance == null) {
            instance = new RandomGiftFactory();
        }
        return instance;
    }

    @Override
    public IGift generate(Maze maze) {
        IGift generatedGift = null;
        if (!giftClasses.isEmpty()) {
            Random rand = new Random();
            Class<? extends IGift> GiftType = giftClasses.get(rand.nextInt(giftClasses.size()));
            try {
                generatedGift = GiftType.newInstance();
                generatedGift.setMaze(maze);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return generatedGift;
    }

    @Override
    public void addGift(Class<? extends IGift> gift) {
        giftClasses.add(gift);
    }
}
