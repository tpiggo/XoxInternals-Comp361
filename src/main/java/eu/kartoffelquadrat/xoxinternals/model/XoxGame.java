package eu.kartoffelquadrat.xoxinternals.model;

/**
 * Represents the state of a Xox running game.
 *
 * @author Maximilian Schiedermeier
 */
public class XoxGame implements XoxGameReadOnly {

    // Read only access to the parameters of the two involved players.
    private final Player[] players = new Player[2];

    // Reference to current state of the board
    Board board;

    // Internal flag to indicate whether the game has already ended or still running.
    private boolean finished;

    // Internal index counter for the current player. Range: [0-1]
    private int currentPlayer;

    public XoxGame(Player startPlayer, Player secondPlayer) {
        players[0] = startPlayer;
        players[1] = secondPlayer;
        currentPlayer = 0;
        board = new Board();
    }

    public boolean isFinished() {
        return finished;
    }

    public void markAsFinished() {
        finished = true;
    }

    @Override
    public Player getPlayerByName(String name) {
        for (Player player : players) {
            if (player.getName().equals(name))
                return player;
        }
        return null;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Player[] getPlayers() {
        Player[] deepCopy = new Player[players.length];
        deepCopy[0] = players[0];
        deepCopy[1] = players[1];
        return deepCopy;
    }

    @Override
    public Player getPlayerInfo(int index) {
        return players[index];
    }

    public int getCurrentPlayerIndex() {
        return currentPlayer;
    }

    public String getCurrentPlayerName() {
        return players[currentPlayer].getName();
    }

    public void setCurrentPlayer(int nextCurrentPlayer) throws ModelAccessException {
        if (nextCurrentPlayer != 0 && nextCurrentPlayer != 1)
            throw new ModelAccessException("Current player can not be set to a value other than 0 or 1.");
        currentPlayer = nextCurrentPlayer;
    }

    /**
     * Helper method that resolves a player object. Returns true if the provided player object is the first player
     * (creator) of the game.
     *
     * @param player as the object to test.
     * @return true if the provided player matches the creator of this game. False otherwise.
     */
    public boolean isFirstPlayer(Player player) {
        return getPlayers()[0].equals(player);
    }
}
