package maze.mazeElements.mazeRunner;

import maze.IMazeElement;

public class IMazeRunner implements IMazeElement {
    private IMazeRunnerState state;

    void moveRight() {
        state.moveRight();
    }

    void moveLeft() {
        state.moveLeft();
    }

    void moveUp() {
        state.moveUp();
    }

    void moveDown() {
        state.moveDown();
    }

    int getHealth() {
        return 0;
    }

    void setHealth(int health) {

    }

    void setLives(int lives) {

    }

    int getLives() {
        return 0;
    }

    int getBullets() {
        return 0;
    }

    void setBullets(int bullets) {
    }

    IMazeRunnerState getState() {
        return state;
    }

    void setState(IMazeRunnerState state) {
        this.state = state;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void affect(IMazeElement element) {

    }

    public void fire() {
    }
}
