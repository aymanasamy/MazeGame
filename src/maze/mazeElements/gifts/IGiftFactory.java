package maze.mazeElements.gifts;

public interface IGiftFactory {
    public IGift generate();

    public void addGift(Class<? extends IGift> gift);
}
