package maze.testers;

import maze.Maze;
import maze.builders.Difficulty;
import maze.builders.EndlessMazeBuilder;
import maze.builders.IMazeBuilder;
import maze.mazeElements.bombs.DynamiteBomb;
import maze.mazeElements.bombs.RandomBombFactory;
import maze.mazeElements.gifts.AmmoGift;
import maze.mazeElements.gifts.RandomGiftFactory;
import maze.mazeElements.mazeRunner.MazeRunner;
import maze.mazeElements.monsters.RandomMonsterFactory;
import maze.mazeElements.monsters.VampireMonster;
import maze.mazeElements.walls.RandomWallFactory;
import maze.mazeElements.walls.UnbreakableWall;
import maze.mazeElements.walls.WoodWall;
import maze.saving.Saver;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class SaverTest {
    static void assertMazeRunnerEquals(MazeRunner mr1, MazeRunner mr2) {
        assertEquals(mr1.getBullets(), mr2.getBullets());
        assertEquals(mr1.getHeight(), mr2.getHeight());
        assertEquals(mr1.getWidth(), mr2.getWidth());
        assertEquals(mr1.getLives(), mr2.getLives());
        assertEquals(mr1.getDirection(), mr2.getDirection());
        assertEquals(mr1.getHealth(), mr2.getHealth());
        assertEquals(mr1.getState().getClass(), mr2.getState().getClass());
    }

    static void assertMazeEquals(Maze m1, Maze m2) {
        assertEquals(m1.getHeight(), m2.getHeight());
        assertEquals(m1.getWidth(), m2.getWidth());
        assertMazeRunnerEquals(m1.getMazeRunner(), m2.getMazeRunner());
        assertEquals(m1.getPosition(m1.getMazeRunner()), m2.getPosition(m2.getMazeRunner()));
        // TODO (FOR MORE COMPREHENSIVE TESTING): Test equality of IMazeElements
    }

    @Test
    public void testMainFunctionality() {
        Saver saver = Saver.getInstance();

        RandomBombFactory.getInstance().addBomb(DynamiteBomb.class);
        RandomGiftFactory.getInstance().addGift(AmmoGift.class);
        RandomMonsterFactory.getInstance().addMonster(VampireMonster.class);
        RandomWallFactory.getInstance().addWall(UnbreakableWall.class,false,1);
        RandomWallFactory.getInstance().addWall(WoodWall.class,true,1);

        IMazeBuilder builder = new EndlessMazeBuilder();

        // Loading unsaved game
        System.out.println("Loading unsaved game...");
        new File(String.format("%d-%s.maze", 5, Difficulty.Easy.toString())).delete();
        Maze loadedMaze = saver.load(Difficulty.Easy, 5);
        assertNull(loadedMaze);

        // Loading saved game (level: 1, difficulty: easy)
        System.out.println("Generating new maze...");
        Maze generatedMaze = builder.generate(5, Difficulty.Easy);
        System.out.println("Saving generated maze...");
        saver.save(Difficulty.Easy, 5, generatedMaze);
        System.out.println("Reloading maze...");
        loadedMaze = saver.load(Difficulty.Easy, 5);
        System.out.println("Checking loaded maze...");
        assertMazeEquals(loadedMaze, generatedMaze);

        // TODO: Add more tests
    }
}