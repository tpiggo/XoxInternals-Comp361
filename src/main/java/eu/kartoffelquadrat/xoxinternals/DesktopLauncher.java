package eu.kartoffelquadrat.xoxinternals;

import eu.kartoffelquadrat.xoxinternals.controller.XoxController;
import eu.kartoffelquadrat.xoxinternals.model.Player;
import eu.kartoffelquadrat.xoxinternals.model.XoxInitSettings;

import java.util.LinkedList;

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

        XoxController xoxController = XoxController.getInstance();

        System.out.println("Welcome to the Xox.");
        System.out.println("-------------------------");
        System.out.println("Here is some feedback from the backend, simulation setup.");

        // Start a new Session with sample players
        LinkedList<Player> players = new LinkedList<>();
        players.add(new Player("PlayerOne", "#FF0000"));
        players.add(new Player("PlayerTwo", "#00FF00"));

        // Use PlayerTwo as start player
        XoxInitSettings initSettings = new XoxInitSettings(players, players.getLast().getName());
        xoxController.initGame(initSettings);

        // Print some game details:
        // Players in seating order:
        System.out.println("Serialized players: ");
        System.out.println(xoxController.getPlayers()[0]);
        System.out.println(xoxController.getPlayers()[1]);

        // Board
        System.out.println("Serialized initial board: " + xoxController.getBoard());

        // Ranking
        System.out.println("Serialized ranking: " + xoxController.getRanking());
    }
}
