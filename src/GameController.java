/**
 * @file: GameController.java
 * @Author: Bill Song - songb10
 * @Date: March.31th, 2020
 * @Description: controller module that handles inputs and links model and view module
 */

package src;

// Import java libraries
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GameController{

    // Define state variables
    private BoardT model;
    private UserInterface view;
    private static GameController controller = null;

    // Define environment variable
    private Scanner keyboard = new Scanner(System.in);

    /**
     * @brief constructor
     * @param model - model module (BoardT)
     * @param view - view module (UseInterface)
     */
    private GameController(BoardT model, UserInterface view){
        this.model = model;
        this.view = view;
    }

    /**
     * @brief public static method for obtaining a single instance
     * @return the single GameController object
     */
    public static GameController getInstance(BoardT model, UserInterface view) 
    { 
        if (controller == null) 
            controller = new GameController(model, view); 
  
        return controller; 
    } 

    /**
     * @brief initialize the game
     */
    public void initializeGame(){
        this.model = new BoardT();
    }

    /**
     * @brief takes the input from the user through keyboard
     * @return the input
     */
    public String readGameModeInput(){
        String gameMode = " ";
        gameMode = keyboard.nextLine();
        gameMode = gameMode.toLowerCase();
        try{
            if(!gameMode.equals("m") && !gameMode.equals("t") && !gameMode.equals("e"))
                throw new IllegalArgumentException();
        }
        catch (IllegalArgumentException e) {
            System.out.println("Invalid Input");
            
        }
        return gameMode;
    }

    /**
     * @brief get coordinates input from the user
     * @return the input
     */
    public String readCoord(){
        String input = "";
        input = keyboard.nextLine();
        return input;
    }

    /**
     * 
     * @return gets the game status from the board
     */
    public boolean getStatus(){
        return this.model.getStatus();
    }

    /**
     * @brief Sets the game ending condtion
     * @param input - the game objective chosen by the user
     */
    public void setGameMode(String input){
        if (input.equals("m"))
            model.setEndCondition(new EndByMoves());
        else if(input.equals("t")){
            EndByTime cond = new EndByTime();
            model.setEndCondition(cond);
            cond.startCountDown();
        }
    }

    /**
     * @brief elimates a sequence of dots entered by the user
     * @param coords - the sequence of coords where the dots are going to be eliminated
     */
    public void setElimination(ArrayList<String> coords){
        model.remove(coords);
        model.replaceEliminated(coords);
    }

    /**
     * @brief updates the view module to display a welcome message
     */
    public void displayWelcomeMessage(){
        view.printWelcomeMessage();
    }

    /**
     * @brief updates the view module to display the board
     */
    public void displayBoard(){
        view.printBoard(model);
    }

    /**
     * @brief updates the view module to display the game objective
     */
    public void displayCondition(){
        view.printCondition(model.getMessage());
    }

    /**
     * @brief updates the view module to display a ending message
     */
    public void displayEnding(){
        view.printEndingMessage();
    }

    /**
     * @brief updates the view module to display a prompt asking the user to select a game objective
     */
    public void displayGameModePrompt(){
        view.printGameModePrompt();
    }

    /**
     * @brief updates the view module to display a prompt asking the user to enter coordinates
     */
    public void displayCoordPrompt(){
        view.printCoordPrompt();
    }

    /**
     * @brief runs the game
     */
    public void runGame(){
        String input = "";
        String gameMode = "";
        displayWelcomeMessage();
        do{
            displayGameModePrompt();
            initializeGame();
            do{
            gameMode = readGameModeInput();
            setGameMode(gameMode);
            }while(!gameMode.equals("m") && !gameMode.equals("t") && !gameMode.equals("e"));
            input = " ";
            while(getStatus() == true && !(input.equals("b")) && !(gameMode.equals("e"))){
            System.out.println("");
            displayCondition();
            System.out.println("");
            displayBoard();
            displayCoordPrompt();
            try{
                input = readCoord();
                if (input.equals("b"))
                    continue;
                String delimiter = " ";
                String[] tempArray = input.split(delimiter);
                ArrayList<String> coords = new ArrayList<String>(Arrays.asList(tempArray));
                setElimination(coords);
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("Invalid Input");
            }
            catch (IllegalArgumentException e){
                System.out.println("Invalid Input");
            }

            }
        }while(!gameMode.equals("e"));
        displayEnding();
        System.exit(0);
    }
}