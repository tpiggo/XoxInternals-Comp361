package eu.kartoffelquadrat.xox.controller;

import eu.kartoffelquadrat.xox.model.*;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Xox controller with all operations that must be exposed by RESTIFY. Note that for simplicity the signatures of this
 * controller do not support error handling. In case of bad parameters, the controller will simply ignore a method call
 * or return an empty return object.
 *
 * @Author: Maximilian Schiedermeier
 * @Date: December 2020
 */
public class XoxController {

    private static XoxController singletonReference;

    // Injected util beans and own service name.
    private final ActionGenerator actionGenerator;
    private final ActionInterpreter actionInterpreter;
    private XoxGame game;
    private RankingGenerator rankingGenerator;

    /**
     * Private default constructor for singleton pattern. Initializes all required util classes and start a new game
     * with players "X" and "O".
     */
    private XoxController() {

        actionGenerator = new XoxActionGenerator();
        actionInterpreter = new XoxActionInterpreter(actionGenerator, new XoxEndingAnalyzer());
        rankingGenerator = new XoxRankingGenerator();
        resetGame();
    }

    /**
     * Singleton access method to obtain the controller instance. First call implicitly initializes a new game for
     * players "X" and "O".
     *
     * @return
     */
    public static XoxController getInstance() {
        if (singletonReference == null)
            singletonReference = new XoxController();
        return singletonReference;
    }

    /**
     * Overwrites any game in progress by a new game with default players "X" and "O".
     */
    public void resetGame() {

        Player playerX = new Player("X", "#000000");
        Player playerO = new Player("O", "#FFFFFF");
        game = new XoxGame(playerX, playerO);
    }

    /**
     * Getter for the current game board state.
     *
     * @return immutable snapshot of current board.
     */
    public BoardReadOnly getBoard() {
        return game.getBoard();
    }

    /**
     * Getter for static player objects (names, preferred colours) of the participants of the game instance referenced
     * by the provided game-id.
     *
     * @return immutably deep copy of players participating in game and their attributes (name, preferred colour)
     */
    public PlayerReadOnly[] getPlayers() {
        return game.getPlayers();
    }

    /**
     * Method to look up possible actions for a given player.
     *
     * @param player as the player requesting a set of available actions in a running. Will return an empty collection
     *               if the player is not recognized.
     * @return A map, indexing actions by the MD5 representation of their json string serialization. The index serves as
     * key for later re-identification if an actions is selected.
     */
    public Map<String, Action> getActions(String player) {

        // Look up player and build an action bundle. (only non empty for current player)
        PlayerReadOnly playerObject = game.getPlayerByName(player);
        if (playerObject == null)

            // Return empty map if the player is not recognized.
            // Error handling ignored for case study simplicity.
            return new LinkedHashMap<>();
        try {
            return actionGenerator.generateActions(game, playerObject);
        } catch (LogicException e) {

            // Error handling ignored for case study simplicity.
            return new LinkedHashMap<>();
        }
    }

    /**
     * Blackboard-style way to allow a client select a specific operation. The operation is identified by the MD5
     * representation of it's JSON serialization.
     *
     * @param player    as the player requesting to play an action
     * @param actionMD5 as the identifier of the selected action
     */
    public void selectAction(String player, String actionMD5) {

        // Verify the selected action was actually offered
        Map<String, Action> offeredActions = getActions(player);
        if (!offeredActions.containsKey(actionMD5))
            // Error handling ignored for case study simplicity.
            // Ignore request if selected action was not previously offered.
            return;

        // Looks good - perform the action by passing it to the XoxActionInterpreter
        Action selectedAction = offeredActions.get(actionMD5);
        try {
            actionInterpreter.interpretAndApplyAction(selectedAction, game);
        } catch (LogicException | ModelAccessException internalException) {
            // Error handling ignored for case study simplicity.
            return;
        }
    }

    /**
     * Returns current player scores as a serialized ranking object. The ranking object also tells if the game has
     * already ended.
     */
    public Ranking getRanking() {

        try {
            return rankingGenerator.computeRanking(game);
        } catch (LogicException e) {

            // Error handling ignored for case study simplicity.
            return null;
        }
    }
}
