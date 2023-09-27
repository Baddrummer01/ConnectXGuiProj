//BENNETT STARKS
package cpsc2150.extendedConnectX.models;

public class BoardPosition {
    /**
    * @invariant 0 <= Row <= 8  AND 0 <= Column <= 6
    */
    private int Row;
    private int Column;
    /**
     * Constructs an object of cpsc2150.extendedConnectX.models.BoardPosition
     * @param r represents the rows
     * @param c represents the columns
     * @pre
     *  0 <= r <= 8 AND 0 <= c <= 6
     * @post
     *  Row = r AND Column = c
     */
    public BoardPosition(int r, int c) {
        Row = r;
        Column = c;
    }
    /**
     * This method retrieves the row of a board position
     * @return
     *  The row position on the board
     * @pre none
     * @post
     *  Row = #Row AND getRow = Row
     */
    public int getRow(){
        return Row;
    }

    /**
     * This method retrieves the column of a board position
     * @return
     *  The column position on the board
     * @pre none
     * @post
     *  Column = #Column AND getColumn = Column
     */
    public int getColumn(){
        return Column;
    }

    /**
     * This method determines whether something is equal
     * @param
     *  obj Object being tested against for equality
     * @return
     *  True or false: Whether the objects of comparison are equal
     * @pre none
     * @post
     *  obj = #obj AND equals(Object obj) = true OR false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BoardPosition) {
            BoardPosition tempPos = (BoardPosition) obj;
            if (tempPos.getRow() == this.getRow() && tempPos.getColumn() == this.getColumn()) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * This method converts the row and column to a string
     * @return
     *  The string format of the row and column position
     * @pre none
     * @post
     *  Row = #Row AND Column = #Column AND toString = string
     */
    @Override
    public String toString() {
        return getRow() + "," + getColumn();
    }
}