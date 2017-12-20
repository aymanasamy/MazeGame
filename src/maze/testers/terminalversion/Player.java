package maze.testers.terminalversion;

import maze.mazeElements.Direction;
import maze.mazeElements.mazeRunner.IMazeRunnerObserver;

public class Player implements IMazeRunnerObserver {


    @Override
    public void fire() {
        TerminalMain.refresh();
    }

    @Override
    public void destroy() {
        TerminalMain.refresh();
    }

    @Override
    public void setHealth(int health) {
        TerminalMain.refresh();
    }

    @Override
    public void setLives(int lives) {
        TerminalMain.refresh();
    }

    @Override
    public void setBullets(int bullets) {
        TerminalMain.refresh();
    }

    @Override
    public void affect() {
        TerminalMain.refresh();
    }

    @Override
    public void setDirection(Direction direction) {
        TerminalMain.refresh();
    }

    @Override
    public void moveIn(Direction direction) {
        if(direction.equals(Direction.Right)) {
            // Move Right
        }
        //TerminalMain.refresh();
    }
}
