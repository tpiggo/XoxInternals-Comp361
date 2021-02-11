package eu.kartoffelquadrat.xox.model;

/**
 * Read Only board interface for Xox boards.
 *
 * @Author: Maximilian Schiedermeier
 * @Date: December 2020
 */
public interface BoardReadOnly {

    boolean isEmpty();

    boolean isFull();

    boolean isFree(int xPos, int yPos);

}
