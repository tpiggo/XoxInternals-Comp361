package eu.kartoffelquadrat.xoxinternals.model;

/**
 * Read only interface for properties of a player, as provided by the LobbyService.
 *
 * @author Maximilian Schiedermeier
 */
public interface PlayerReadOnly {

    /**
     * Getter for the name of a player.
     *
     * @return name of a player
     */
    String getName();

    /**
     * Getter for the preferred colour of a player.
     *
     * @return hexadecimal colour string, e.g. #CAFFEE
     */
    String getPreferredColour();

    /**
     * Equals comparison. Only matches names. Ignores upper/lower cases.
     *
     * @param other as the player object to compare with.
     * @return a boolean that indicates whether the names of the provided player objects match.
     */
    boolean equals(Object other);
}
