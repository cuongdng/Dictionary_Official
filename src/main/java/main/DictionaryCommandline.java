package main;

import java.util.TreeMap;
import java.util.Set;

public class DictionaryCommandline {
//    public static void showAllWords() {
//        System.out.println("No    |English          |Vietnamese");
//        for (int i = 0; i < Dictionary.listWords.size(); i++) {
//            System.out.printf("%-6d%c %-15s%c %-15s%n", (i+1), '|', Dictionary.listWords.get(i).getWordTarget(), '|', Dictionary.listWords.get(i).getWordExplain());
//        }
//    }

    public static TreeMap<String, String> dictionarySearcher(String keyWord) {
        TreeMap<String, String> listSearch = new TreeMap<String, String>();
        Set<String> keySet = Dictionary.listWords.keySet();
        for (String key : keySet) {
            if (key.startsWith(keyWord)) {
                listSearch.put(key, Dictionary.listWords.get(key));
            }
        };
        return listSearch;
    }

    public static void dictionaryBasic() {
        DictionaryManagement.insertFromFile();
        //showAllWords();
    }

    public static void dictionaryAdvanced() {
//        DictionaryManagement.insertFromFile();
//        showAllWords();
////        DictionaryManagement.dictionaryLookup();
//        DictionaryManagement.editWord();
//        DictionaryManagement.deleteWord();
////        showAllWords();
////        dictionarySearcher();
        DictionaryManagement.dictionaryExportToFile();
    }
}
