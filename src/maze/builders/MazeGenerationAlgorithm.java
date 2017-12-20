package maze.builders;

import java.awt.Point;
import java.util.Random;
import java.util.Stack;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class MazeGenerationAlgorithm {
    private String lineSeparator = System.getProperty("line.separator");
	private static final Logger logger = LogManager.getLogger(MazeGenerationAlgorithm.class);
	public static Stack<Point> myWay = new Stack();
	public static Stack<Point> planb = new Stack();

	private static char[][] maze;
	
	
	static boolean[][] visited;

    private String mazeToString(char[][] theMaze) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < theMaze[0].length; ++i) {
            for(int j = 0; j < theMaze.length; ++j) {
                sb.append(theMaze[j][i] + " ");
            }
            sb.append(lineSeparator);
        }
        return sb.toString();
    }

	public Stack<Point> GetmazeInStack(int width, int height) {
        logger.info("Generating a {}x{} maze", width, height);
		int ObserveEnd = 0;
		maze = new char[width][height];
		visited = new boolean[width][height];
		for (int i = 0; i < maze[0].length; i++) {
			for (int j = 0; j < maze.length; j++) {

				maze[i][j] = '#';
			}
		}

		Random rand = new Random();

		int n = rand.nextInt(maze.length);
		int k = 0;
		int l = 0;

		Point CurrentPosition = new Point(n, 0);
		Point PositionBeforeEdit = new Point(n, 0);
		maze[n][0] = 'S';
		visited[n][0] = true;
		Point temp2 = new Point(0, 0);
		temp2.x = CurrentPosition.x;
		temp2.y = CurrentPosition.y;
		myWay.push(temp2);
		planb.push(temp2);
		// System.out.println(temp);
		while (true) {

			if (CurrentPosition.x == maze.length || CurrentPosition.y == maze.length) {
				break;
			}

			CurrentPosition = Search(CurrentPosition);

			if (checkAround(CurrentPosition)) {

				// System.out.println("fuck"+CurrentPosition);

				int checkoutIfThereExistUnvisitedCell = 0;
				int w = 0;
				int z = 0;
				for (w = 0; w < visited.length; w++) {
					for (z = 0; z < visited[0].length; z++) {
						if (!visited[w][z]) {
							// System.out.println(w+" "+z);
                            logger.debug("Not visited: [{}][{}]", w, z);
							checkoutIfThereExistUnvisitedCell = 1;
							break;
						}

					}

					if (checkoutIfThereExistUnvisitedCell == 1) {

						int i = planb.size() - 1;
						while (i >= 0) {
							Point test1 = new Point(0, 1);
							if (w == 0 && z == 0) {
								if (myWay.contains(test1)) {
									Point temp5 = new Point(test1.x, test1.y);
									Point temp6 = new Point(w, z);
									visited[w][z] = true;
									maze[w][z] = 'F';
									myWay.push(temp5);
									myWay.push(temp6);
								}
								Point test5 = new Point(1, 0);
								if (myWay.contains(test5)) {
									Point temp5 = new Point(test5.x, test5.y);
									Point temp6 = new Point(w, z);
									visited[w][z] = true;
									maze[w][z] = 'F';
									myWay.push(temp5);
									myWay.push(temp6);
								}

							}
							// System.out.println(myWay.size()+"
							// "+planb.size());
							test1 = planb.pop();
							// System.out.println(test1+"test");
							if (!checkAround(test1)) {

								Point test = new Point(0, 0);
								test.x = test1.x;
								test.y = test1.y;
								Point temp3 = new Point(test.x, test.y);
								myWay.push(test1);
								// System.out.println(test + "Sds");

								CurrentPosition.x = test.x;
								CurrentPosition.y = test.y;

								break;
							}
							i--;
						}
						planb.clear();
						planb.addAll(myWay);

						break;
					}
					// System.out.println(" ");
				}

				if (checkoutIfThereExistUnvisitedCell == 0) {
					break;
				}
			} else {
				PositionBeforeEdit = CurrentPosition;
			}

			/*
			 * for (int h = 0; h < maze.length; h++) { for (int u = 0; u
			 * <maze.length; u++) { System.out.print(maze[h][u]); }
			 * System.out.println(" "); }
			 * System.out.println("-------------------------------");
			 */
		}

		int size = myWay.size();
		/*
		 * for (int i = 0; i < size; i++) { System.out.println(myWay.get(i));
		 * 
		 * }
		 */

		return myWay;
	}

	private static Point SearchRight(Point currentPosition) {

        logger.debug("Searching right [{}][{}]", currentPosition.x, currentPosition.y);
		int x = currentPosition.x;
		int y = currentPosition.y;
		// System.out.println(x+" "+y);
		if (y >= maze.length || x >= maze.length || y + 1 >= maze[0].length || visited[x][y + 1]) {
			return currentPosition;
		}
		// System.out.println("right");
		visited[x][y + 1] = true;

		currentPosition.y = y + 1;
		maze[x][y + 1] = 'F';
		Point temp = new Point(0, 0);
		temp.x = currentPosition.x;
		temp.y = currentPosition.y;
		myWay.push(temp);
		planb.push(temp);
		// System.out.println(temp);
		return currentPosition;
	}

	private static Point SearchLeft(Point currentPosition) {
        logger.debug("Searching left [{}][{}]", currentPosition.x, currentPosition.y);
		// System.out.println("sfdfs");
		int x = currentPosition.x;
		int y = currentPosition.y;
		// System.out.println(x+" "+y);
		// System.out.println("sfdfs");

		if (y >= maze.length || x >= maze.length || y - 1 < 0 || visited[x][y - 1]) {
			return currentPosition;
		}
		// System.out.println("left");

		visited[x][y - 1] = true;
		maze[x][y - 1] = 'F';

		currentPosition.y = y - 1;
		Point temp = new Point(0, 0);
		temp.x = currentPosition.x;
		temp.y = currentPosition.y;
		myWay.push(temp);
		planb.push(temp);
		// System.out.println(temp);
		return currentPosition;
	}

	private static Point SearchDown(Point currentPosition) {
        logger.debug("Searching down [{}][{}]", currentPosition.x, currentPosition.y);
		int x = currentPosition.x;
		int y = currentPosition.y;
		// System.out.println(x+" "+y);

		if (y >= maze.length || x >= maze.length || x + 1 >= maze.length || visited[x + 1][y]) {
			return currentPosition;
		}
		// System.out.println("down");

		visited[x + 1][y] = true;

		currentPosition.x = x + 1;
		maze[x + 1][y] = 'F';
		Point temp = new Point(0, 0);
		temp.x = currentPosition.x;
		temp.y = currentPosition.y;
		myWay.push(temp);
		planb.push(temp);
		return currentPosition;
	}

	private static Point SearchUp(Point currentPosition) {
        logger.debug("Searching up [{}][{}]", currentPosition.x, currentPosition.y);
		int x = currentPosition.x;
		int y = currentPosition.y;
		// System.out.println(x+" "+y);

		if (y >= maze.length || x >= maze.length || x - 1 < 0 || visited[x - 1][y]) {
			return currentPosition;
		}
		// System.out.println("up");

		visited[x - 1][y] = true;
		maze[x - 1][y] = 'F';

		currentPosition.x = x - 1;
		Point temp = new Point(0, 0);
		temp.x = currentPosition.x;
		temp.y = currentPosition.y;
		myWay.push(temp);
		planb.push(temp);
		// System.out.println(temp);

		return currentPosition;
	}

	public static boolean checkAround(Point mypoint) {
        logger.debug("Checking around [{}][{}]", mypoint.x, mypoint.y);

		if ((mypoint.x) - 1 > 0) {

			if (!visited[(mypoint.x) - 1][mypoint.y]) {
				return false;
			}
		}
		if ((mypoint.x) + 1 < maze.length) {

			if (!visited[(mypoint.x) + 1][mypoint.y]) {
				return false;
			}
		}
		if ((mypoint.y) - 1 > 0) {

			if (!visited[mypoint.x][(mypoint.y) - 1]) {
				return false;
			}
		}
		if ((mypoint.y) + 1 < maze.length) {

			if (!visited[mypoint.x][(mypoint.y) + 1]) {
				return false;
			}
		}

		return true;
	}

	public static Point Search(Point CurrentPosition) {
        logger.debug("Search around [{}][{}]", CurrentPosition.x, CurrentPosition.y);
		Random rand = new Random();
		int Direction = rand.nextInt(4) + 1;
		Point temp = new Point(0, 0);
		if (Direction == 1) {
			temp = SearchUp(CurrentPosition);
			if (temp == CurrentPosition) {
				Direction = rand.nextInt(3) + 2;
			}
		}
		if (Direction == 2) {
			temp = SearchDown(CurrentPosition);
			if (temp == CurrentPosition) {
				Direction = rand.nextInt(2) + 3;
			}
		}
		if (Direction == 3) {
			temp = SearchRight(CurrentPosition);
			if (temp == CurrentPosition) {
				Direction = 4;
			}
		}
		if (Direction == 4) {
			temp = SearchLeft(CurrentPosition);

		}

		return temp;

	}

	public char[][] GetMazeInCharacters(Stack<Point> myStack, int width, int height) {
		Stack<Point> newStack = new Stack();
		char[][] MyCompleteMaze = new char[width * 2 + 1][height * 2 + 1];
		final int NumOfGifts = MyCompleteMaze.length / 10;
		final int NumOfMonisters = MyCompleteMaze.length / 10;
		for (int x = 0; x < MyCompleteMaze.length; x++) {
			for (int y = 0; y < MyCompleteMaze[0].length; y++) {
				MyCompleteMaze[x][y] = '#';
			}
		}
		int order = 1;
		for (int j = 0; j < myStack.size(); j++) {
			for (int i = 0; i < myStack.size(); i++) {
				int x = myStack.get(i).x;
				int y = myStack.get(i).y;

				/* take a random end of the maze */
				if (order == 1) {
					Random rand = new Random();
					int end = rand.nextInt(maze.length);
					maze[end][maze.length - 1] = 'E';
					order++;
				}
				/* end thid task */

				x = x + x + 1;
				y = y + y + 1;

				Point newPoint = new Point(x, y);
				newStack.push(newPoint);
				if (maze[myStack.get(i).x][myStack.get(i).y] == 'S') {
					MyCompleteMaze[x][y] = 'S';
				} else if (maze[myStack.get(i).x][myStack.get(i).y] == 'E') {
					MyCompleteMaze[x][y] = 'E';
				} else {
					MyCompleteMaze[x][y] = 'f';
				}
			}
			order++;

		}

		for (int f = 0; f < newStack.size() - 1; f++) {
			int x = newStack.get(f).x;
			int y = newStack.get(f).y;
			int x2 = newStack.get(f + 1).x;
			int y2 = newStack.get(f + 1).y;

			if (x == (x2 + 2) && y == y2) {
				MyCompleteMaze[x - 1][y] = 'f';

			} else if ((x + 2) == x2 && y == y2) {
				MyCompleteMaze[x2 - 1][y] = 'f';
			} else if (y == (y2 + 2) && x == x2) {
				MyCompleteMaze[x][y - 1] = 'f';

			} else if ((y + 2) == y2 && x == x2) {
				MyCompleteMaze[x][y2 - 1] = 'f';
			}

		}

		for (int x = 0; x < MyCompleteMaze.length; x++) {
			for (int y = 0; y < MyCompleteMaze[0].length; y++) {
				if (MyCompleteMaze[x][y] == 'E' && y == (MyCompleteMaze.length) - 2) {
					MyCompleteMaze[x][y + 1] = 'E';
					MyCompleteMaze[x][y] = 'f';
				}
				if (MyCompleteMaze[x][y] == 'S' && y == 1) {
					MyCompleteMaze[x][y - 1] = 'S';
					MyCompleteMaze[x][y] = 'f';
				}
			}
		}

		MyCompleteMaze = putBooms(MyCompleteMaze, 0);
		MyCompleteMaze = putGifts(MyCompleteMaze, 0);
		MyCompleteMaze = putMonisters(MyCompleteMaze, 0);
		MyCompleteMaze = puttrees(MyCompleteMaze,0);

		return MyCompleteMaze;
	}
	
	
	public char [][] putBooms(char [][]MyCompleteMaze,int difficulty){
        logger.debug("Populating maze with bombs");
		final int NumOfBooms = MyCompleteMaze.length / 2;
		int i=0;
		while(i<NumOfBooms) {

			Random rand = new Random();
			int x = rand.nextInt(maze.length);
			int y = rand.nextInt(maze.length);
			
			if(MyCompleteMaze[x][y]== 'S' ||
					MyCompleteMaze[x][y]== 'E'|| 
					MyCompleteMaze[x][y]=='B' ||
					MyCompleteMaze[x][y]=='M' ||
					MyCompleteMaze[x][y]=='G'||
				    MyCompleteMaze[x][y]=='T'||
					x==0 || y==0){
				 
			}else{
				MyCompleteMaze[x][y]='B';
				i++;
			}
		}
		return MyCompleteMaze;
	}
	public char [][] putMonisters(char [][]MyCompleteMaze,int difficulty){
        logger.debug("Populating maze with monsters");
		final int NumOfMonisters = MyCompleteMaze.length / 2;
		int i=0;
		while(i<NumOfMonisters) {

			Random rand = new Random();
			int x = rand.nextInt(maze.length);
			int y = rand.nextInt(maze.length);
			
			if(MyCompleteMaze[x][y]== 'S' ||
					MyCompleteMaze[x][y]== 'E'|| 
					MyCompleteMaze[x][y]=='B' ||
					MyCompleteMaze[x][y]=='M' ||
					MyCompleteMaze[x][y]=='G'||
				    MyCompleteMaze[x][y]=='T'||
					x==0 || y==0){
				 
			}else{
				MyCompleteMaze[x][y]='M';
				i++;
			}
		}
		
		return MyCompleteMaze;
	}
	public char [][] putGifts(char [][]MyCompleteMaze,int difficulty){
        logger.debug("Populating maze with gifts");
		final int NumOfGifts = MyCompleteMaze.length / 2;
		int i=0;
		while(i<NumOfGifts) {

			Random rand = new Random();
			int x = rand.nextInt(maze.length);
			int y = rand.nextInt(maze.length);
			
			if(MyCompleteMaze[x][y]== 'S' ||
					MyCompleteMaze[x][y]== 'E'|| 
					MyCompleteMaze[x][y]=='B' ||
					MyCompleteMaze[x][y]=='M' ||
					MyCompleteMaze[x][y]=='G'||
				    MyCompleteMaze[x][y]=='T'||
					x==0 || y==0){
				 
			}else{
				MyCompleteMaze[x][y]='G';
				i++;
			}
		}
		return MyCompleteMaze;
		
	}
	public char [][] puttrees(char [][]MyCompleteMaze,int difficulty){
        logger.debug("Populating maze with tree");
		final int NumOftrees = MyCompleteMaze.length / 2;
		int i=0;
		while(i<NumOftrees) {

			Random rand = new Random();
			int x = rand.nextInt(maze.length);
			int y = rand.nextInt(maze.length);
			
		if(MyCompleteMaze[x][y]== 'S' ||
				MyCompleteMaze[x][y]== 'E'|| 
				MyCompleteMaze[x][y]=='B' ||
				MyCompleteMaze[x][y]=='M' ||
				MyCompleteMaze[x][y]=='G'||
			    MyCompleteMaze[x][y]=='T'||
				x==0 || y==0){
				 
			}else{
				MyCompleteMaze[x][y]='T';
				i++;
			}
		}
		return MyCompleteMaze;
		
	}
	
	
	
	

}
