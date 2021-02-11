package eu.kartoffelquadrat.xox.controller;

import eu.kartoffelquadrat.xox.model.PlayerReadOnly;
import eu.kartoffelquadrat.xox.model.XoxGame;
import eu.kartoffelquadrat.xox.model.XoxGameReadOnly;

public class XoxRankingGenerator implements RankingGenerator {

    @Override
    public Ranking computeRanking(XoxGameReadOnly game) throws LogicException {

        // can only be applied on Xox games
        if(game.getClass() != XoxGame.class)
            throw new LogicException("Xox Ranking generator can only operate on Xox games.");
        XoxGame xoxGame = (XoxGame) game;

        // Will only provide a ranking with non-0 scores, if the game has already ended.
        if(!((XoxGame) game).isFinished())
            return new Ranking(game.getPlayers(), new int[]{0,0}, false);

        // Verify there actually is a player, if not:
        if(isDraw(xoxGame))
            return new Ranking(game.getPlayers(), new int[]{0,0}, true);

        // Winner (player with 3 in a row) gets 1 point, looser 0.
        char winnerChar = xoxGame.getBoard().getThreeInALineCharIfExists();
        PlayerReadOnly[] rankedPlayers = game.getPlayers();

        // If the non-creator won, return a ranking that is the inverse of the games player listing.
        if(winnerChar != 'x')
            ArrayUtils.reverse(rankedPlayers); // ToDo: Get rid of library call.
        return new Ranking(rankedPlayers, new int[]{1, 0}, true);
    }

    /**
     * Analyze if the provided game resulted in a draw (no winner)
     * @return
     */
    private boolean isDraw(XoxGame game)
    {
        if(!game.isFinished())
            return false;

        return !game.getBoard().isThreeInALine();
    }
}
