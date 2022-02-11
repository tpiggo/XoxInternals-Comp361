package eu.kartoffelquadrat.xoxinternals;

import eu.kartoffelquadrat.xoxinternals.controller.XoxManagerImpl;

/**
 * This class is to demo / test the functionality of Xox. It has no relevance in a REST context.
 *
 * @author Maximilian Schiedermeier
 */
public class DesktopLauncher {

    /**
     * The desktop launcher has no relevance for a REST case study. However it can be run to test the backend.
     *
     * @param args as optional argument. Currently not in use.
     */
    public static void main(String[] args) {

        playXox();
    }

    /**
     * Sample method to set up a demo game and query initialized parameter. This doe snot replace unit tests and is only
     * for demo purposes (how use the XoxController).
     */
    public static void playXox() {

        XoxManagerImpl xoxManagerImpl = XoxManagerImpl.getInstance();

        System.out.println("Welcome to the Xox.");
        System.out.println("-------------------------");
        System.out.println("Here is some feedback from the backend, simulation setup.");

        // Print some game details:
        // Players in seating order:
        System.out.println("Serialized players: ");
        System.out.println(xoxManagerImpl.getPlayers(42)[0]);
        System.out.println(xoxManagerImpl.getPlayers(42)[1]);

        // Board
        System.out.println("Serialized initial board: " + xoxManagerImpl.getBoard(42));

        // Ranking
        System.out.println("Serialized ranking: " + xoxManagerImpl.getRanking(42));
    }
}
