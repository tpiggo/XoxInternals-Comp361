package eu.kartoffelquadrat.xoxinternals.controller;

import eu.kartoffelquadrat.xoxinternals.model.XoxGameReadOnly;

/**
 * Custom game specific classes implementing this interface can be applied on a game at any state, to compute the
 * current ranking (scores per player, descending order.)
 *
 * @author Maximilian Schiedermeier
 */
public interface RankingGenerator {

    /**
     * @param game as the game instance to be evaluated.
     * @return a ranking object that tells the scores per player and the players in descending order.
     * @throws LogicException in case the provide game could not be properly interpreted.
     */
    Ranking computeRanking(XoxGameReadOnly game) throws LogicException;
}
