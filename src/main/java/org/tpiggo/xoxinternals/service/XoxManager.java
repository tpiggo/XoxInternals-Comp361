package org.tpiggo.xoxinternals.service;

import org.springframework.stereotype.Service;
import org.tpiggo.xoxinternals.model.BoardReadOnly;
import org.tpiggo.xoxinternals.model.Player;
import org.tpiggo.xoxinternals.model.XoxInitSettings;

import java.util.Collection;


public interface XoxManager {

    Collection<Long> getGames();

    void removeGame(long gameId);

    long addGame(XoxInitSettings initSettings);

    BoardReadOnly getBoard(long gameId);

    Player[] getPlayers(long gameId);

    XoxClaimFieldAction[] getActions(long gameId, String player);

    void performAction(long gameId, String player, int actionIndex);

    Ranking getRanking(long gameId);
}
