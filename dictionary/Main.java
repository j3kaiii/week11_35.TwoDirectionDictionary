package dictionary;

public class Main {
    public static void main(String[] args) {
        // Test your dictionary here
        
        MindfulDictionary m = new MindfulDictionary("test/words.txt");
        m.load();
        //fill your dictionary at first
        //use m.add("word", "translate")
        
        m.save();
    }
}
