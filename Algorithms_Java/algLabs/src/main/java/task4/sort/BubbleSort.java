package task4.sort;

public class BubbleSort {

    public static void bubbleSortOptimized(int[] notSortedList){
        boolean swapped;
        int n = notSortedList.length;
        for(int i = 0; i < n; i++){
            swapped = false;
            for(int j = 0; j < n-i-1; j++){
                if(notSortedList[j] > notSortedList[j+1]){
                    int tmp = notSortedList[j];
                    notSortedList[j] = notSortedList[j+1];
                    notSortedList[j+1] = tmp;
                    swapped = true;
                }
            }
            if(swapped == false)
                break;
        }
    }

    public static void bubbleSort(int[] notSortedList){
        int n = notSortedList.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (notSortedList[j] > notSortedList[j+1])
                {
                    int temp = notSortedList[j];
                    notSortedList[j] = notSortedList[j+1];
                    notSortedList[j+1] = temp;
                }
    }
}
