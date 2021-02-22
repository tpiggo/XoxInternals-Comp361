package eu.kartoffelquadrat.xoxinternals;

import eu.kartoffelquadrat.xoxinternals.controller.XoxController;

/**
 * This class is to demo / test the functionality of Xox. It has no relevance in a REST-serivce context.
 *
 * @author Maximilian Schiedermeier
 */
public class DesktopLauncher {

    /**
     * The desktop launcher has no relevance for a REST case study. However it can be run to test the backend.
     */
    public static void main(String[] args) {

        playXox();
    }

    public static void playXox() {

        XoxController xoxController = XoxController.getInstance();

        System.out.println("Welcome to the Xox.");
        System.out.println("-------------------------");
        System.out.println("Here is some feedback from the backend, simulation setup.");
        System.out.println("Serialized players: " +xoxController.getPlayers());
        System.out.println("Serialized initial board: "+xoxController.getBoard());
        System.out.println("Serialized ranking: "+xoxController.getRanking());
    }
}