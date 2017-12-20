package maze.mazeElements.monsters;

import maze.Maze;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMonsterFactory implements IMonsterFactory {
    private static final Logger logger = LogManager.getLogger(RandomMonsterFactory.class);
    private static RandomMonsterFactory instance;
    private List<Class<? extends IMonster>> monsterClasses;

    private RandomMonsterFactory() {
        monsterClasses = new ArrayList<Class<? extends IMonster>>();
    }

    public static RandomMonsterFactory getInstance() {
        if (instance == null) {
            instance = new RandomMonsterFactory();
        }
        return instance;
    }

    @Override
    public IMonster generate(Maze maze, int initialHealth) {
        logger.debug("Generating a random monster");
        IMonster generatedMonster = null;
        if (!monsterClasses.isEmpty()) {
            Random rand = new Random();
            Class<? extends IMonster> MonsterType = monsterClasses.get(rand.nextInt(monsterClasses.size()));
            try {
                generatedMonster = MonsterType.newInstance();
                generatedMonster.setMaze(maze);
                logger.debug("Generated a random monster of type {}", MonsterType.getSimpleName());
            } catch (InstantiationException e) {
                logger.error(e.toString());
            } catch (IllegalAccessException e) {
                logger.error(e.toString());
            }
        }
        return generatedMonster;
    }
    @Override
    public void addMonster(Class<? extends IMonster> monster) {
        monsterClasses.add(monster);
        logger.debug("Added monster class {}", monster.getSimpleName());
        logger.debug("Number of available monsters: {}", monsterClasses.size());
    }
}
