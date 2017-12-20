package maze.testers;

import maze.IMazeElement;
import maze.Maze;
import maze.builders.Difficulty;
import maze.builders.EndlessMazeBuilder;
import maze.builders.IMazeBuilder;
import maze.mazeElements.EmptyElement;
import maze.mazeElements.bombs.DynamiteBomb;
import maze.mazeElements.bombs.IBomb;
import maze.mazeElements.bombs.RandomBombFactory;
import maze.mazeElements.gates.EndGate;
import maze.mazeElements.gifts.AmmoGift;
import maze.mazeElements.gifts.IGift;
import maze.mazeElements.gifts.RandomGiftFactory;
import maze.mazeElements.mazeRunner.MazeRunner;
import maze.mazeElements.monsters.IMonster;
import maze.mazeElements.monsters.RandomMonsterFactory;
import maze.mazeElements.monsters.VampireMonster;
import maze.mazeElements.walls.RandomWallFactory;
import maze.mazeElements.walls.UnbreakableWall;
import maze.mazeElements.walls.WoodWall;

import java.awt.*;

public class EndlessMazeBuilderTester {
    public static void main(String[] args) {

        RandomBombFactory.getInstance().addBomb(DynamiteBomb.class);
        RandomGiftFactory.getInstance().addGift(AmmoGift.class);
        RandomMonsterFactory.getInstance().addMonster(VampireMonster.class);
        RandomWallFactory.getInstance().addWall(UnbreakableWall.class,false,1);
        RandomWallFactory.getInstance().addWall(WoodWall.class,true,1);

        IMazeBuilder mazeBuilder = new EndlessMazeBuilder();
        Maze maze = mazeBuilder.generate(5, Difficulty.Easy);
        for(int i = 0;i < maze.getHeight();i++) {
            for (int j = 0;j < maze.getWidth();j++) {
                Character character = new Character('?');
                IMazeElement element = maze.getElement(new Point(i,j));
                if(element instanceof MazeRunner)
                    character = 'P';
                if(element instanceof UnbreakableWall)
                    character = '#';
                if(element instanceof WoodWall)
                    character = 'W';
                if(element instanceof IBomb)
                    character = 'B';
                if(element instanceof IMonster)
                    character = 'M';
                if(element instanceof IGift)
                    character = 'G';
                if(element instanceof EndGate)
                    character = 'E';
                if(element instanceof EmptyElement)
                    character = ' ';
                System.out.print(character);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}
