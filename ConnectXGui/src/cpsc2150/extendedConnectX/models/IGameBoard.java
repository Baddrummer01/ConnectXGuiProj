//BENNETT STARKS
package cpsc2150.extendedConnectX.models;

/**
 * A Gameboard containing blank spaces or a token decided by the user
 * A Gameboard contains characters in such a way to allow you
 * to insert a player token at the lowest available space of the board
 */

/**
 * self is abstractly a board of characters
 *
 * Initialization ensures:
 *  self is filled with blank spaces, i.e. ' ', with a size dictated by its rows and columns
 *  self's 0,0 coordinate is the bottom left of the board
 *
 * Constraints:
 *  length of a self = [size of rows of self] * [size of columns of self]
 */
public interface IGameBoard {

    /**
     * This method returns the number of rows in the game grid
     * @return the number of rows in the game grid
     * @pre none
     * @post getNumRows = [rows of self] AND [rows of self] = #[rows of self]
     */
    public int getNumRows();

    /**
     * This method returns the number of columns in the game grid
     * @return the number of columns in the game grid
     * @pre none
     * @post getNumColumns = [columns of self] AND [columns of self] = #[columns of self]
     */
    public int getNumColumns();

    /**
     * This method returns the number of same-characters in a row to win
     * @return the number of same-characters in a row to win
     * @pre none
     * @post getNumToWin = [number of wins of self] AND [number of wins of self] = #[number of wins of self]
     */
    public int getNumToWin();

    /**
     * Shows what token is in the current position of the game board, otherwise shows a blank space
     * @param pos represents the position that is being checked
     * @return the character in the position specified, or a blank space if nothing
     * @pre pos.getRow() < [rows of self] AND pos.getColumn() < [columns of self]
     * @post (whatsAtPos = [char] OR whatsAtPos = ' ') AND self = #self
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * This method determines whether a column is free and can accept another token
     * @param c represents the column the token is being placed in
     * @return whether the column can accept another token or is full; true or false
     * @pre 0 {@code <=} c {@code <} [columns of self]
     * @post checkIfFree = true [when not a tie] OR checkIfFree = false
     */
    public default boolean checkIfFree(int c) {
        BoardPosition test = new BoardPosition(getNumRows() - 1, c);
        return whatsAtPos(test) == ' ';
    }

    /**
     * This method places a token of a certain character into a certain column
     * @param p represents the character token that will be placed in the column, X or O
     * @param c represents the column the token will be placed in
     * @pre (p = 'X' OR p = 'O') AND 0 {@code <=} c {@code <} [columns of self]
     * @post [p placed in lowest available row position of c], otherwise self = #self
     */
    public void placeToken(char p, int c);

    /**
     * This method checks if the token placed in the column wins the game
     * @param c represents the column the token is placed in
     * @return whether the placed token in the column results in a win
     * @pre 0 {@code <=} c {@code <} numColumns AND c = [column where last token was placed]
     * @post (checkForWin = true [number of wins of self in a row Vertically, Horizontally, or Diagonally] OR checkForWin = false) AND self = #self
     */
    public default boolean checkForWin(int c) {
        for (int i = getNumRows() - 1; i >= 0; i--) {
            BoardPosition tempPos = new BoardPosition(i, c);
            if (whatsAtPos(tempPos) != ' ') {
                if (checkHorizWin(tempPos, whatsAtPos(tempPos))) {
                    return true;
                }
                else if (checkVertWin(tempPos, whatsAtPos(tempPos))) {
                    return true;
                }
                else {
                    return checkDiagWin(tempPos, whatsAtPos(tempPos));
                }
            }
        }
        return false;
    }

