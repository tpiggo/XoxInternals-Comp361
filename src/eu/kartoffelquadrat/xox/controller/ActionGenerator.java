package eu.kartoffelquadrat.xox.controller;

import eu.kartoffelquadrat.xox.model.PlayerReadOnly;
import eu.kartoffelquadrat.xox.model.XoxGameReadOnly;

import java.util.Map;

/**
 * Generic interface for Blockboard-style action generators that provide collections of selectable player options.
 *
 * @Author: Maximilian Schiedermeier
 * @Date: December 2020
 */
public interface ActionGenerator {

    /**
     * Generates a map of actions, where each object is indexed by the MD5 hash of its json string representation.
     *
     * @param game
     * @param player
     * @return
     * @throws LogicException
     */
    Map<String, Action> generateActions(XoxGameReadOnly game, PlayerReadOnly player) throws LogicException;
}
