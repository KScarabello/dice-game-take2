import java.util.Random;

class Die {
  public String roll(){
    Random random = new Random();
    //get random number between 0 and 5
    int rollResult = random.nextInt(5);
    return toString(rollResult);
  }

  //convert the result into a displayable string value
  public String toString(int result){
    switch(result){
      case 0:
        return "one";
      case 1:
        return "two";
      case 2:
        return "three";
      case 3:
        return "four";
      case 4:
        return "five";
      case 5:
        return "six";
      default:
        return "error - invalid integer passed";
    }
  }

}