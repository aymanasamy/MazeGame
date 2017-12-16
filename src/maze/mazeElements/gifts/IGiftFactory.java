package maze.mazeElements.gifts;

import maze.Maze;

public interface IGiftFactory {
    public IGift generate(Maze maze);

    public void addGift(Class<? extends IGift> gift);
}
