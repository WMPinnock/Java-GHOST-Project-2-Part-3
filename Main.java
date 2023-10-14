// Wayne Pinnock, CSCI 212, Spring Semester 2023
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    try {  
      // Read words
      File wordsFile = new File("words.txt");
      Scanner fileReader = new Scanner(wordsFile);  
      String[] words = new String[279496];  

      // Store words into array
      while(fileReader.hasNextLine()) {  
        for(int i = 0; i < 279496; i++){
          words[i] = fileReader.nextLine();
        }
      }

      // Store user input
      Scanner input = new Scanner(System.in);
      int players; 
      String name;
  
      System.out.println("Enter the number of players: ");
      players = input.nextInt();
      System.out.println("Enter the names of the players");
  
      // Instantiate player objects
      Player [] player = new Player[players + 1];
  
      for(int i = 0; i <= players; i++) {
        name = input.nextLine();
        player[i] = new Player(name);
      }
  
      // Game starts and will end when there is one player left
      String letters = "", userLetter = "";
      while(players > 1) {
        // i represents current player
        for(int i = 1; i <= players; i++) {
          if(players == 1) {
            break;
          }
          if(player[i].isEliminated()) {
            continue;
          }
          System.out.println(player[i].toString() + ", it's your turn. The letters are " + letters + ". Enter a letter or enter * to challenge.");
          userLetter = input.nextLine();
          
          // If current player challenges
          if(userLetter.equals("*")) {
            for(int s = 0; s < 279496; s++) {
              if(words[s].length() < letters.length()) {
                continue;
              }
              String wordSubstring = words[s].substring(0, letters.length());
              if(letters.equals(wordSubstring)) {
                player[i].loseRound();
                System.out.println(words[s] + " begins with those letters. " + player[i].toString() + " loses the round!");
              } else if(i == players) {
                player[1].loseRound();
                System.out.println("No word begins with those letters. " + player[1].toString() + " loses the round!");
                } else if(i == 1) {
                player[players].loseRound();
                System.out.println("No word begins with those letters. " + player[players].toString() + " loses the round!");
              } else if(i > 1 && i < players) {
                player[i-1].loseRound();
                System.out.println("No word begins with those letters. " + player[i-1].toString() + " loses the round!");
              }
              letters = "";
              userLetter = "";
              break;
            }
          }
            
            
            
          // If current player enters a letter
          letters += userLetter;
          
          // If current player creates a word
          for(int j = 0; j < 279496; j++) {
            if(letters.equals(words[j])) {
              player[i].loseRound();
              System.out.println(letters + " is a word. " + player[i].toString() + " loses the round!");
              letters = "";
              break;
            }
          }

          // If player reaches all five letters of GHOST
          if(player[i].isEliminated()) {
            System.out.println(player[i].toString() + " is eliminated!");
            players--;
          }
        }
      }
      System.out.println(player[players] + " is victorious!");

    } catch (FileNotFoundException e) {
      System.out.println("An error occured.");
      e.printStackTrace();
    }
  }
}
/* Explanation of Code

Read words and Store words into array
- Read each line from the words.txt file and store it into the words array

Store user input and Instantiate player objects
- Store how many players user wants in players variable and then pass name parameter to each object array constructor to name that particular object

i represents current player
- The current iteration of the for loop will represent the current player
- if there is only on player left then break out of the for loop
- if the currenet player is eliminated then skip the iteration (skip the current player)
- ask the current player for a letter

If current player challenges
- if the current player enters an asterisk, loop through each word in the wordss array. If the length of that word is less than the length of the current letters, then skip that iteration because it won't match letters.
- however if it does match the letter then store the substring of that word which begins from 0 to the length of the current letters and check if that substring of the word is equal to the letters
- if it does then a word does contain those letters and thus the player who challenged loses
- if not and the player is the last player, the beginning player loses
- if not and the player is the beginning player, the last player loses
- if not and the player is neither the last nor beginning player, the previous player loses
- afterwards letters is reset and the asterisk is removed
- 

If current player enters a letter
- check to see if the current letters is a word by looping through each element in the words array and checking if the current element is exactly the same as the current leters. If not then move on to the next player

If current player creates a word
- if the current letters matches a word from the words array then the player loses that round and gains a letter form the word GHOST

If player reaches all five letters of GHOST
- player is eliminated from the game and players variable is deducted by one












*/