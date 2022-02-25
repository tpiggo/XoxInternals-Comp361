package eu.kartoffelquadrat.xoxinternals.controller;

import eu.kartoffelquadrat.xoxinternals.model.Player;
import eu.kartoffelquadrat.xoxinternals.model.XoxGame;
import eu.kartoffelquadrat.xoxinternals.model.XoxGameReadOnly;

import java.util.Map;

/**
 * Generic interface for Blackboard-style action generators that provide collections of selectable player options.
 *
 * @author Maximilian Schiedermeier
 */
public interface ActionGenerator {

    /**
     * Generates a map of actions, where each object is indexed by the MD5 hash of its json string representation.
     *
     * @param game   as the game instance to analyze
     * @param player as the player for who the action bundle must be created
     * @return a map where every entry key is the MD5 of the JSON string representation for the associated value
     * (action)
     * @throws LogicException in case the provided game type is not supported
     */
    Map<String, XoxClaimFieldAction> generateActions(XoxGameReadOnly game, Player player) throws LogicException;
}
