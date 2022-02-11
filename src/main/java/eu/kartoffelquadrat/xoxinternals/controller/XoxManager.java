package eu.kartoffelquadrat.xoxinternals.controller;

import eu.kartoffelquadrat.xoxinternals.model.BoardReadOnly;
import eu.kartoffelquadrat.xoxinternals.model.PlayerReadOnly;
import eu.kartoffelquadrat.xoxinternals.model.XoxInitSettings;

import java.util.Collection;
import java.util.Map;

public interface XoxManager {

    Collection<Long> getGames();

    void removeGame(long gameId);

    long addGame(XoxInitSettings initSettings);

    BoardReadOnly getBoard(long gameId);

    PlayerReadOnly[] getPlayers(long gameId);

    Map<String, Action> getActions(long gameId, String player);

    void performAction(long gameId, String player, String actionMD5);

    Ranking getRanking(long gameId);
}
