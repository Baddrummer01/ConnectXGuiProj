//BENNETT STARKS
package cpsc2150.extendedConnectX.models;

public abstract class AbsGameBoard implements IGameBoard {

    /**
     * This method converts the game board to a string
     * @return The string format of the game board
     * @pre none
     * @post position = #position AND toString = [string representation of gameboard] AND self = #self
     */
    @Override
    public String toString() {
        //shows the entire gameboard
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getNumColumns(); i++) {
            if (i < 10) {
                sb.append("| " + i);
            }
            else {
                sb.append("|" + i);
            }
        }
        sb.append("|\n");
        for (int i = getNumRows() - 1; i >= 0; i--) {
            for (int j = 0; j < getNumColumns(); j++) {
                BoardPosition tempPos = new BoardPosition(i, j);
                sb.append("|" + whatsAtPos(tempPos) + " ");
            }
            sb.append("|\n");
        }
        return sb.toString();
    }
}
