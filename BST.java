import java.util.Collection;
import java.util.List;
import java.util.Random;
public class BST<T extends Comparable<T>> {
    
    private BSTNode<T> root;

    private int size;

    public BST() {
        root = new BSTNode<>();
        size = 0;
    }

    public BST(Collection<T> initialVals) {
        this();

        for(T val : initialVals) {
            add(val);
        }
    }

    public T add(T val) {
        if(root.getData() == null) {
            root.setData(val);
            size++;
            return val;
        }

        BSTNode<T> last = null;
        BSTNode<T> curr = root;
        BSTNode<T> next = new BSTNode<>(val);

        int compare;

        do {
            last = curr;
            compare = val.compareTo(curr.getData());
            if(compare == 0) {
                return val;
            }
            curr = compare < 0 ? curr.getLeft() : curr.getRight();
        }
        while(curr != null);

        if(compare < 0) {
            last.setLeft(next);
        }
        else {
            last.setRight(next);
        }
        size++;
        return val;
    }

    public boolean contains(T val) {
        BSTNode<T> curr = root;

        while(curr != null) {
            int compare = curr.getData().compareTo(val);
            if(compare == 0) {
                return true;
            }
            else if(compare > 0) {
                curr = curr.getRight();
            }
            else {
                curr = curr.getLeft();
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        List<SparsePaddedString> lines = root.toStringList();

        for(int i = 0; i < lines.size(); i++) {
            sb.append(lines.get(i).toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    public int getSize() {
        return size;
    }

    //

    public static void randInsert(int n, Random rand) {
        BST<Integer> bst = new BST<>();

        for(int i = 0; i < n; i++) {
            bst.add(rand.nextInt(2*n));
        }
        System.out.println(bst.toString());
        System.out.println("Total Elements: " + bst.getSize());
    }

    public static void seqInsert(int n) {
        BST<Integer> bst = new BST<>();

        for(int i = 0; i < n; i++) {
            bst.add(i);
        }
        System.out.println(bst.toString());
        System.out.println("Total Elements: " + bst.getSize());
    }

    public static void myArrInsert() {
        int[] arr = {5, 3, 2, 9, 6, 3, 8, 1};

        BST<Integer> bst = new BST<>();

        for(int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }
        System.out.println(bst.toString());
        System.out.println("Total Elements: " + bst.getSize());
    }

    public static void main(String[] args) {
        Random rand = new Random();

        // randomly inserts elements into a binary tree and prints the resulting tree
        // n is the number of elements we attempt to insert
        randInsert(20, rand);
        randInsert(40, rand);
        randInsert(60, rand);
        randInsert(80, rand);
        randInsert(100, rand);

        // inserts elements sequencially up to n and prints the resulting tree
        //seqInsert(5);
        //seqInsert(10);
        //seqInsert(15);
        //seqInsert(20);

        //myArrInsert();
    }
}
