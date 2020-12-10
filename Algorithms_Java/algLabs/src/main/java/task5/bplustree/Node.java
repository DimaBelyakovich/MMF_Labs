package task5.bplustree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

public class Node {
    private List<Key> keys;
    private List<Node> children;

    private Node prev;
    private Node next;
    private Node parent;

    public Node() {
        this.children = new ArrayList<>();
        this.keys = new ArrayList<>();
        prev = next = null;
    }

    public List<Key> getKeys() {
        return keys;
    }

    public void setKeys(List<Key> keys) {
        Iterator<Key> iter = keys.iterator();
        while (iter.hasNext()){
            this.keys.add(iter.next());
        }
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
                .add("keys=" + keys.toString())
                .toString();
    }
}
