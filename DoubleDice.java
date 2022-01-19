import java.util.Scanner;
import java.util.InputMismatchException;

class DoubleDice {
  Scanner scnr = new Scanner(System.in);
  //total variable - holds amount of user's bank
  Double total = 100.00;
  //bet variable - will store the user's bet input
  Double bet = -1.00;
  //validInput variable to record if the user gave a viable input
  boolean validInput;
  //gameOver variable - has the game reached an end?
  boolean gameOver = false;

  public static void askUserForInput(Double total){
    System.out.print("You have $");
    System.out.printf("%.2f", total);
    System.out.println();
    System.out.println("How much would you like to bet (Enter 0 to quit)?");
  }

  public void validateInput(){
      //check if user has entered a value of the correct type
      try {
        bet = scnr.nextDouble();
        //checks to make sure bet is valid
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
          //if the bet is valid
          validInput = true;
        }
      } catch (InputMismatchException e) {
          //if user inputs an non numeric data type
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
      System.out.print("You win $");
      System.out.printf("%.2f", bet);
      System.out.println();
      //add bet amount to the total
      total = total + bet;
    } else {
        System.out.print("You lose $");
        System.out.printf("%.2f", bet);
        System.out.println();
        //deduct bet from the total
        total = total - bet;
    }
  }

  public void main(){
    while(!gameOver){
      askUserForInput(total);
      validateInput();
      //if input is valid
      if(validInput){
        //roll the dice
        rollDice();
      } else {
        //reprompt for a new bet
        do{
          scnr.nextLine();
          validateInput();
        } while(!validInput);
        //roll when valid input is found
        if(validInput){
          rollDice();
        }
      }
      System.out.println();
      //check for a loss
      if(total <= 0){
        System.out.println("You lost all your money. See you next time.");
        gameOver = true;
      }
    }
  }
}