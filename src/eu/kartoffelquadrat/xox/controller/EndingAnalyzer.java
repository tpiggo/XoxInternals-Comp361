package eu.kartoffelquadrat.xox.controller;

import eu.kartoffelquadrat.xox.model.XoxGameReadOnly;

/**
 * Generic ending analyzer interface. Implement this interface to provide a tailored analyzer that determines when
 * entities of the game you implement have reached an end criteria.
 *
 * @Author: Maximilian Schiedermeier
 * @Date: December 2020
 */
public interface EndingAnalyzer {
    boolean analyzeAndUpdate(XoxGameReadOnly game) throws LogicException;
}
