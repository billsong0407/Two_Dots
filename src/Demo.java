/**
 * @file: Demo.java
 * @Author: Bill Song - songb10
 * @Date: March.31th, 2020
 * @Description: Runs the game (client code)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import src.BoardT;
import src.CellType;
import src.UserInterface;
import src.GameController;

public class Demo
{
   public static void main(String[] args) {

      BoardT boardT = new BoardT();
      UserInterface UI = UserInterface.getInstance();
      GameController game = GameController.getInstance(boardT, UI);
      game.runGame();
  }
}

