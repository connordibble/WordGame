public class WordInfo {
    private String word;
    private int length;
    private int timesSeen;

    WordInfo(String word, int timesSeen){
        this.word = word.toLowerCase(); //ensure that the word is always processed as lower case
        this.length = word.length(); //get the length of the word
        this.timesSeen = timesSeen;
    }

    @Override
    //override the equals so we are comparing based on words
    public boolean equals(Object word2){
        if (word2==this) return true; //if same object return true

        if (!(word2 instanceof WordInfo)) return false; //check to see if its a WordInfo

        WordInfo wordInfo2 = (WordInfo)word2;//cast as a WordInfo
        return (this.word.compareTo(wordInfo2.word) == 0);
    }

    @Override
    public int hashCode() {
        return this.word.hashCode(); //use the hash code of the word
    }

    @Override
    public String toString(){
        return (this.word + "(" + (this.timesSeen) + ")"); //prints the word and times we've seen it
    }

    public void incrementTimesSeen() {
        this.timesSeen ++;
    }

    public int getTimesSeen() {
        return timesSeen;
    }

    public int getLength() {
        return length;
    }

    public String getWord() {
        return word;
    }
}


