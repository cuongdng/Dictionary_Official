package main;

import java.util.TreeMap;

public class Dictionary {
    public static TreeMap<String, String> listWords = new TreeMap<String, String>();

    /**
     * AddWord to array.
     * @param newWord represent new word to add;
     * */
    public void addWord(Word newWord) {
        listWords.put(newWord.getWordTarget(), newWord.getWordExplain());
    }

}
