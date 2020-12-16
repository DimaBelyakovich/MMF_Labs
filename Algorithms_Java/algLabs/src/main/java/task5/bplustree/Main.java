package task5.bplustree;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String path = "src/main/resources/bplustree.txt";

    public static void main(String[] args) {

        File inputFile = new File(path);
        try{
            Scanner sc = new Scanner(inputFile);

            BPlusTree tree = new BPlusTree();
            tree.initializer(Integer.parseInt(sc.nextLine()));

            while (sc.hasNextLine()){
                String newLine = sc.nextLine();

                String[] input = newLine.split("\\(|,|\\)");

                switch (input[0]){
                    case "Insert": {
                        tree.insert(Double.parseDouble(input[1]), input[2]);
                        break;
                    }
                    case "Search":{
                        if (input.length == 2) {
                            List<String> res = tree.search(Double.parseDouble(input[1]));
                            String searchResult = "";
                            if (null == res) {
                                System.out.println("kek");
                            } else {
                                Iterator<String> valueIterator = res.iterator();
                                while (valueIterator.hasNext()) {
                                    searchResult = searchResult + valueIterator.next() + ", ";
                                }
                                System.out.println(searchResult.substring(0, searchResult.length() - 2));
                            }
                        }
                        break;
                    }
                }
            }

            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
