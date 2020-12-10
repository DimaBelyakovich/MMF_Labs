package task5.bplustree;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Key {
    private double key;

    List<String> values;

    public Key(double key, String value){
        this.key = key;
        this.values = new ArrayList<>();
        this.values.add(value);
    }

    public Key(double key) {
        this.key = key;
        this.values = new ArrayList<>();
    }

    public double getKey() {
        return key;
    }

    public void setKey(double key) {
        this.key = key;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Key.class.getSimpleName() + "[", "]")
                .add("key=" + key)
                .add("values=" + values)
                .toString();
    }
}
