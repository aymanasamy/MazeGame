package maze.saving;

import maze.Maze;
import maze.builders.Difficulty;

import java.io.*;

public class Saver {
    private static Saver instance;

    private Saver() {}

    public static Saver getInstance() {
        if (instance == null) {
            instance = new Saver();
        }
        return instance;
    }

    public void save(Difficulty difficulty,int level,Maze maze) {
        GameState currentState = new GameState(level, difficulty, maze);
        try {
            FileOutputStream fos = new FileOutputStream(
                    String.format("%d-%s.maze", level, difficulty.toString()));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(currentState);
            oos.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public Maze load(Difficulty difficulty,int level) {
        Maze loadedMaze = null;

        if (isSaved(difficulty, level)) {
            try {
                FileInputStream fis = new FileInputStream(
                        String.format("%d-%s.maze", level, difficulty.toString()));
                ObjectInputStream ois = new ObjectInputStream(fis);
                GameState loadedState = (GameState) ois.readObject();
                ois.close();
                if (loadedState.getLevel() == level && loadedState.getDifficulty() == difficulty) {
                    loadedMaze = loadedState.getMaze();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return loadedMaze;
    }

    public boolean isSaved(Difficulty difficulty,int level){
        File file = new File(
                String.format("%d-%s.maze", level, difficulty.toString()));
        return file.exists();
    }
}
