
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class BSTNode<T> {

    private T data;

    private BSTNode<T> left;
    private BSTNode<T> right;

    public BSTNode() {
        data = null;
        left = null;
        right = null;
    }

    public BSTNode(T data) {
        this.data = data;
        left = null;
        right = null;
    }

    public T getData() {
        return data;
    }

    public T setData(T data) {
        T old = data;
        this.data = data;
        return old;
    }

    public BSTNode<T> getLeft() {
        return left;
    }

    public BSTNode<T> setLeft(BSTNode<T> left) {
        BSTNode<T> old = this.left;
        this.left = left;
        return old;
    }

    public BSTNode<T> getRight() {
        return right;
    }

    public BSTNode<T> setRight(BSTNode<T> right) {
        BSTNode<T> old = this.right;
        this.right = right;
        return old;
    }

    public List<SparsePaddedString> toStringList() {

        if(left == null && right == null) {
            SparsePaddedString resPdStr = new SparsePaddedString();
            resPdStr.add(0, " ");
            resPdStr.add(0, data.toString());
            resPdStr.add(0, " ");

            LinkedList<SparsePaddedString> res = new LinkedList<>();
            res.add(resPdStr);

            return res;
        }

        List<SparsePaddedString> leftStrLst = (left != null) ? left.toStringList() : new LinkedList<>();
        List<SparsePaddedString> rightStrLst = (right != null) ? right.toStringList() : new LinkedList<>();

        //

        int leftTgtLen = 0;
        int rightTgtLen = 0;

        for(SparsePaddedString s : leftStrLst) {
            leftTgtLen = s.length() > leftTgtLen ? s.length() : leftTgtLen;
        }

        for(SparsePaddedString s : rightStrLst) {
            rightTgtLen = s.length() > rightTgtLen ? s.length() : rightTgtLen;
        }

        //

        int totalLines = leftStrLst.size() >= rightStrLst.size() ? leftStrLst.size() : rightStrLst.size();
        
        while(leftStrLst.size() < totalLines) {
            leftStrLst.add(new SparsePaddedString());
        }
        
        while(rightStrLst.size() < totalLines) {
            rightStrLst.add(new SparsePaddedString());
        }

        //

        for(int i = 0; i < leftStrLst.size(); i++) {
            SparsePaddedString sps = leftStrLst.get(i);

            if(sps.length() < leftTgtLen) {
                sps.add(leftTgtLen - sps.length(), "");
            }
        }

        for(int i = 0; i < rightStrLst.size(); i++) {
            SparsePaddedString sps = rightStrLst.get(i);

            if(sps.length() < rightTgtLen) {
                sps.add(rightTgtLen - sps.length(), "");
            }
        }

        //

        for(int i = 0; i < leftStrLst.size(); i++) {
            leftStrLst.get(i).add(rightStrLst.get(i)); 
        }

        int padLen = (3 * leftTgtLen + rightTgtLen - 2 * data.toString().length())/4;
        //int padLen = leftTgtLen - data.toString().length()/2;

        SparsePaddedString top = new SparsePaddedString();
        top.add(padLen, data.toString());
        top.add(rightTgtLen + leftTgtLen - top.length(), "");

        leftStrLst.add(0, top);
        return leftStrLst;
    }
}