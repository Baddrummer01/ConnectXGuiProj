//BENNETT STARKS
package cpsc2150.extendedConnectX.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class GameBoardMem extends AbsGameBoard implements IGameBoard {
    /**
     * @invariant No duplicate boardPositions in the map
     *
     * @correspondence self = Map{@code <}Character, List{@code <}BoardPosition{@code >>}
     */

    static private Map<Character, List<BoardPosition>> position;
    static private int numRows;
    static private int numColumns;
    static private int numToWin;

    /**
     * This method is the constructor for the GameBoardMem class and initializes a gameboard in a map format
     * @param r represents the rows of the gameboard
     * @param c represents the columns of the gameboard
     * @param n represents the number of tokens in a row needed to win
     * @pre r {@code <=} 100 AND r {@code >} 2 AND c {@code <=} 100 AND c {@code >} 2 AND n {@code <=} 25 AND n {@code >} 2 AND n {@code <=} r AND n {@code <=} c
     * @post position = HashMap AND numRows = r AND numColumns = c AND numToWin = n
     */
    public GameBoardMem(int r, int c, int n) {
        position = new HashMap<>();
        numRows = r;
        numColumns = c;
        numToWin = n;
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

    public char whatsAtPos(BoardPosition pos) {
        for (Map.Entry<Character, List<BoardPosition>> m: position.entrySet()) {
            List<BoardPosition> p = m.getValue();
            if (p.contains(pos)) {
                return m.getKey();
            }
        }
        //returns an empty char if no value was returned before
        return ' ';
    }

    public void placeToken(char p, int c) {
        BoardPosition test;
        for (int i = 0; i < numRows; i++) {
            test = new BoardPosition(i, c);
            //makes sure the area is unoccupied
            if (whatsAtPos(test) == ' ') {
                List<BoardPosition> boardPos;
                //makes a new array if one is not already present. Otherwise gets the array from the key
                if (position.containsKey(p)) {
                    boardPos = position.get(p);
                }
                else {
                    boardPos = new ArrayList<>();
                }
                boardPos.add(test);
                position.put(p, boardPos);
                break;
            }
        }
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if (position.containsKey(player)) {
            List<BoardPosition> p = position.get(player);
            return p.contains(pos);
        }
        else {
            return false;
        }
    }
}
