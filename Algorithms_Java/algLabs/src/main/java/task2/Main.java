package task2;

import helpers.Reader;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String path = "/Users/dimabelyakovich/dev/MMF_Labs/Algorithms_Java/algLabs/src/main/resources/lines.txt";
    public static void main(String[] args) {
        List<String> words = Reader.readStringFromFile(new File(path));
        Collections.sort(words);
        System.out.println(words);

        Map resultTonight = AcceleratedLinearSearch.getSearchWord(words, "tonight");
        Map resultA = AcceleratedLinearSearch.getSearchWord(words, "a");
        Map resultMine = AcceleratedLinearSearch.getSearchWord(words, "mine");
        Map resultSky = AcceleratedLinearSearch.getSearchWord(words, "sky");
        //Map resultNoWord = AcceleratedLinearSearch.getSearchWord(words, "tonightddddd");

        System.out.println("Time: " + resultA.get(1));
        System.out.println("Time: " + resultMine.get(1));
        System.out.println("Time: " + resultSky.get(1));
        System.out.println("Time: " + resultTonight.get(1));
        //System.out.println("Time: " + resultNoWord.get(-1));
    }
}
