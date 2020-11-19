package task2;

import helpers.ReadFile;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String path = "/Users/dimabelyakovich/dev/MMF_Labs/Algorithms_Java/algLabs/src/main/resources/lines.txt";
    public static void main(String[] args) {
        List<String> words = ReadFile.readStringFromFile(new File(path));
        Collections.sort(words);
        System.out.println(words);
        Map result = AcceleratedLinearSearch.getSearchWord(words, "tonight");

        if(result.containsKey(-1)){
            System.out.println("Такого ключа в массиве нет");
            System.out.println(result.get(-1));
        }else {
            System.out.println(result.get(1));
        }
    }
}
