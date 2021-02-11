package eu.kartoffelquadrat.xox.model;

/**
 * Read-only interface for Xox game model
 *
 * @Author: Maximilian Schiedermeier
 * @Date: December 2020
 */
public interface XoxGameReadOnly {

    /**
     * Look up a player by name
     *
     * @param name as the name of the player to look up.
     * @return PlayerReadOnly as the matching player object, if associated. Throws a ModelAccessException if the
     * provided player is not associated to this game object.
     */
    PlayerReadOnly getPlayerByName(String name) throws ModelAccessException;

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
    PlayerReadOnly[] getPlayers();

    /**
     * Returns bundled information on a player, accessed by the player index within this game instance.
     *
     * @param index to select the player. Either 0 or 1.
     * @return the player info bundle.
     */
    public PlayerReadOnly getPlayerInfo(int index);
}