package task2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcceleratedLinearSearch {

    public static Map<Integer, Long> getSearchWord(List<String> words, String word){
        Map<Integer, Long> result = new HashMap<>();
        long start = System.nanoTime();
        int i = 0;
        if(!word.isEmpty()){
            while (i < words.size()){
                if(word.equals(words.get(i))){
                    long end = System.nanoTime();
                    result.put(1,end-start);
                    return result;
                }
                i++;
            }
        }
        long end = System.nanoTime();
        result.put(-1, end-start);
        return result;
    }
}
