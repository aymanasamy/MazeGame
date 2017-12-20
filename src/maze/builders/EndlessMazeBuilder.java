package maze.builders;

import java.awt.Point;
import java.util.Stack;

import maze.Maze;
import maze.mazeElements.bombs.IBombFactory;
import maze.mazeElements.bombs.RandomBombFactory;
import maze.mazeElements.gates.EndGate;
import maze.mazeElements.gates.IGate;
import maze.mazeElements.gifts.IGiftFactory;
import maze.mazeElements.gifts.RandomGiftFactory;
import maze.mazeElements.mazeRunner.MazeRunner;
import maze.mazeElements.monsters.IMonsterFactory;
import maze.mazeElements.monsters.RandomMonsterFactory;
import maze.mazeElements.walls.IWallFactory;
import maze.mazeElements.walls.RandomWallFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EndlessMazeBuilder implements IMazeBuilder {
	private static final Logger logger = LogManager.getLogger(EndlessMazeBuilder.class);
	
	@Override
	public Maze generate(int level, Difficulty difficulty) {
		logger.info("Generating an endless maze with a difficulty of {} and level {}",
				difficulty.toString(), level);

		final int size = level * 3;

		logger.info("Maze size is {}", size);

		Stack<Point> myStack = new Stack();
		MazeGenerationAlgorithm MyEndlessMazeinchractars = new MazeGenerationAlgorithm();
		myStack = MyEndlessMazeinchractars.GetmazeInStack(size, size);
		char[][] theMaze = MyEndlessMazeinchractars.GetMazeInCharacters(myStack, size, size);

		Maze myEndlessMaze = new Maze(theMaze.length, theMaze[0].length);
		for (int i = 0; i < theMaze.length; i++) {
			for (int j = 0; j < theMaze[0].length; j++) {
				Point myElementPosition = new Point(i, j);
				if (theMaze[i][j] == 'B') {
					IBombFactory myboomb = RandomBombFactory.getInstance();
					myEndlessMaze.setElement(myElementPosition, myboomb.generate(myEndlessMaze));

				}
				if (theMaze[i][j] == 'G') {
					IGiftFactory mygift = RandomGiftFactory.getInstance();
					myEndlessMaze.setElement(myElementPosition, mygift.generate(myEndlessMaze));

				}

				if (theMaze[i][j] == 'M') {
					IMonsterFactory myMonister = RandomMonsterFactory.getInstance();
					myEndlessMaze.setElement(myElementPosition, myMonister.generate(myEndlessMaze, 5));
				}
				if (theMaze[i][j] == 'S') {
					MazeRunner mazeRunner =  new MazeRunner();
					mazeRunner.setMaze(myEndlessMaze);
					myEndlessMaze.setElement(myElementPosition,mazeRunner);
				}
				if (theMaze[i][j] == 'E') {
					IGate myEnd = new EndGate();
					myEndlessMaze.setElement(myElementPosition, myEnd);
				}
				if (theMaze[i][j] == '#') {
					IWallFactory mywall = RandomWallFactory.getInstance();
					myEndlessMaze.setElement(myElementPosition, mywall.generate(myEndlessMaze, false, 500));
				}
				if (theMaze[i][j] == 'T') {
					IWallFactory myTree = RandomWallFactory.getInstance();
					myEndlessMaze.setElement(myElementPosition, myTree.generate(myEndlessMaze, true,1));
				}

			}
		}

		return myEndlessMaze;
	}
}
