package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

public class DictionaryManagement {
    public static void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so luong tu vung: ");
        int n = sc.nextInt();
        String target;
        String explain;
        Dictionary newDic = new Dictionary();
        String t = sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap tu thu " + (i + 1) + ": ");
            target = sc.nextLine();
            explain = sc.nextLine();
            Word tmp = new Word(target, explain);
            newDic.addWord(tmp);
        }
        sc.close();
    }

    public static void insertFromFile() {
        try {
            BufferedReader buf = new BufferedReader(new FileReader("src/main/resources/data/E_V.txt"));
            Dictionary newDic = new Dictionary();
            String tmp;
            while((tmp = buf.readLine()) != null) {
                int position = tmp.indexOf('<');
                String target = tmp.substring(0, position);
                String explain = tmp.substring(position);
                target = target.trim().toLowerCase();
                explain = explain.trim().toLowerCase();
                newDic.addWord(new Word(target, explain));
            }
            buf.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    public static String dictionaryLookup(String eng_word) {
        return Dictionary.listWords.get(eng_word);
    }

    public static String editWord(String oldWord, String newWord) {
        Dictionary.listWords.put(oldWord, newWord);
        return newWord;
    }

    public static void addWord(String target, String explain) {
        target = target.trim().toLowerCase();
        explain = explain.trim().toLowerCase();
        Dictionary.listWords.put(target, explain);
    }

    public static boolean deleteWord(String tmp) {
        try {
            Dictionary.listWords.remove(tmp);
            System.out.println("Deleted!");
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public static void dictionaryExportToFile() {
        try {
            FileWriter writer = new FileWriter("output.txt");
            Set<String> keySet = Dictionary.listWords.keySet();
            for (String key : keySet) {
                writer.write(key + "   " + Dictionary.listWords.get(key) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
