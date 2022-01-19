import java.util.Scanner;
import java.util.InputMismatchException;

class DoubleDice {
  Scanner scnr = new Scanner(System.in);
  Double total = 100.00;
  Double bet = -1.00;
  boolean validInput;
  boolean win;
  boolean gameOver = false;

  public static void askUserForInput(Double total){
    System.out.print("You have $");
    System.out.printf("%.2f", total);
    System.out.println();
    System.out.println("How much would you like to bet (Enter 0 to quit)?");
  }

  public void validateInput(){
      try {
        bet = scnr.nextDouble();
        if(bet == 0){
          System.out.println("See you around, winner!");
          gameOver = true;
        } else if(bet > total){
            System.out.println("You can't bet more than you have! Try again.");
            validInput = false;
        } else if(bet < 0){
            System.out.println("Positive numbers only, please.");
            validInput = false;
        } else {
          validInput = true;
        }
      } catch (InputMismatchException e) {
          validInput = false;
          System.out.println("Number inputs only, please.");
      }
  }

  public void rollDice(){
    Die dieRoll1 = new Die();
    Die dieRoll2 = new Die();
    String result1 = dieRoll1.roll();
    String result2 = dieRoll2.roll();
    System.out.println("You rolled a " + result1 + " and " + result2);
    if(result1.equals(result2)){
      win = true;
      System.out.print("You win $");
      System.out.printf("%.2f", bet);
      System.out.println();
      total = total + bet;
    } else {
        win = false;
        System.out.print("You lose $");
        System.out.printf("%.2f", bet);
        System.out.println();
        total = total - bet;
    }
  }

  public void main(){
    while(!gameOver){
      askUserForInput(total);
      validateInput();
      if(validInput){
        rollDice();
      } else {
        do{
          scnr.nextLine();
          validateInput();
        } while(!validInput);
        if(validInput){
          rollDice();
        }
      }
      System.out.println();
      if(total <= 0){
        System.out.println("You lost all your money. See you next time.");
        gameOver = true;
        break;
      }
    }
  }
}