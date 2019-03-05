import java.io.*;
import java.util.Scanner;

public class Game {
    private int[] letterValue = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
    private DoubleHashingHashTable<WordInfo> myHashTable;

    public Game(){
        myHashTable = new DoubleHashingHashTable<>();
    }

    public int computeScore(WordInfo word) {
        int score = 0;
        //handle letter value
        for (int i=0; i < word.getLength(); i++){
            score += this.letterValue[word.getWord().charAt(i) - 'a']; //get the value of each letter
        }
        //handle word length
        if (word.getLength() > 2 && word.getLength() <= 8) score *= (word.getLength() - 2);
        else if (word.getLength() > 8) score *= 6;
        else score = 0; // if word is too short it's not worth anything

        //handle bonus for duplicate words
        if (word.getTimesSeen() == 0) score *= 5; //first time word is seen, it gets multiplied by 5
        else if (word.getTimesSeen() <= 5) score *= 4; //if in between 1 and 5 multiply by 4 and so on
        else if (word.getTimesSeen() <= 10) score *= 3;
        else if (word.getTimesSeen() <= 15) score *= 2;

        return score;
    }

    public void playGame(String filename){
        int totalScore = 0;

        //get input from the text files
        try {
            java.util.Scanner input = new Scanner(new File(filename));

            while (input.hasNext()) {
                WordInfo word = new WordInfo(input.next(),0); //get the word

                myHashTable.insert(word); //insert the word into the table

                word = myHashTable.find(word); //look at the word in the hash table
                totalScore += computeScore(word);//add the word score to the total
                word.incrementTimesSeen(); //increment that the word has been seen
            }
        }
        catch (java.io.IOException ex) {
            System.out.println("File exception: " + ex.getMessage());
        }
        //print out game and hash table stats
        System.out.println(filename + " Total Score: " + totalScore);
        System.out.println(filename + " Total finds " + myHashTable.getFindCount());
        System.out.println(filename + " Total probes " + myHashTable.getProbes());
        System.out.println(filename + " Total items in hash " + myHashTable.getOccupiedCt());
        System.out.println(filename + " Size of hash table " + myHashTable.capacity());

    }

    public String toString() {
        int LIMIT = 20;
        System.out.println("\n---First " + LIMIT + " non-null entries of the hash table---");
        return "\n"+ myHashTable.toString(LIMIT);
    }


    public static void main( String [ ] args ){
            //go through the different games in the text files
            Game g0 = new Game(  );
            g0.playGame("game0.txt" );
            System.out.println(g0);

            Game g1 = new Game(  );
            g1.playGame("game1.txt" );
            System.out.println(g1);

            Game g2 = new Game(  );
            g2.playGame("game2.txt" );
            System.out.println(g2);

            Game g3 = new Game(  );
            g3.playGame("game3.txt" );
            System.out.println(g3);

            Game g4 = new Game(  );
            g4.playGame("game4.txt" );
            System.out.println(g4);


    }

}
