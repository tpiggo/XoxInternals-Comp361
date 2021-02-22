package eu.kartoffelquadrat.xoxinternals.model;

import java.util.LinkedList;

/**
 * Bean that compiles all information required to initialize a new Xox game instance. Pass an instance of this class to
 * the controller to create a new game session.
 *
 * @Author: Maximilian Schiedermeier
 * @Date: December 2020
 */
public class XoxInitSettings {

    // List of players, in seating order and the preferred colours.
    LinkedList<Player> players;

    // Creator of the game. Typically the first player.
    String creator;

    public XoxInitSettings() {
    }

    public XoxInitSettings(LinkedList<Player> players, String creator) {
        this.players = players;
        this.creator = creator;
    }

    public LinkedList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(LinkedList<Player> players) {
        this.players = players;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}