package eu.kartoffelquadrat.xoxinternals.controller;

import eu.kartoffelquadrat.xoxinternals.model.ModelAccessException;
import eu.kartoffelquadrat.xoxinternals.model.XoxGameReadOnly;

/**
 * Generic interface for Blackboard-style action interpreters that apply provided player actions on a game instance.
 *
 * @author Maximilian Schiedermeier
 */
public interface ActionInterpreter {

    /**
     * Applies a provided action on a game object. Implementations should verify if the game has ended (using the
     * dedicated EndingAnalyzer) after every performed action.
     *
     * @param action as the action to be interpreted and applied to the game.
     * @param game as the game instance the action shall be applied on.
     * @throws LogicException in case the provided action can not be applied.
     * @throws ModelAccessException in case the provided action attempts to modify restricted parts of the model.
     */
    void interpretAndApplyAction(Action action, XoxGameReadOnly game) throws LogicException, ModelAccessException;
}
