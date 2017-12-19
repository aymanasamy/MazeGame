package maze.saving;

import maze.Maze;
import maze.builders.Difficulty;

import java.io.Serializable;

public class GameState implements Serializable {
    private int level;
    private Difficulty difficulty;
    private Maze maze;

    public GameState(int level, Difficulty difficulty, Maze maze) {
        this.level = level;
        this.difficulty = difficulty;
        this.maze = maze;
    }

    public int getLevel() {
        return level;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Maze getMaze() {
        return maze;
    }
}
