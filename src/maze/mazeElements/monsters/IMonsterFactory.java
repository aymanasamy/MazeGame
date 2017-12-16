package maze.mazeElements.monsters;

import maze.Maze;

public interface IMonsterFactory {
    public IMonster generate(Maze maze, int initialHealth);

    public void addMonster(Class<? extends IMonster > monster);
}
