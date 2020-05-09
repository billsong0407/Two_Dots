/**
 * @file: UserInterface.java
 * @Author: Bill Song - songb10
 * @Date: March.31th, 2020
 * @Description: view module that displays the status of the game
 */

package src;

public class UserInterface{

    private static UserInterface visual = null;

    /** 
     * @brief constructor
     */
    private UserInterface(){}

    /**
     * @brief public static method for obtaining a single instance
     * @return an UserInterface object
     */
    public static UserInterface getInstance(){
        if (visual == null)
            return visual = new UserInterface();
        return visual;
    }

    /**
     * @brief display a welcome message
     */
    public void printWelcomeMessage(){
        System.out.println("-------------------------------------------------");
        System.out.println("                 Welcome to DOTS                 ");
        System.out.println("-------------------------------------------------");
    }

    /**
     * @brief display a prompt asking the user to select a game objective
     */
    public void printGameModePrompt(){
        System.out.println("                     New game                    ");
        System.out.println("                 -----------------               ");
        System.out.println("");
        System.out.println("Select Game Mode: max moves or time?");
        System.out.println("Enter m for max moves, t for time, e to exit");
    }

    /**
     * @brief display a prompt asking the user to enter coordinates
     */
    public void printCoordPrompt(){
        System.out.println("Enter coords with no space in between");
        System.out.println("Enter row number first then column, b to quit");
        System.out.println("Ex: 25 26 27");
    }

    /**
     * @brief display the game objective
     * @param message - the game objective (different based on objective) retrived from the model
     */
    public void printCondition(String message){
        System.out.println(message);
    }

    /**
     * @brief Display the board on the screen
     * @details print the board 90 degrees clock wise to where it is normally presented
     * @param boardT - the game board
     */
    public void printBoard(BoardT boardT){
        int[] legend = {0 ,1 ,2 ,3 ,4 ,5, 6, 7};
        int size = boardT.size;
        System.out.print("    ");
        for(int i : legend)
            System.out.print(i + "   ");
        System.out.println();
        System.out.println();
        for (int x = 0; x < size; x++){
            System.out.print(legend[x] + "   ");
            for (int y = 0; y < size; y++){
               System.out.print(boardT.getCell(x, y) + "   ");
            } 
            System.out.println();
        }
        System.out.println();
    }

    /**
     * @brief display an ending message after user choose to exit the game
     */
    public void printEndingMessage(){
        System.out.println("-------------------------------------------------");
        System.out.println("             Thank You For Playing !!!           ");
        System.out.println("-------------------------------------------------");
    }
}