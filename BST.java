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

    public static void main(String[] args) {
        /**/
        Random rand = new Random();

        BST<Integer> bst = new BST<>();

        for(int i = 0; i < 1000; i++) {
            bst.add(rand.nextInt(100));
        }
        System.out.println(bst.toString());
        System.out.println("Size: " + bst.getSize());
        /**/

        /*Random rand = new Random();

        BST<String> bst = new BST<>();

        for(int i = 0; i < 50; i++) {
            String s = "";
            for(int j = 0; j < 3; j++) {
                s += (char)(rand.nextInt(26) + 97);
            }
            bst.add(s);
        }
        System.out.println(bst.toString());
        System.out.println("Size: " + bst.getSize());
        */
    }
}
