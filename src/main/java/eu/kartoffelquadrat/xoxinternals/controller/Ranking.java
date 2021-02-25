package eu.kartoffelquadrat.xoxinternals.controller;

import eu.kartoffelquadrat.xoxinternals.model.ModelAccessException;
import eu.kartoffelquadrat.xoxinternals.model.PlayerReadOnly;

/**
 * Represents player scores. Can be created at any point during the gameplay or at the end of the game for final stats.
 *
 * @author Maximilian Schiedermeier
 */
public class Ranking {

    // Represents all players, ranked by their scored in descending order.
    private final PlayerReadOnly[] playersDescending;
    // Represents the scores of all players, in descending order. (players match playerDescending array order)
    private final int[] scoresDescending;
    // boolean flag indicating whether no more moves are possible by any player.
    private boolean gameOver;

    /**
     * Constructor for Ranking beans.
     *
     * @param playersDescending player object array listing player details. The order should be adapted to the players
     *                          scored, listing the player with the highest score first, the second highest score
     *                          second, and so on.
     * @param scoresDescending  the actual scores obtained by the corresponding players of the playersDescending array.
     * @param gameOver          flag to indicate whether no more moves are possible in this session.
     */
    public Ranking(PlayerReadOnly[] playersDescending, int[] scoresDescending, boolean gameOver) {

        if (playersDescending.length != scoresDescending.length)
            throw new RuntimeException("Unable to create ranking. Provided players and scored differ in size.");

        this.playersDescending = playersDescending;
        this.scoresDescending = scoresDescending;

        this.gameOver = gameOver;
    }

    /**
     * Getter to obtain the player details, ordered by descending score.
     *
     * @return the player details as an array.
     */
    public PlayerReadOnly[] getPlayersDescending() {
        return playersDescending;
    }

    /**
     * Getter to obtain the player scores, ordered by descending value.
     *
     * @return the player scores as an array.
     */
    public int[] getScoresDescending() {
        return scoresDescending;
    }

    /**
     * Helper method to get the score obtained by a player specified by name.
     *
     * @param playerName as the name of the player whose score must be retrieved.
     * @return the score of the specified player.
     * @throws ModelAccessException it the provided player string does not any registered player.
     */
    public int getScoreForPlayer(String playerName) throws ModelAccessException {
        for (int i = 0; i < playersDescending.length; i++) {
            if (playersDescending[i].getName().toLowerCase().equals(playerName.toLowerCase()))
                return scoresDescending[i];
        }
        throw new ModelAccessException("Can not retrieve score for player " + playerName + ". No mention of player in this ranking.");
    }

    /**
     * Getter to determine if no more moves are possible by any player.
     *
     * @return boolean flag indicating if no more moves are possible.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Helper method to convert beans of this kind to a human readable string representation.
     *
     * @return String representation of this bean's fields.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Players in descending order:\n");
        sb.append(playersDescending[0]).append(" -> ").append("Longest line: ").append(scoresDescending[0]).append("\n");
        sb.append(playersDescending[1]).append(" -> ").append("Longest line: ").append(scoresDescending[1]).append("\n");
        return sb.toString();
    }
}
