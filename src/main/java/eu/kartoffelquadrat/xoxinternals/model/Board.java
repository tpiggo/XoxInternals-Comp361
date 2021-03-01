package eu.kartoffelquadrat.xoxinternals.model;

/**
 * Xox specific implementation of the board interface. Encodes 3x3 matrix with individually maintained state model per
 * cell.
 *
 * @author Maximilian Schiedermeier
 */
public class Board implements BoardReadOnly {

    // States of cells are encoded by Characters:
    // ' ': empty. 'x': occupied by x, 'o': occupied by o, 'X' winning cell of x, 'O' winning cell of o.
    private final char[][] cells;

    /**
     * Default constructor for Xox boards. Creates an empty 3x3 cell matrix.
     */
    public Board() {
        cells = new char[3][3];
        initCells();
    }

    /**
     * Iterates of a board and returns true if all cells are populated.
     *
     * @return boolean telling whether the xox is full.
     */
    @Override
    public boolean isFull() {

        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                if (cells[x][y] == ' ')
                    return false;
            }
        }
        return true;
    }

    /**
     * Insepcts the cells and returns true if three equal symbols lie on row, column or main diagonal.
     *
     * @return boolean telling whether on the current board there are three cells in a line, claimed by the same player.
     */
    public boolean isThreeInALine() {

        return (getThreeInALineCharIfExists() != ' ');
    }

    /**
     * If there a three in a line, this method returns the character of those cells. If there are not, it returns the
     * space character.
     *
     * @return a character indicating the occupier of the line or a whitespace.
     */
    public char getThreeInALineCharIfExists() {
        // check for three in a row
        for (int y = 0; y < cells[0].length; y++) {
            if (cells[0][y] == cells[1][y] && cells[1][y] == cells[2][y])
                return cells[1][y];
        }

        // check for three in a column
        for (int x = 0; x < cells.length; x++) {
            if (cells[x][0] == cells[x][1] && cells[x][1] == cells[x][2])
                return cells[x][1];
        }

        // check diagonals
        if (cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2])
            return cells[1][1];
        if (cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0])
            return cells[1][1];

        return ' ';
    }

    public boolean isFree(int xPos, int yPos) {

        return cells[yPos][xPos] == ' ';
    }

    @Override
    public char[][] getCells() {
        //Create target copy of same size
        char[][] copiedCells = new char[cells.length][cells[0].length];

        // Fill target copy with identical values
        for (int x = 0; x < copiedCells.length; x++) {
            for (int y = 0; y < copiedCells[x].length; y++) {
                copiedCells[x][y] = cells[x][y];
            }
        }

        //return deep copy of cells.
        return copiedCells;
    }

    /**
     * Iterates over the board and initializes all cells with the whitespace character.
     */
    private void initCells() {
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                cells[x][y] = ' ';
            }
        }
    }

    public void occupy(int xPos, int yPos, boolean firstPlayer) throws ModelAccessException {
        if (!isFree(xPos, yPos))
            throw new ModelAccessException("Requested cell can not by occupied. Is not free.");
        cells[yPos][xPos] = (firstPlayer ? 'x' : 'o');
    }

    @Override
    public boolean isEmpty() {
        // iterate over all cells. return false if at least one cell occupied, true otherwise.
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                if (!isFree(x, y))
                    return false;
            }
        }

        // All cells free, board is empty.
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");

        sb.append("\n -------------\n");
        for (int x = 0; x < cells.length; x++) {
            sb.append(" | ");
            for (int y = 0; y < cells[x].length; y++) {
                if (cells[x][y] == ' ')
                    sb.append('.');
                else
                    sb.append(cells[x][y]).charAt(0);
                sb.append(" | ");
            }
            sb.append("\n");
            sb.append(" -------------\n");
        }
        return sb.toString();
    }
}
