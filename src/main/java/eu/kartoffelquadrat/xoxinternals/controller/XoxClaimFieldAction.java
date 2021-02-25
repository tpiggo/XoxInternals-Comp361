package eu.kartoffelquadrat.xoxinternals.controller;

import eu.kartoffelquadrat.xoxinternals.model.PlayerReadOnly;

/**
 * Represents the only kind of blackboard action required in Xox. The Claim-Field action encodes a position, specified
 * through x and y coordinates and a player who claims the field. The origin is top-left, index counting starts at 0.
 *
 * @author Maximilian Schiedermeier
 */
public class XoxClaimFieldAction implements Action {

    private final int x;
    private final int y;
    private final PlayerReadOnly player;

    /**
     * Constructor for a Xox action encoding the action that represents marking a specific cell (specified by x/y
     * coordinates.)
     *
     * @param x      as the column-index to be populated by this action.
     * @param y      as the row-index to be populated by this action.
     * @param player as the player for who the action is generated.
     * @throws LogicException in case one of the provided position parameters is out of bounds.
     */
    public XoxClaimFieldAction(int x, int y, PlayerReadOnly player) throws LogicException {
        if (x < 0 || x > 2)
            throw new LogicException("Xox action can not be created. X position is out of bounds.");
        if (y < 0 || y > 2)
            throw new LogicException("Xox action can not be created. Y Position is out of bounds.");

        this.x = x;
        this.y = y;
        this.player = player;
    }

    /**
     * Getter for the X value of this claim-field action.
     *
     * @return x coordinate of the cell targeted by this action.
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the Y value of this claim-field action.
     *
     * @return y coordinate of the cell targeted by this action.
     */
    public int getY() {
        return y;
    }

    /**
     * Getter for the player information bundle this action object was build for.
     *
     * @return player this action was built for / the player claiming the specified cell if this action is applied.
     */
    public PlayerReadOnly getPlayer() {
        return player;
    }

    /**
     * Equals comparator. Verifies if this action is semantically identical to a second provided action object.
     *
     * @param other as the second action object.
     * @return boolean telling whether the actions are semantically identical.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != XoxClaimFieldAction.class)
            return false;

        // Compare position and name fields
        XoxClaimFieldAction otherAction = (XoxClaimFieldAction) other;
        if (getX() != otherAction.getX())
            return false;

        if (getY() != otherAction.getY())
            return false;

        return player.equals(otherAction.getPlayer());
    }

    /**
     * Builds a JSON representation of the action object.
     *
     * @return json string serialization of this object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("{x:").append(x).append(",y:").append(y).append(",player:").append(player.toString()).append('}');
        return sb.toString();
    }
}
