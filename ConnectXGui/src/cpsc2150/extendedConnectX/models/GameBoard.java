//BENNETT STARKS
package cpsc2150.extendedConnectX.models;

public class GameBoard extends AbsGameBoard implements IGameBoard {
    /**
     * @invariant No gaps between non ' ' tokens
     *
     * @correspondence self = position[numRows - 1 ... 0][0 ... numColumns - 1]
     */
    static private int numRows;
    static private int numColumns;
    static private char[][] position;
    static private int numToWin;



    /**
     * This method is the constructor for the gameboard class and initializes Gameboard as well as sets all available positions to a blank space
     * This method also swaps the rows of position to make the array go bottom left to top right
     * @param r represents the rows of the gameboard
     * @param c represents the columns of the gameboard
     * @param n represents the number of tokens in a row needed to win
     * @pre r {@code <=} 100 AND r {@code >} 2 AND c {@code <=} 100 AND c {@code >} 2 AND n {@code <=} 25 AND n {@code >} 2 AND n {@code <=} r AND n {@code <=} c
     * @post position = char[numRows][numColumns] AND position = [filled with ' '] AND position[0][0] = [in bottom left of 2d array] AND numRows = r AND numColumns = c AND numToWin = n
     */
    public GameBoard(int r, int c, int n) {
        numRows = r;
        numColumns = c;
        numToWin = n;
        position = new char[numRows][numColumns];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                position[i][j] = ' ';
            }
        }
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getNumToWin() {
        return numToWin;
    }

    public char whatsAtPos(BoardPosition pos)
    {
        //returns what is in the cpsc2150.extendedConnectX.models.GameBoard at position pos
        //If no marker is there, it returns a blank space char.
        return position[pos.getRow()][pos.getColumn()];
    }

    public void placeToken(char p, int c)
    {
        //places the character p in column c. The token will be placed in the lowest available row in column c.
        for (int i = 0; i < numRows; i++) {
            if (position[i][c] == ' ') {
                position[i][c] = p;
                break;
            }
        }
    }

}
