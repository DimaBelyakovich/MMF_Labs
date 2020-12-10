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
            BufferedWriter bw = openNewFile();

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
                            writeSearchByKey(res, bw);
                        }
                        break;
                    }
                }
            }

            sc.close();
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedWriter openNewFile() throws IOException {
        File file = new File("src/main/resources/search_result.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        return bw;
    }

    private static void writeSearchByKey(List<String> res, BufferedWriter bw) throws IOException {
        String newLine = "";
        if (null == res) {
            bw.write("f*ck you");
        } else {
            Iterator<String> valueIterator = res.iterator();
            while (valueIterator.hasNext()) {
                newLine = newLine + valueIterator.next() + ", ";
            }
            bw.write(newLine.substring(0, newLine.length() - 2));
        }
        bw.newLine();
    }
}
