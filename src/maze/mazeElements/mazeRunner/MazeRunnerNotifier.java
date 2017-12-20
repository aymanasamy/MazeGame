package maze.mazeElements.mazeRunner;

import maze.mazeElements.Direction;

import java.util.Iterator;
import java.util.List;

public class MazeRunnerNotifier {
    private List<IMazeRunnerObserver> observerList;
    public MazeRunnerNotifier() {
    }
    public void setDirection(Direction direction) {
        Iterator<IMazeRunnerObserver> iterator = observerList.iterator();
        while (iterator.hasNext()){
            iterator.next().setDirection(direction);
        }
    }
    public void addObserver(IMazeRunnerObserver observer){
        observerList.add(observer);
    }
    public void removeObserver(IMazeRunnerObserver observer){
        observerList.remove(observer);
    }
}
