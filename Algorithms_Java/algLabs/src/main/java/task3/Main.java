package task3;

import helpers.Reader;

class Main {
    public static void main(String args[]) {
        int decimal_number = Reader.readIntFromConsole();

        long start = System.nanoTime();
        int result = Converter.convertToOtherNumSystem(decimal_number,8);
        long end = System.nanoTime();

        System.out.println(result);
        System.out.println("Time: "+ (end-start));
    }
}


