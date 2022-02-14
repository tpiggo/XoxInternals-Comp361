package eu.kartoffelquadrat.xoxinternals;

import eu.kartoffelquadrat.xoxinternals.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    /**
     * Tests player field setters.
     */
    @Test
    public void testSetters() {

        // Initialize player object, default CTR
        Player testPlayer = new Player();

        // Call setters and verify effect
        testPlayer.setName("Moritz");
        testPlayer.setPreferredColour("#1CE7EA");
        Assert.assertTrue("Name not correctly set", testPlayer.getName().equals("Moritz"));
        Assert.assertTrue("Colour not correctly set", testPlayer.getPreferredColour().equals("#1CE7EA"));
    }

}
