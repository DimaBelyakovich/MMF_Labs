package task5.bplustree;

import java.util.ArrayList;
import java.util.List;

public class BPlusTree {
    private int m;
    private Node root;

    public BPlusTree() {
    }

    public void initializer(int m) {
        this.m = m;
        this.root = null;
    }

    public void insert(double key, String value) {
        if (null == this.root) {
            Node newNode = new Node();
            newNode.getKeys().add(new Key(key, value));
            this.root = newNode;
            this.root.setParent(null);
        } else if (this.root.getChildren().isEmpty() && this.root.getKeys().size() < (this.m - 1)) {
            insertWithinExternalNode(key, value, this.root);
        } else {
            Node curr = this.root;
            while (!curr.getChildren().isEmpty()) {
                curr = curr.getChildren().get(binarySearchWithinInternalNode(key, curr.getKeys()));
            }
            insertWithinExternalNode(key, value, curr);
            if (curr.getKeys().size() == this.m) {
                splitExternalNode(curr, this.m);
            }
        }
    }

    private void insertWithinExternalNode(double key, String value, Node node) {
        int indexOfKey = binarySearchWithinInternalNode(key, node.getKeys());
        if (indexOfKey != 0 && node.getKeys().get(indexOfKey - 1).getKey() == key) {
            node.getKeys().get(indexOfKey - 1).getValues().add(value);
        } else {
            Key newKey = new Key(key, value);
            node.getKeys().add(indexOfKey, newKey);
        }
    }

    private void splitExternalNode(Node curr, int m) {
        int midIndex = m / 2;

        Node middle = new Node();
        Node rightPart = new Node();

        rightPart.setKeys(curr.getKeys().subList(midIndex, curr.getKeys().size()));
        rightPart.setParent(middle);

        middle.getKeys().add(new Key(curr.getKeys().get(midIndex).getKey()));
        middle.getChildren().add(rightPart);

        curr.getKeys().subList(midIndex, curr.getKeys().size()).clear();

        boolean firstSplit = true;

        splitInternalNode(curr.getParent(), curr, m, middle, firstSplit);
    }

    private void splitInternalNode(Node curr, Node prev, int m, Node toBeInserted, boolean firstSplit) {
        if (null == curr) {
            this.root = toBeInserted;

            int indexForPrev = binarySearchWithinInternalNode(prev.getKeys().get(0).getKey(), toBeInserted.getKeys());
            prev.setParent(toBeInserted);
            toBeInserted.getChildren().add(indexForPrev, prev);
            if (firstSplit) {
                if (indexForPrev == 0) {
                    toBeInserted.getChildren().get(0).setNext(toBeInserted.getChildren().get(1));
                    toBeInserted.getChildren().get(1).setPrev(toBeInserted.getChildren().get(0));
                } else {
                    toBeInserted.getChildren().get(indexForPrev + 1)
                            .setPrev(toBeInserted.getChildren().get(indexForPrev));
                    toBeInserted.getChildren().get(indexForPrev - 1)
                            .setNext(toBeInserted.getChildren().get(indexForPrev));
                }
            }
        }else {
            mergeInternalNodes(toBeInserted, curr);
            if (curr.getKeys().size() == m) {

                int midIndex = (int) Math.ceil(m / 2.0) - 1;
                Node middle = new Node();
                Node rightPart = new Node();

                rightPart.setKeys(curr.getKeys().subList(midIndex + 1, curr.getKeys().size()));
                rightPart.setParent(middle);

                middle.getKeys().add(curr.getKeys().get(midIndex));
                middle.getChildren().add(rightPart);

                List<Node> childrenOfCurr = curr.getChildren();
                List<Node> childrenOfRight = new ArrayList<>();

                int lastChildOfLeft = childrenOfCurr.size() - 1;

                for (int i = childrenOfCurr.size() - 1; i >= 0; i--) {
                    List<Key> currKeysList = childrenOfCurr.get(i).getKeys();
                    if (middle.getKeys().get(0).getKey() <= currKeysList.get(0).getKey()) {
                        childrenOfCurr.get(i).setParent(rightPart);
                        childrenOfRight.add(0, childrenOfCurr.get(i));
                        lastChildOfLeft--;
                    } else {
                        break;
                    }
                }

                rightPart.setChildren(childrenOfRight);

                curr.getChildren().subList(lastChildOfLeft + 1, childrenOfCurr.size()).clear();
                curr.getKeys().subList(midIndex, curr.getKeys().size()).clear();

                splitInternalNode(curr.getParent(), curr, m, middle, false);
            }
        }
    }

