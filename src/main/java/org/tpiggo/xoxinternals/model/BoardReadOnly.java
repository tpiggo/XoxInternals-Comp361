package org.tpiggo.xoxinternals.model;

/**
 * Read Only board interface for Xox boards.
 *
 * @author Maximilian Schiedermeier
 */
public interface BoardReadOnly {

    boolean isEmpty();

    boolean isFull();

    boolean isFree(int xPos, int yPos);

    int[][] getCells();

}
