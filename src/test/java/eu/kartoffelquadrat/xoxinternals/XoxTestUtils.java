package eu.kartoffelquadrat.xoxinternals;

import eu.kartoffelquadrat.xoxinternals.controller.Action;
import eu.kartoffelquadrat.xoxinternals.controller.XoxClaimFieldAction;
import eu.kartoffelquadrat.xoxinternals.controller.XoxController;
import eu.kartoffelquadrat.xoxinternals.model.Player;
import eu.kartoffelquadrat.xoxinternals.model.XoxInitSettings;

import java.util.LinkedList;
import java.util.Map;

/**
 * Helper methods required by various JUnit tests.
 *
 * @author Maximilian Schiedermeier
 */
public class XoxTestUtils {

    /**
     * Creates a new Xox game using the controller singleton.
     *
     * @param inverted puts player two as start player if set to true.
     */
    public void initGame(boolean inverted) {

        // Add test game to gameManager
        LinkedList<Player> players = new LinkedList<>();
        players.add(new Player("X", "#FF0000"));
        players.add(new Player("O", "#00FF00"));
        String startPlayerName = (inverted?"O":"X");
        XoxInitSettings initSettings = new XoxInitSettings(players, startPlayerName);
        XoxController controller = XoxController.getInstance();
        controller.initGame(initSettings);
    }

    /**
     * Helper method to extract the desired action of an actions bundle. The action is identified by gird position.
     */
    public XoxClaimFieldAction findActionForPosition(Map<String, Action> actions, int xPos, int yPos) {

        for(Action action : actions.values())
        {
            if(action.getClass() != XoxClaimFieldAction.class)
                throw new RuntimeException("Action map can not be interpreted - not Xox actions");
            XoxClaimFieldAction xoxAction = (XoxClaimFieldAction) action;

            if(xoxAction.getX() == xPos && xoxAction.getY() == yPos)
                return xoxAction;
        }
        throw new RuntimeException("Requested action is not contained in provided action bundle.");
    }
}
