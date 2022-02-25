package eu.kartoffelquadrat.xoxinternals.controller;

import eu.kartoffelquadrat.xoxinternals.model.ModelAccessException;
import eu.kartoffelquadrat.xoxinternals.model.Player;
import eu.kartoffelquadrat.xoxinternals.model.XoxGame;
import eu.kartoffelquadrat.xoxinternals.model.XoxGameReadOnly;

import java.util.Collection;

/**
 * Business Logic class that applies a Xox Action on a provided Xox model instance. A Xox action encodes a players
 * request to lay on a given position. The ActionInterpreter verifies that the action is legal for the provided user. If
 * this is the case the provided Xox model instance is modified as requested.
 *
 * @author Maximilian Schiedermeier
 */
public class XoxActionInterpreter implements ActionInterpreter {

    private final XoxActionGenerator actionGenerator;

    private final XoxEndingAnalyzer endingAnalyzer;

    public XoxActionInterpreter(XoxActionGenerator actionGenerator, XoxEndingAnalyzer endingAnalyzer) {
        this.actionGenerator = actionGenerator;
        this.endingAnalyzer = endingAnalyzer;
    }

    @Override
    public void interpretAndApplyAction(XoxClaimFieldAction action, XoxGameReadOnly game) throws LogicException, ModelAccessException {

        // Verify action and game input type
        if (action.getClass() != XoxClaimFieldAction.class)
            throw new LogicException("Xox Action Interpreter can only handle XoxClaimFieldActions");
        if (game.getClass() != XoxGame.class)
            throw new LogicException("Xox Action Interpreter can only handle XoxGames");
        XoxClaimFieldAction xoxClaimFieldAction = (XoxClaimFieldAction) action;
        XoxGame xoxGame = (XoxGame) game;

        // Verify the action is legit (must be included in list of actions provided by generator)
        if (!isValidAction(game, xoxClaimFieldAction))
            throw new LogicException("Provided action can not be applied on game - is not a valid action.");

        // Apply action on model
        xoxGame.getBoard().occupy(xoxClaimFieldAction.getX(), xoxClaimFieldAction.getY(), xoxGame.isFirstPlayer(xoxClaimFieldAction.getPlayer()));

        // Update current player
        xoxGame.setCurrentPlayer(1-xoxGame.getCurrentPlayerIndex());

        // Pass Game-Over test on model instance
        endingAnalyzer.analyzeAndUpdate(game);
    }

    /**
     * Helper method verify if a specific xox action is contained in an actions bundle. The action is identified by grid
     * position and player information.
     */
    private boolean isValidAction(XoxGameReadOnly game, XoxClaimFieldAction selectedAction) throws LogicException {

        // retrieve all valid actions for player
        Collection<XoxClaimFieldAction> validActions = actionGenerator.generateActions(game, selectedAction.getPlayer()).values();

        // look up if provided action is contained
        for (XoxClaimFieldAction currentAction : validActions) {
            if (currentAction.equals(selectedAction))
                return true;
        }
        return false;
    }
}
