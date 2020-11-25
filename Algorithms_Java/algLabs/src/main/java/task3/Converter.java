package task3;

public class Converter {
    public static int convertToOtherNumSystem(int decimal_number, int numSystem){
        if (decimal_number <= 0 || numSystem > 10) {
            return 0;
        }
        else {
            return (decimal_number % numSystem + 10 *
                    convertToOtherNumSystem(decimal_number / numSystem, numSystem));
        }
    }
}
