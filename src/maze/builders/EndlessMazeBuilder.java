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
		Maze MyEndlessMaze = new Maze(theMaze.length, theMaze[0].length);
		for (int i = 0; i < theMaze.length; i++) {
			for (int j = 0; j < theMaze[0].length; j++) {
				Point myElementPosition = new Point(i, j);
				if (theMaze[i][j] == 'B') {
					IBombFactory myboomb = RandomBombFactory.getInstance();
					MyEndlessMaze.setElement(myElementPosition, myboomb.generate(MyEndlessMaze));

				}
				if (theMaze[i][j] == 'G') {
					IGiftFactory mygift = RandomGiftFactory.getInstance();
					MyEndlessMaze.setElement(myElementPosition, mygift.generate(MyEndlessMaze));

				}

				if (theMaze[i][j] == 'M') {
					IMonsterFactory myMonister = RandomMonsterFactory.getInstance();
					MyEndlessMaze.setElement(myElementPosition, myMonister.generate(MyEndlessMaze, 5));
				}
				if (theMaze[i][j] == 'S') {
					IGate mystart = new StartGate();
					MyEndlessMaze.setElement(myElementPosition, mystart);
				}
				if (theMaze[i][j] == 'E') {
					IGate myEnd = new EndGate();
					MyEndlessMaze.setElement(myElementPosition, myEnd);
				}
				if (theMaze[i][j] == '#') {
					IWallFactory mywall = RandomWallFactory.getInstance();
					MyEndlessMaze.setElement(myElementPosition, mywall.generate(MyEndlessMaze, false, 500));
				}
				if (theMaze[i][j] == 'T') {
					IWallFactory myTree = RandomWallFactory.getInstance();
					MyEndlessMaze.setElement(myElementPosition, myTree.generate(MyEndlessMaze, true,1));
				}

			}
		}

		return MyEndlessMaze;
	}

	@Override
	public IMazeBuilder getInstance() {
		return null;
	}
}
