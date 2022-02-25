package eu.kartoffelquadrat.xoxinternals.controller;

import eu.kartoffelquadrat.xoxinternals.model.*;

import java.util.*;


/**
 * Xox Controller Implementation. Acts as DAO for game state and provides endpoints to generate actions that allow state
 * modification. Indexes all running games and allows game-specific access by game-id. Note that for simplicity the
 * signatures of this controller do not support error handling. In case of bad parameters, the controller will simply
 * ignore a method call or return an empty return object.
 *
 * @author Maximilian Schiedermeier
 */
public class XoxManagerImpl implements XoxManager {

    private static XoxManagerImpl singletonReference;
    private final XoxActionGenerator actionGenerator;
    private final ActionInterpreter actionInterpreter;
    private final HashMap<Long, XoxGame> games;
    private final RankingGenerator rankingGenerator;

    /**
     * Private default constructor for singleton pattern. Initializes all required util classes and start a new game
     * with players "X" and "O".
     */
    private XoxManagerImpl() {

        actionGenerator = new XoxActionGenerator();
        actionInterpreter = new XoxActionInterpreter(actionGenerator, new XoxEndingAnalyzer());
        games = new LinkedHashMap<>();
        rankingGenerator = new XoxRankingGenerator();
        initializeSampleGame();
    }

    /**
     * Singleton access method to obtain the controller instance. First call implicitly initializes a new game for
     * players "X" and "O".
     *
     * @return unique singleton representative of this class.
     */
    public static XoxManagerImpl getInstance() {
        if (singletonReference == null)
            singletonReference = new XoxManagerImpl();
        return singletonReference;
    }

    @Override
    public Collection<Long> getGames() {
        return games.keySet();
    }

    /**
     * Removes an existing game, no matter the current state.
     */
    @Override
    public void removeGame(long gameId) {

        games.remove(gameId);
    }

    /**
     * Creates a new game entity with the parameters specified as payload. (players, preferredColours). The game new
     * game id is created randomly.
     *
     * @param initSettings as a settings bundle specifying details for the new game. (Player names, player colours,
     *                     creator)
     */
    @Override
    public long addGame(XoxInitSettings initSettings) {

        // Generate a new random game id
        long gameId = generateUniqueGameId();

        // If needed rearrange received array so that first player equals game creator
        if (!initSettings.getCreator().equals(initSettings.getPlayers().getFirst().getName()))
            initSettings.getPlayers().add(initSettings.getPlayers().removeFirst());

        games.put(gameId, new XoxGame(initSettings.getPlayers().getFirst(), initSettings.getPlayers().getLast()));
        return gameId;
    }

    /**
     * Getter for the current game board state. Return null if no game is currently initialized.
     *
     * @return immutable snapshot of current board.
     */
    @Override
    public BoardReadOnly getBoard(long gameId) {

        if (!games.containsKey(gameId))
            return null;
        return games.get(gameId).getBoard();
    }

    /**
     * Getter for static player objects (names, preferred colours) of the participants of the game instance referenced
     * by the provided game-id.
     *
     * @return immutably deep copy of players participating in game and their attributes (name, preferred colour). If no
     * such game is initialized, null is returned.
     */
    @Override
    public Player[] getPlayers(long gameId) {

        if (!games.containsKey(gameId))
            return null;
        return games.get(gameId).getPlayers();
    }

    /**
     * Method to look up possible actions for a given player.
     *
     * @param player as the player requesting a set of available actions in a running. Will return an empty collection
     *               if the player is not recognized.
     * @return A map, indexing actions by the MD5 representation of their json string serialization. The index serves as
     * key for later re-identification if an actions is selected. Returns null if no such game is currently initialized.
     */
    @Override
    public XoxClaimFieldAction[] getActions(long gameId, String player) {

        // Reject if no game is currently initialized
        if (!games.containsKey(gameId))
            return null;

        // Look up player and build an action bundle. (only non empty for current player)
        Player playerObject = games.get(gameId).getPlayerByName(player);
        if (playerObject == null)

            // Return empty map if the player is not recognized.
            // Error handling ignored for case study simplicity.
            return new XoxClaimFieldAction[]{};
        try {
            return actionGenerator.generateActions(games.get(gameId), playerObject).values().toArray(new XoxClaimFieldAction[]{});
        } catch (LogicException e) {

            // Error handling ignored for case study simplicity.
            return new XoxClaimFieldAction[]{};
        }
    }

    /**
     * Blackboard-style way to allow a client select a specific operation. The operation is identified by the MD5
     * representation of it's JSON serialization.
     *
     * @param player    as the player requesting to play an action
     * @param actionIndex as the index of the selected action in the original actions array
     */
    @Override
    public void performAction(long gameId, String player, int actionIndex) {

        // Reject if no such game is currently initialized
        if (!games.containsKey(gameId))
            return;

        // Verify the selected action was actually offered
        XoxClaimFieldAction[] offeredActions = getActions(gameId, player);

        // Looks good - perform the action by passing it to the XoxActionInterpreter
        XoxClaimFieldAction selectedAction = offeredActions[actionIndex];
        try {
            actionInterpreter.interpretAndApplyAction(selectedAction, games.get(gameId));
        } catch (LogicException | ModelAccessException internalException) {
            // Error handling ignored for case study simplicity.
            return;
        }
    }

    /**
     * Returns current player scores as a serialized ranking object. The ranking object also tells if the game has
     * already ended.
     *
     * @return a ranking bundle object with details on the players and their scores.
     */
    @Override
    public Ranking getRanking(long gameId) {

        // Reject if no such game is currently initialized
        if (!games.containsKey(gameId))
            return null;

        try {
            return rankingGenerator.computeRanking(games.get(gameId));
        } catch (LogicException e) {

            // Error handling ignored for case study simplicity.
            return null;
        }
    }

    private void initializeSampleGame() {

        // reject if map not empty
        if (!games.isEmpty())
            throw new RuntimeException("Sample game can only be added as first game");

        // Initialize sample game object
        XoxGame sampleGame = new XoxGame(new Player("Max", "#CAFFEE"), new Player("Moritz", "#1CE7EA"));

        // Add sample game at fixed index ... (Note: all other game ID must be generated dynamically)
        games.put(new Long(42), sampleGame);
    }

    /**
     * Creates a random game ID that is not yet in use.
     */
    private long generateUniqueGameId() {
        long randomGameId = Math.abs(new Random().nextLong());
        while (games.keySet().contains(randomGameId)) {
            randomGameId = Math.abs(new Random().nextLong());
        }
        return randomGameId;
    }
}
