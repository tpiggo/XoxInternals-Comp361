package org.tpiggo.xoxinternals;

import org.tpiggo.xoxinternals.model.Player;
import org.tpiggo.xoxinternals.model.XoxInitSettings;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class XoxInitSettingsTest {

    /**
     * Tests init settings field setters.
     */
    @Test
    public void testSetters() {

        // Initialize settings object, default CTR
        XoxInitSettings settings = new XoxInitSettings();

        // Call setters and verify effect
        Player player1 = new Player("Max", "#CAFFEE");
        Player player2 = new Player("Moritz", "#1CE7EA");
        LinkedList<Player> players = new LinkedList<>();
        players.add(player1);
        players.add(player2);
        settings.setPlayers(players);
        settings.setCreator(player1.getName());

        // verify setter call was successful
        Assert.assertTrue("Creator not correctly set", settings.getCreator().equals(player1.getName()));
        Assert.assertTrue("Players not correctly set", settings.getPlayers() == players);
    }

}
