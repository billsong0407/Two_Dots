/**
 * @File: BoardT.java
 * @Author: Bill Song - songb10
 * @Date: March.31th, 2020
 * @Description: a model module for storing the state and status of the game
 */

package src;

// Import java libraries
import java.util.ArrayList;
import java.util.Arrays;

public class BoardT{

    // State Variables
    private ArrayList<ArrayList<CellType>>board;
    private boolean status;
    private EndCondition condition;
    private int moves;

    // Exported constants
    public final static int size = 8;

    /**
     * @brief constructor
     * @details generates a board with random value
     */
    public BoardT(){
        this.status = true;
        this.moves = 0;
        this.board = new ArrayList<ArrayList<CellType>>();
        for(int a = 0; a < size; a++){
            this.board.add(new ArrayList<CellType>(Arrays.asList(generateRandomCell(),generateRandomCell(),generateRandomCell(),generateRandomCell(),
                                                                 generateRandomCell(),generateRandomCell(),generateRandomCell(),generateRandomCell())));
        }
        
    }

    /**
     * @brief constructor
     * @details User can choose to use their own board. Also easier for testing since the values are not randomized
     * @param b - a predefined board
     */
    public BoardT(ArrayList<ArrayList<CellType>>b){
        this.status = true;
        this.moves = 0;
        this.board = b;
    }

    /**
     * @brief Gets the cell type at given x and y 
     * @param x - row number
     * @param y - column number
     * @throws IndexOutOfBoundsException - if entered coordinate is located outside of the board
     * @return a CellType
     */
    public CellType getCell(int x, int y){
        if (validateCell(x, y) == false)
            throw new IndexOutOfBoundsException("Invalid coordinate");
        return this.board.get(y).get(7 - x);
    }

    /**
     * @brief the status of the game
     * @return status
     */
    public boolean getStatus(){
        return this.status;
    }

    /**
     * @brief sets the game ending condition for the game
     * @param condition - the game objective
     */
    public void setEndCondition(EndCondition condition){
        this.condition = condition;
    }

    /**
     * 
     * @return the current objective of the game
     */
    public String getMessage(){
        return condition.message(this.moves);
    }

    /**
     * @brief
     * @param input
     * @throws IllegalArgumentException - if the input one element long, or the dots entered are connected diagonally or even not connected at all
     */
    public void remove(ArrayList<String> input){
        if (input.size() == 1)
            throw new IllegalArgumentException("Input is invalid");
        if (validateConectivity(input) == false)
            throw new IllegalArgumentException("Input is invalid");
        CellType color = markCell(input);
        for(int i = 0; i < size; i++){
            this.board.get(i).removeAll(Arrays.asList(CellType.E));
        }
        this.moves++;
        this.status = condition.gameStatus(input.size(), color, moves);
    }

    /**
     * @brief Replaces the eliminated dots with newly randomly generated dots
     * @param input - an sequence of coordinates that the dots are going to be elimiated
     */
    public void replaceEliminated(ArrayList<String> input){
        
        
        for(int i = 0; i < size; i++){
            for (int b = board.get(i).size(); b < size; b ++){
                this.board.get(i).add(b, generateRandomCell());
            }
        }
    }

    private CellType generateRandomCell(){
        int num = (int)(Math.random() * (5 + 1))+0;
        if(num == CellType.R.ordinal())
            return CellType.R;
        else if (num == CellType.B.ordinal())
            return CellType.B;
        else if (num == CellType.G.ordinal())
            return CellType.G;
        else if (num == CellType.O.ordinal())
            return CellType.O;
        else 
            return CellType.P;
    }

    private boolean validateCell(int x, int y){
        if (x >= size || y >= size || x < 0 || y < 0) 
            return false;
        return true;
    }

    private boolean validateConectivity(ArrayList<String> input){
        int x, x1;
        int y, y1;
        x1 = Character.getNumericValue(input.get(0).charAt(0));
        y1 = Character.getNumericValue(input.get(0).charAt(1));
        for(int i = 0; i < input.size(); i++){
            x = Character.getNumericValue(input.get(i).charAt(0));
            y = Character.getNumericValue(input.get(i).charAt(1));
            if (validateCell(x, y) == false)
                return false;
            if(i != 0){
                if(getCell(x, y) != getCell(x1, y1))
                    return false;
                else if((x + 1 != x1 && x - 1 != x1) && (y == y1))
                    return false;
                else if((y + 1 != y1 && y - 1 != y1) && (x == x1))
                    return false;
            }
            x1 = x;
            y1 = y;
        }
        return true;
    }

    private CellType markCell(ArrayList<String> input){
        int x, y;
        CellType color = null;
        
        for(int i = 0; i < input.size(); i++){
            x = Character.getNumericValue(input.get(i).charAt(0));
            y = Character.getNumericValue(input.get(i).charAt(1));
            color = getCell(x, y);
            this.board.get(y).set(7 - x, CellType.E);
        }
        return color;
    }

}