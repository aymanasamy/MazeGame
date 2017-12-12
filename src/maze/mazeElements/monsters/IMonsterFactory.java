package maze.mazeElements.monsters;

public interface IMonsterFactory {
    public IMonster generate(int initialHealth);

    public void addMonster(Class<? extends IMonster > monster);
}
