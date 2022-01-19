import java.util.Scanner;
import java.util.InputMismatchException;

class DoubleDice {
  //initializing scanner
  Scanner scnr = new Scanner(System.in);
  //total variable - holds amount of user's bank
  Double total = 100.00;
  //bet variable - will store the user's bet input
  Double bet = -1.00;
  //validInput variable to record if the user gave a viable input
  boolean validInput;
  //gameOver variable - has the game reached an end?
  boolean gameOver = false;

  //method to prompt the user for a bet
  public static void askUserForInput(Double total){
    System.out.print("You have $");
    System.out.printf("%.2f", total);
    System.out.println();
    System.out.println("How much would you like to bet (Enter 0 to quit)?");
  }

  //method to determine if the user has provided a valid input, and a roll can commence
  public void validateInput(){
      //check if user has entered a value of the correct type
      try {
        bet = scnr.nextDouble();
        //if user enters 0, they wish to end the game, say goodbye
        if(bet == 0){
          System.out.println("See you around, winner!");
          //set gameOver to true to stop asking user for a new bet
          gameOver = true;
          //if user bets more than they have, tellthe
        } else if(bet > total){
            System.out.println("You can't bet more than you have! Try again.");
            //set validInput to false, so it knows to ask for a new input
            validInput = false;
        } else if(bet < 0){
            //if user enters a negative value
            System.out.println("Positive numbers only, please.");
            validInput = false;
        } else {
          //set validInput to false, so it knows to ask for a new input
          validInput = true;
        }
      } catch (InputMismatchException e) {
          //if user inputs a string or other non numeric value. set validInput to false to ask for another input
          validInput = false;
          System.out.println("Number inputs only, please.");
      }
  }

  //method rolls to dice, gives the user feed back, and adds/deducts from the total as needed
  public void rollDice(){
    //create first die
    Die dieRoll1 = new Die();
    //create second die
    Die dieRoll2 = new Die();
    //get result of the die roll for die 1
    String result1 = dieRoll1.roll();
    //get result of the die roll for die 2
    String result2 = dieRoll2.roll();
    //display roll results to the user
    System.out.println("You rolled a " + result1 + " and " + result2);
    //if both die roll the same value
    if(result1.equals(result2)){
      //tell the user they won
      System.out.print("You win $");
      System.out.printf("%.2f", bet);
      System.out.println();
      //add bet amount to the total
      total = total + bet;
    } else {
        //tell the user they lost
        System.out.print("You lose $");
        System.out.printf("%.2f", bet);
        System.out.println();
        //deduct bet from the total
        total = total - bet;
    }
  }

  //main method that initiates a new game, calls the appropriate methods
  public void main(){
    //while the game is still active
    while(!gameOver){
      //ask for input
      askUserForInput(total);
      //make sure it is ready for a roll
      validateInput();
      //if input is good...
      if(validInput){
        //roll the dice
        rollDice();
      } else {
        //if user has not provided a valid input, recheck the new input value until a valid one is given
        do{
          scnr.nextLine();
          validateInput();
        } while(!validInput);
        //when the user gives a valid input, roll the dice
        if(validInput){
          rollDice();
        }
      }
      System.out.println();
      //check for a loss - if total is zero, tell user they lost
      if(total <= 0){
        System.out.println("You lost all your money. See you next time.");
        //set gameOver variable to true to stop the loop
        gameOver = true;
      }
    }
  }
}