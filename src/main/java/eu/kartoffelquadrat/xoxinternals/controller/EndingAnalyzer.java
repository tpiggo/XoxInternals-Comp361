package eu.kartoffelquadrat.xoxinternals.controller;

import eu.kartoffelquadrat.xoxinternals.model.XoxGameReadOnly;

/**
 * Generic ending analyzer interface. Implement this interface to provide a tailored analyzer that determines when
 * entities of the game you implement have reached an end criteria.
 *
 * @author Maximilian Schiedermeier
 */
public interface EndingAnalyzer {

    /**
     * Analyzes the state of a provided game entity and tells whether the session has already come to an end (no more
     * moves possible).
     *
     * @param game as the session to interpret.
     * @return boolean telling whether the game has already ended. If detected, this method will also set a
     * corresponding flag in the game model.
     * @throws LogicException if the provided game can not be interpreted.
     */
    boolean analyzeAndUpdate(XoxGameReadOnly game) throws LogicException;
}
