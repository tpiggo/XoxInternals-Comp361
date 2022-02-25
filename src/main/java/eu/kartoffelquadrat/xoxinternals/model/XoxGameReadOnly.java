package eu.kartoffelquadrat.xoxinternals.model;

/**
 * Read-only interface for Xox game model
 *
 * @author Maximilian Schiedermeier
 */
public interface XoxGameReadOnly {

    /**
     * Look up a player by name
     *
     * @param name as the name of the player to look up.
     * @return PlayerReadOnly as the matching player object, if associated. Null otherwise.
     */
    Player getPlayerByName(String name);

    /**
     * Retrieves the board of a generic game.
     *
     * @return the read-only board of a game.
     */
    BoardReadOnly getBoard();

    /**
     * Retrieves the array (in order of registration) of players associated to a game object. Only returns a deep copy
     * of the original players array.
     *
     * @return a deep copy of the player array associated to a game.
     */
    Player[] getPlayers();

    /**
     * Returns bundled information on a player, accessed by the player index within this game instance.
     *
     * @param index to select the player. Either 0 or 1.
     * @return the player info bundle.
     */
    public Player getPlayerInfo(int index);
}
