package eu.kartoffelquadrat.xox.controller;

import eu.kartoffelquadrat.xox.model.ModelAccessException;
import eu.kartoffelquadrat.xox.model.XoxGameReadOnly;

/**
 * Generic interface for Blackboard-style action interpreters that apply provided player actions on a game instance.
 *
 * @Author: Maximilian Schiedermeier
 * @Date: December 2020
 */
public interface ActionInterpreter {

    /**
     * Applies a provided action on a game object. Implementations should verify if the game has ended (using the
     * dedicated EndingAnalyzer) after every performed action.
     *
     * @param action as the action to be interpreted and applied to the game.
     * @param game as the game instance the action shall be applied on.
     */
    void interpretAndApplyAction(Action action, XoxGameReadOnly game) throws LogicException, ModelAccessException;
}
