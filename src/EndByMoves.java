/**
 * @file: EndByMoves.java
 * @Author: Bill Song - songb10
 * @Date: March.31th, 2020
 * @Description: module implementing EndCondition interface
 */

package src;

public class EndByMoves implements EndCondition{

    // Declare state variables
    private final int maxMoves;
    private int redTargert;
    private int orangeTargert;

    /**
     * @brief constructor
     */
    public EndByMoves(){
        this.maxMoves = 10;
        this.redTargert = 5;
        this.orangeTargert = 5;
    }

    /**
     * @brief changes the game status
     * @param num - the number of dots eliminated during this one move
     * @param color - the color of the eliminated dot
     * @param moves - the number of moves used already
     */
    @Override
    public boolean gameStatus(int num, CellType color, int moves){
        if(color == CellType.R)
            redTargert = redTargert - num;
        else if(color == CellType.O)
            orangeTargert = orangeTargert - num;
        return !((redTargert <= 0 && orangeTargert <= 0)|| (maxMoves - moves) <= 0);
    }

    /**
     * @brief the string representing the game objective and amount of moves and dots left until game terminates
     * @param moves - the number of moves used already
     */
    @Override
    public String message(int moves){
        return String.format("Target: # of Red Dots left: %d , # of Orange Dots left: %d\n Number of moves left: %d", redTargert, orangeTargert, (maxMoves - moves));
    }


}