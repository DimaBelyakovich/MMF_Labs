package helpers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReadFile {

    public static List<String> readStringFromFile(File file){
        ArrayList<String> words = new ArrayList<>();
        try(Scanner reader = new Scanner(new FileReader(file))){
            String word;
            while(reader.hasNext()){
                words.add(reader.next().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return words.stream().distinct().collect(Collectors.toList());
    }

}
