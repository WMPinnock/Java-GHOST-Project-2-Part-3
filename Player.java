// Wayne Pinnock, CSCI 212, Spring Semester 2023
public class Player {
  // Keep track of name and letters of a player
  private String name, letters = "", ghost = "GHOST";
  private int ghostCounter = 0;

  // Constructor whose parameter is the name of the player, it assigns the object's name to that name given by the player when the object is created
  public Player(String n) {
    this.name = n;
  }

  public String getName() {
    return name;
  }

  // Three public instance methods:
  // loseRound, which assigns the player an additional letter
  public void loseRound() {
    this.letters += ghost.charAt(ghostCounter);
    this.ghostCounter++;
  }

  // isEliminated, which returns true if the player has all five letters
  public boolean isEliminated() {
    if(this.letters.equals("GHOST")) {
      return true;
    } else return false;
  }

  // toString, which returns a string composed of the player's name and letters (whenever they win or lose)
  public String toString() {
    return (this.name + "(" + this.letters + ")");
  }

}