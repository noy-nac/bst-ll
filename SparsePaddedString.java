import java.util.List;
import java.util.ArrayList;

public class SparsePaddedString {
    private List<Integer> paddings;
    private List<String> strings;

    private int length;

    public SparsePaddedString() {
        paddings = new ArrayList<>();
        strings = new ArrayList<>();
    }

    public void add(SparsePaddedString sps) {
        for(int i = 0; i < sps.paddings.size(); i++) {
            add(sps.paddings.get(i), sps.strings.get(i));
        }
    }

    public void add(Integer pad, String str) {
        insert(pad, str, paddings.size());
    }

    public void insert(Integer pad, String str, int index) {
        paddings.add(index, pad);
        strings.add(index, str);

        length += pad;
        length += str.length();
    }

    public int length() {
        return length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < paddings.size(); i++) {
            /*int s = paddings.get(i)/3;
            int d = paddings.get(i) - 3 * s;

            for(int j = 0; j < s; j++) {
                sb.append(" . ");
            }
            for(int j = 0; j < d; j++) {
                sb.append(' ');
            }*/
            for(int j = 0; j < paddings.get(i); j++) {
                sb.append(' ');
            }
            sb.append(strings.get(i));
        }
        return sb.toString();
    }
}
