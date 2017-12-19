package maze.builders;

import java.awt.Point;
import java.util.Stack;

import maze.IMazeElement;
import maze.Maze;
import maze.mazeElements.bombs.IBombFactory;
import maze.mazeElements.bombs.RandomBombFactory;
import maze.mazeElements.gates.EndGate;
import maze.mazeElements.gates.IGate;
import maze.mazeElements.gates.StartGate;
import maze.mazeElements.gifts.IGiftFactory;
import maze.mazeElements.gifts.RandomGiftFactory;
import maze.mazeElements.monsters.IMonsterFactory;
import maze.mazeElements.monsters.RandomMonsterFactory;
import maze.mazeElements.walls.IWallFactory;
import maze.mazeElements.walls.RandomWallFactory;

public class EndlessMazeBuilder implements IMazeBuilder {

	
	@Override
	public Maze generate(int level, Difficulty difficulty) {
		
		final int size = level * 3;
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
					IGate mystart = new StartGate();
					myEndlessMaze.setElement(myElementPosition, mystart);
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

	@Override
	public IMazeBuilder getInstance() {
		return null;
	}
}
