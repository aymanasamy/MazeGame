package maze.mazeElements.bombs;

public interface IBombFactory {
    public IBomb generate();

    public void addBomb(Class<? extends IBomb> bomb);
}
