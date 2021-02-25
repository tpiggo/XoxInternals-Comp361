package eu.kartoffelquadrat.xoxinternals.controller;

import eu.kartoffelquadrat.xoxinternals.model.BoardReadOnly;
import eu.kartoffelquadrat.xoxinternals.model.PlayerReadOnly;
import eu.kartoffelquadrat.xoxinternals.model.XoxGame;
import eu.kartoffelquadrat.xoxinternals.model.XoxGameReadOnly;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Analyzes a Xox game and generates a collection of valid actions for a player.
 *
 * @author Maximilian Schiedermeier
 */
public class XoxActionGenerator implements ActionGenerator {

    /**
     * Verifies if a provided player is a valid participant (player) of a Xox game. The verification runs a case
     * sensitive string comparison of the player names.
     *
     * @param game   as the xox game.
     * @param player as the player object of the participant to test.
     * @return a boolean, indicating whether the provided name is a valid player name.
     */
    private static boolean isParticipant(XoxGameReadOnly game, PlayerReadOnly player) {
        return game.getPlayerInfo(0).getName().equals(player.getName()) ||
                game.getPlayerInfo(1).getName().equals(player.getName());
    }

    /**
     * Iterates over all cells of a provided Xox-Board and creates an action object for every unoccupied cell.
     *
     * @param board as the 3x3 grid to be analyzed.
     * @return an array of possible lay actions.
     * @throws LogicException in case one of the resulting player actions could not be correctly created.
     */
    private static Map<String, Action> emptyCellsToActions(BoardReadOnly board, PlayerReadOnly player) throws LogicException {
        Map<String, Action> actionMap = new LinkedHashMap();

        // Iterate over board
        for (int yPos = 0; yPos < 3; yPos++) {
            for (int xPos = 0; xPos < 3; xPos++) {

                // Add an action if the position is free
                if (board.isFree(xPos, yPos)) {
                    Action action = new XoxClaimFieldAction(xPos, yPos, player);
                    String actionMd5 = actionToHash(action);
                    actionMap.put(actionMd5, action);
                }
            }
        }
        return actionMap;
    }

    /**
     * Computes a unique MD5 checksum based on the string representation of an action object. See:
     * https://stackoverflow.com/a/5470263
     *
     * @param action
     */
    private static String actionToHash(Action action) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5sum = md.digest(action.toString().getBytes());
            return String.format("%032X", new BigInteger(1, md5sum));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 hashing not supported.");
        }
    }

    /**
     * @param game   as the game instance for which the
     * @param player as the player object defining the participant for why tha action bundle shall be created. Can be
     *               null, if en empty actions set must be generated for an observer who does not actively participate
     *               in the game.
     * @return Map translating from unique action identifiers to the actual actions.
     */
    @Override
    public Map<String, Action> generateActions(XoxGameReadOnly game, PlayerReadOnly player) throws LogicException {

        // Verify and cast the game type
        if (game.getClass() != XoxGame.class)
            throw new LogicException("Xox Action Generator can only handle Xox games.");
        XoxGame xoxGame = (XoxGame) game;

        // Non participants (observers) always receive an empty action bundle.
        if (player == null || !isParticipant(xoxGame, player))
            return new LinkedHashMap<>();

        // If the game is already over, return an empty set
        if (xoxGame.isFinished())
            return new LinkedHashMap<>();

        // If not the player's turn, return an empty set. (Check is performed by comparing the name of the current player)
        if (!player.getName().toLowerCase().equals(xoxGame.getCurrentPlayerName().toLowerCase()))
            return new LinkedHashMap<>();

        // Iterate over board and add an action for every unoccupied cell.
        return emptyCellsToActions(xoxGame.getBoard(), player);
    }
}