    private void mergeInternalNodes(Node mergeFrom, Node mergeInto) {
        Key keyToBeInserted = mergeFrom.getKeys().get(0);
        Node childToBeInserted = mergeFrom.getChildren().get(0);

        int indexToBeInsertedAt = binarySearchWithinInternalNode(keyToBeInserted.getKey(), mergeInto.getKeys());
        int childInsertPos = indexToBeInsertedAt;
        if (keyToBeInserted.getKey() <= childToBeInserted.getKeys().get(0).getKey()) {
            childInsertPos = indexToBeInsertedAt + 1;
        }
        childToBeInserted.setParent(mergeInto);
        mergeInto.getChildren().add(childInsertPos, childToBeInserted);
        mergeInto.getKeys().add(indexToBeInsertedAt, keyToBeInserted);

        if (!mergeInto.getChildren().isEmpty() && mergeInto.getChildren().get(0).getChildren().isEmpty()) {

            if (mergeInto.getChildren().size() - 1 != childInsertPos
                    && mergeInto.getChildren().get(childInsertPos + 1).getPrev() == null) {
                mergeInto.getChildren().get(childInsertPos + 1).setPrev(mergeInto.getChildren().get(childInsertPos));
                mergeInto.getChildren().get(childInsertPos).setNext(mergeInto.getChildren().get(childInsertPos + 1));
            }
            else if (0 != childInsertPos && mergeInto.getChildren().get(childInsertPos - 1).getNext() == null) {
                mergeInto.getChildren().get(childInsertPos).setPrev(mergeInto.getChildren().get(childInsertPos - 1));
                mergeInto.getChildren().get(childInsertPos - 1).setNext(mergeInto.getChildren().get(childInsertPos));
            }

            else {
                mergeInto.getChildren().get(childInsertPos)
                        .setNext(mergeInto.getChildren().get(childInsertPos - 1).getNext());
                mergeInto.getChildren().get(childInsertPos).getNext()
                        .setPrev(mergeInto.getChildren().get(childInsertPos));
                mergeInto.getChildren().get(childInsertPos - 1).setNext(mergeInto.getChildren().get(childInsertPos));
                mergeInto.getChildren().get(childInsertPos).setPrev(mergeInto.getChildren().get(childInsertPos - 1));
            }
        }

    }

    public int binarySearchWithinInternalNode(double key, List<Key> keyList) {
        int st = 0;
        int end = keyList.size() - 1;
        int mid;
        int index = -1;
        if (key < keyList.get(st).getKey()) {
            return 0;
        }
        if (key >= keyList.get(end).getKey()) {
            return keyList.size();
        }
        while (st <= end) {
            mid = (st + end) / 2;
            if (key < keyList.get(mid).getKey() && key >= keyList.get(mid - 1).getKey()) {
                index = mid;
                break;
            }
            else if (key >= keyList.get(mid).getKey()) {
                st = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return index;
    }

    public List<String> search(double key) {
        List<String> searchValues = null;

        Node curr = this.root;
        while (curr.getChildren().size() != 0) {
            curr = curr.getChildren().get(binarySearchWithinInternalNode(key, curr.getKeys()));
        }
        List<Key> keyList = curr.getKeys();

        for (int i = 0; i < keyList.size(); i++) {
            if (key == keyList.get(i).getKey()) {
                searchValues = keyList.get(i).getValues();
            }
            if (key < keyList.get(i).getKey()) {
                break;
            }
        }

        return searchValues;
    }

}
