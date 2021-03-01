package eu.kartoffelquadrat.xoxinternals;

import eu.kartoffelquadrat.xoxinternals.controller.Action;
import eu.kartoffelquadrat.xoxinternals.controller.XoxController;
import eu.kartoffelquadrat.xoxinternals.model.Board;
import eu.kartoffelquadrat.xoxinternals.model.BoardReadOnly;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.Set;


/**
 * Unit tests for the actual controller to be exposed through RESTify.
 *
 * @author Maximilian Schiedermeier
 */
public class ControllerTest extends XoxTestUtils {

    /**
     * Verifies player two is start player is specified as start player.
     */
    @Test
    public void testSeatingInverted() {
        XoxController.getInstance().resetGame();

        // Initialize game with inverted seating order
        initGame(true);

        // Verify player order - must be inverted, because player two was specified as start player
        assert XoxController.getInstance().getActions("X").isEmpty();
        assert !XoxController.getInstance().getActions("O").isEmpty();
        assert XoxController.getInstance().getPlayers()[0].getName().equals("O");
        assert XoxController.getInstance().getPlayers()[1].getName().equals("X");
    }

    /**
     * Verifies player one is start player is specified as start player.
     */
    @Test
    public void testSeating() {
        XoxController.getInstance().resetGame();


        // Initialize game with inverted seating order
        initGame(false);

        // Verify player order - must be inverted, because player two was specified as start player
        // Verify actions for current player (O) are non empty, actions for player (X) are empty
        assert !XoxController.getInstance().getActions("X").isEmpty();
        assert XoxController.getInstance().getActions("O").isEmpty();
        assert XoxController.getInstance().getPlayers()[0].getName().equals("X");
        assert XoxController.getInstance().getPlayers()[1].getName().equals("O");
    }

    /**
     * Verifies no game is present upon first controller initialization.
     */
    @Test
    public void testInitNull() {
        XoxController.getInstance().resetGame();
        assert (XoxController.getInstance().getBoard() == null);
        assert (XoxController.getInstance().getPlayers() == null);
        assert (XoxController.getInstance().getRanking() == null);
    }

    /**
     * Verifies no game is present after manual reset.
     */
    @Test
    public void testResetNull() {
        XoxController.getInstance().resetGame();
        initGame(false);
        XoxController.getInstance().resetGame();
        assert (XoxController.getInstance().getBoard() == null);
        assert (XoxController.getInstance().getPlayers() == null);
        assert (XoxController.getInstance().getRanking() == null);
    }

    @Test
    public void testDoubleInit() {
        XoxController.getInstance().resetGame();

        initGame(true);
        initGame(false);

        // second initialization must not have overwritten the initial seating order
        // Verify player order - must be inverted, because player two was specified as start player
        assert XoxController.getInstance().getActions("X").isEmpty();
        assert !XoxController.getInstance().getActions("O").isEmpty();
        assert XoxController.getInstance().getPlayers()[0].getName().equals("O");
        assert XoxController.getInstance().getPlayers()[1].getName().equals("X");
    }

    @Test
    public void playActionTest() {
        XoxController.getInstance().resetGame();

        // X is start player.
        initGame(false);

        // Obtain list of available actions. Play the first one.
        Map<String, Action> actions = XoxController.getInstance().getActions("X");
        Set<String> actionMD5s = actions.keySet();
        String firstActionMD5 = actionMD5s.iterator().next();
        XoxController.getInstance().selectAction("X", firstActionMD5);

        // Verify that now it is "O"s turn
        assert !XoxController.getInstance().getActions("O").isEmpty();

        // Verify the board marks an X on the position that corresponds to the action, and blank on all other cells.
        BoardReadOnly board = XoxController.getInstance().getBoard();
        Assert.assertFalse(board.isEmpty());
    }

    @Test
    public void testGetBoard()
    {
        XoxController.getInstance().resetGame();

        // Init game, no inverted order.
        initGame(false);

        Board emptyReferenceBoard = new Board();

        // Verify the game board is all empty.
        BoardReadOnly board = XoxController.getInstance().getBoard();
        Assert.assertTrue("Board of newly initialized game should be empty, but retrieved board object states it is not.", board.isEmpty());
        Assert.assertFalse("Board of newly initialized games should not be full.", board.isFull());
        Assert.assertTrue("String representation of board does not match empty board: "+board.toString(), board.toString().equals(emptyReferenceBoard.toString()));
    }
}
