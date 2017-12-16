package maze.mazeElements.monsters;

import maze.Maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMonsterFactory implements IMonsterFactory {
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
        IMonster generatedMonster = null;
        if (!monsterClasses.isEmpty()) {
            Random rand = new Random();
            Class<? extends IMonster> MonsterType = monsterClasses.get(rand.nextInt(monsterClasses.size()));
            try {
                generatedMonster = MonsterType.newInstance();
                generatedMonster.setMaze(maze);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return generatedMonster;
    }
    @Override
    public void addMonster(Class<? extends IMonster> monster) {
        monsterClasses.add(monster);
    }
}
