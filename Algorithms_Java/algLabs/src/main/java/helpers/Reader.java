package helpers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Reader {
    final static Logger logger = LogManager.getLogger(Reader.class);

    public static List<String> readStringFromFile(File file){
        ArrayList<String> words = new ArrayList<>();
        try(Scanner reader = new Scanner(new FileReader(file))){
            while(reader.hasNext()){
                words.add(reader.next().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            logger.error(e);
        }
        return words.stream().distinct().collect(Collectors.toList());
    }

    public static int readIntFromConsole(){
        try(Scanner sc = new Scanner(System.in)){
            return sc.nextInt();
        } catch (InputMismatchException e){
            logger.error(e);
            return -1;
        }
    }
}