    /**
     * Checks whether there are no free board positions, resulting in a tie if there are none
     * @return whether the game has resulted in a tie
     * @pre [Game does not have a winner yet]
     * @post (checkTie = true [when no positions empty] OR checkTie = false) AND self = #self
     */
    public default boolean checkTie() {
        for (int i = 0; i < getNumColumns(); i++) {
            if (checkIfFree(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether a placed token results in a win horizontally on the board with number of tokens to win horizontally consecutive
     * @param pos represents the position the token was placed in
     * @param p represents the token character being placed in
     * @return Whether the token placed at a position results in a victory with number of tokens to win horizontally consecutive
     * @pre pos.getRow() {@code <} [rows of self] AND pos.getColumn() {@code <} [columns of self] AND p = [character of player in game]
     * @post (checkHorizWin = true [number of tokens to win in a row horizontally] OR checkHorizWin = false) AND self = #self
     */
    public default boolean checkHorizWin(BoardPosition pos, char p) {
        //checks to see if the last token placed (which was placed in position pos by player p) resulted in 5 in a row horizontally. Returns true if it does, otherwise false
        int winCondition = 1;

        //checks only 4 more in a direction and does not exceed the bounds of the position array
        for (int i = 1; i < getNumToWin() && pos.getColumn() + i < getNumColumns(); i++) {
            BoardPosition tempPos = new BoardPosition(pos.getRow(), pos.getColumn() + i);
            if (whatsAtPos(tempPos) == p) {
                winCondition++;
            }
            else {
                break;
            }
        }
        //checks the other direction from the initial piece
        for (int j = 1; j < getNumToWin() && pos.getColumn() - j >= 0; j++) {
            BoardPosition tempPos = new BoardPosition(pos.getRow(), pos.getColumn() - j);
            if (whatsAtPos(tempPos) == p) {
                winCondition++;
            }
            else {
                break;
            }
        }

        return winCondition == getNumToWin();
    }

    /**
     * Checks whether a placed token results in a win vertically on the board with number of tokens to win vertically consecutive
     * @param pos represents the position the token was placed in
     * @param p represents the token character being placed in
     * @return Whether the token placed at a position results in a victory with number of tokens to win vertically consecutive
     * @pre pos.getRow() {@code <} [rows of self] AND pos.getColumn() {@code <} [columns of self] AND p = [character of player in game]
     * @post (checkVertWin = true [number of tokens to win in a row vertically] OR checkVertWin = false) AND self = #self
     */
    public default boolean checkVertWin(BoardPosition pos, char p) {
        int winCondition = 1;

        //checks in a direction and does not exceed the bounds of the position array
        for (int i = 1; i < getNumToWin() && pos.getRow() + i < getNumRows(); i++) {
            BoardPosition tempPos = new BoardPosition(pos.getRow() + i, pos.getColumn());
            if (whatsAtPos(tempPos) == p) {
                winCondition++;
            }
            else {
                break;
            }
        }
        //checks the other direction from the initial piece
        for (int j = 1; j < getNumToWin() && pos.getRow() - j >= 0; j++) {
            BoardPosition tempPos = new BoardPosition(pos.getRow() - j, pos.getColumn());
            if (whatsAtPos(tempPos) == p) {
                winCondition++;
            }
            else {
                break;
            }
        }

        return winCondition == getNumToWin();
    }

    /**
     * Checks whether a placed token results in a win diagonally either direction on the board with number of tokens to win diagonally consecutive
     * @param pos represents the position the token was placed in
     * @param p represents the token character being placed in
     * @return Whether the token placed at a position results in a victory with number of tokens to win diagonally consecutive
     * @pre pos.getRow() {@code <} [rows of self] AND pos.getColumn() {@code <} [columns of self] AND p = [character of player in game]
     * @post (checkDiagWin = true [number of tokens in a row diagonally] OR checkDiagWin = false) AND self = #self
     */
    public default boolean checkDiagWin(BoardPosition pos, char p) {
        int winCondition = 1;

        //checks only 4 more in a direction and does not exceed the bounds of the position array
        for (int i = 1; i < getNumToWin() && pos.getRow() + i < getNumRows() && pos.getColumn() + i < getNumColumns(); i++) {
            BoardPosition tempPos = new BoardPosition(pos.getRow() + i, pos.getColumn() + i);
            if (whatsAtPos(tempPos) == p) {
                winCondition++;
            }
            else {
                break;
            }
        }
        //checks the other direction from the initial piece
        for (int j = 1; j < getNumToWin() && pos.getRow() - j >= 0 && pos.getColumn() - j >= 0; j++) {
            BoardPosition tempPos = new BoardPosition(pos.getRow() - j, pos.getColumn() - j);
            if (whatsAtPos(tempPos) == p) {
                winCondition++;
            }
            else {
                break;
            }
        }

        if (winCondition == getNumToWin()) {
            return true;
        }

        winCondition = 1;
        //checks in the other diagonal direction
        //checks only 4 more in a direction and does not exceed the bounds of the position array
        for (int i = 1; i < getNumToWin() && pos.getRow() + i < getNumRows() && pos.getColumn() - i >= 0; i++) {
            BoardPosition tempPos = new BoardPosition(pos.getRow() + i, pos.getColumn() - i);
            if (whatsAtPos(tempPos) == p) {
                winCondition++;
            }
            else {
                break;
            }
        }
        //checks the other direction from the initial piece
        for (int j = 1; j < getNumToWin() && pos.getRow() - j >= 0 && pos.getColumn() + j < getNumColumns(); j++) {
            BoardPosition tempPos = new BoardPosition(pos.getRow() - j, pos.getColumn() + j);
            if (whatsAtPos(tempPos) == p) {
                winCondition++;
            }
            else {
                break;
            }
        }
        return winCondition == getNumToWin();
    }

    /**
     * Shows whether specified player token is in the current position of the game board
     * @param pos represents the position that is being checked
     * @param player represents the token being searched for in the position
     * @return whether the specified player token is at a specified position
     * @pre pos.getRow() {@code <} [rows of self] AND pos.getColumn() {@code <} [columns of self] AND player = [valid character]
     * @post (isPlayerAtPos = true [when player is at pos] OR isPlayerAtPos = false) AND self = #self
     */
    public default boolean isPlayerAtPos(BoardPosition pos, char player) {
        return player == whatsAtPos(pos);
    }
}
