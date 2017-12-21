package maze;

public final class ScoreHandler {
    private ScoreHandler() {}

    private static long totalScore = 0;
    private static long score = 0;
    private static int startingTime = (int) (System.currentTimeMillis() / 1000);
    private static int collectedGifts = 0;
    private static int remainingLives = 0;
    private static int killedMonsters = 0;

    private static void updateTotalScore() {
        int elapsedTime = (int) (System.currentTimeMillis() / 1000) - startingTime;
        totalScore = score +
                (long) Math.pow(collectedGifts +
                                remainingLives +
                                killedMonsters, 2) / elapsedTime;
    }

    public static void addToScore(final int amount) {
        score += amount;
        totalScore += amount;
    }

    public static void subtractFromScore(final int amount) {
        score -= amount;
        totalScore -= amount;
    }

    public static void incrementCollectedGifts() {
        collectedGifts += 1;
        updateTotalScore();
    }

    public static void setRemainingLives(int lives) {
        remainingLives = lives;
        updateTotalScore();
    }

    public static void incrementKilledMonsters() {
        killedMonsters += 1;
        updateTotalScore();
    }

    public static int getStartingTime() {
        return startingTime;
    }

    public static int getCollectedGifts() {
        return collectedGifts;
    }

    public static int getRemainingLives() {
        return remainingLives;
    }

    public static int getKilledMonsters() {
        return killedMonsters;
    }

    public static long getTotalScore() {
        return totalScore;
    }
}
