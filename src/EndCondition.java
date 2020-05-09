/**
 * @file: EndCondition.java
 * @Author: Bill Song - songb10
 * @Date: March.31th, 2020
 * @Description: generic interface for setting the game ending condition used in Strategy pattern
 */

package src;

public interface EndCondition{

    // Declare the interface methods
    public boolean gameStatus(int num, CellType color, int moves);
    public String message(int moves);
}