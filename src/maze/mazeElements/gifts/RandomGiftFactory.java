package maze.mazeElements.gifts;

import maze.Maze;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGiftFactory implements IGiftFactory {
    private static final Logger logger = LogManager.getLogger(RandomGiftFactory.class);
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
        logger.debug("Generating a random gift");
        IGift generatedGift = null;
        if (!giftClasses.isEmpty()) {
            Random rand = new Random();
            Class<? extends IGift> GiftType = giftClasses.get(rand.nextInt(giftClasses.size()));
            try {
                generatedGift = GiftType.newInstance();
                generatedGift.setMaze(maze);
                logger.debug("Generated a gift of type {}", generatedGift.getClass().getSimpleName());
            } catch (InstantiationException e) {
                logger.error(e.toString());
            } catch (IllegalAccessException e) {
                logger.error(e.toString());
            }
        }
        return generatedGift;
    }

    @Override
    public void addGift(Class<? extends IGift> gift) {
        giftClasses.add(gift);
        logger.debug("Added a gift of type {} to the factory", gift.getSimpleName());
    }
}
