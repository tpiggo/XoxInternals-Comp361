package org.tpiggo.xoxinternals;

import org.tpiggo.xoxinternals.service.XoxClaimFieldAction;
import org.tpiggo.xoxinternals.service.XoxManagerImpl;
import org.tpiggo.xoxinternals.model.Board;
import org.tpiggo.xoxinternals.model.BoardReadOnly;
import org.tpiggo.xoxinternals.model.XoxInitSettings;
import org.junit.Assert;
import org.junit.Test;


/**
 * Unit tests for the XoxManager
 *
 * @author Maximilian Schiedermeier
 */
public class ManagerTest extends XoxTestUtils {

    /**
     * Verifies player two is start player if test game is created with second player as creator.
     */
    @Test
    public void testSeatingInverted() {
        XoxManagerImpl.getInstance();

        XoxInitSettings initSettings = getDefaultInitSettings(true);
        long gameId = XoxManagerImpl.getInstance().addGame(initSettings);

        // Verify player order - must be inverted, because player two was specified as start player
        assert XoxManagerImpl.getInstance().getActions(gameId, "X").length==0;
        assert XoxManagerImpl.getInstance().getActions(gameId, "O").length!=0;
        assert XoxManagerImpl.getInstance().getPlayers(gameId)[0].getName().equals("O");
        assert XoxManagerImpl.getInstance().getPlayers(gameId)[1].getName().equals("X");
    }

    /**
     * Verifies player one is specified as start player.
     */
    @Test
    public void testSeating() {
        XoxManagerImpl.getInstance();

        XoxInitSettings initSettings = getDefaultInitSettings(false);
        long gameId = XoxManagerImpl.getInstance().addGame(initSettings);

        // Verify player order - must be inverted, because player two was specified as start player
        // Verify actions for current player (O) are non empty, actions for player (X) are empty
        assert XoxManagerImpl.getInstance().getActions(gameId, "X").length!=0;
        assert XoxManagerImpl.getInstance().getActions(gameId, "O").length==0;
        assert XoxManagerImpl.getInstance().getPlayers(gameId)[0].getName().equals("X");
        assert XoxManagerImpl.getInstance().getPlayers(gameId)[1].getName().equals("O");
    }

    /**
     * Verifies game distinct to default game is not present upon first controller initialization.
     */
    @Test
    public void testInitNull() {
        XoxManagerImpl.getInstance();
        assert (XoxManagerImpl.getInstance().getBoard(43) == null);
        assert (XoxManagerImpl.getInstance().getPlayers(43) == null);
        assert (XoxManagerImpl.getInstance().getRanking(43) == null);
    }

    /**
     * Verifies no game is present for not-existing game
     */
    @Test
    public void testResetNull() {
        XoxManagerImpl.getInstance();
        assert (XoxManagerImpl.getInstance().getBoard(43) == null);
        assert (XoxManagerImpl.getInstance().getPlayers(43) == null);
        assert (XoxManagerImpl.getInstance().getRanking(43) == null);
    }

    /**
     * This test alters state, it therefore operates on a newly created random game (avoids interference with previous
     * test executions).
     */
    @Test
    public void playActionTest() {
        XoxManagerImpl.getInstance();

        // Initialize new random game we can mess with
        long gameId = XoxManagerImpl.getInstance().addGame(getDefaultInitSettings(false));

        // Obtain list of available actions. Play the first one.
        XoxClaimFieldAction[] actions = XoxManagerImpl.getInstance().getActions(gameId, "X");
        XoxManagerImpl.getInstance().performAction(gameId, "X", 0);

        // Verify that now it is "O"s turn
        assert XoxManagerImpl.getInstance().getActions(gameId, "O").length != 0;

        // Verify the board marks an X on the position that corresponds to the action, and blank on all other cells.
        BoardReadOnly board = XoxManagerImpl.getInstance().getBoard(gameId);
        Assert.assertFalse(board.isEmpty());
    }

    @Test
    public void testGetBoard() {
        XoxManagerImpl.getInstance();

        Board emptyReferenceBoard = new Board();

        // Verify the game board is all empty.
        BoardReadOnly board = XoxManagerImpl.getInstance().getBoard(42);
        Assert.assertTrue("Board of newly initialized game should be empty, but retrieved board object states it is not.", board.isEmpty());
        Assert.assertFalse("Board of newly initialized games should not be full.", board.isFull());
        Assert.assertTrue("String representation of board does not match empty board: " + board.toString(), board.toString().equals(emptyReferenceBoard.toString()));
    }

    /**
     * This test alters state, it therefore operates on a newly created random game (avoids interference with previous
     * test executions). It creates a new game and then tries to remove it again.
     */
    @Test
    public void testRemoveGame() {
        XoxManagerImpl.getInstance();

        // Initialize new random game we can mess with
        long gameId = XoxManagerImpl.getInstance().addGame(getDefaultInitSettings(false));

        //Verify the game is present
        BoardReadOnly board = XoxManagerImpl.getInstance().getBoard(gameId);
        Assert.assertNotNull("Game was created but the associated board object is null", board);
        Assert.assertTrue("Game was created but ID is not listed by manager", XoxManagerImpl.getInstance().getGames().contains(gameId));

        // Try to remove game again
        XoxManagerImpl.getInstance().removeGame(gameId);

        // Verify the game is gone
        board = XoxManagerImpl.getInstance().getBoard(gameId);
        Assert.assertNull("Game was removed but the associated board object is not null", board);
        Assert.assertFalse("Game was removed but ID is still listed by manager", XoxManagerImpl.getInstance().getGames().contains(gameId));
    }
}