import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class d15p2 {
    // I got first half accepted with an incorrect DP moving only down and right, lol
    public static void main(String args[]) throws FileNotFoundException {
        Scanner in = new Scanner(new File("test.in"));
//        Scanner in = new Scanner(System.in);
        List<String> tboard = new ArrayList<>();
        while(in.hasNext()) {
            tboard.add(in.next());
        }
        List<String> board = new ArrayList<>();

        int n = tboard.size();
        for(int i = 0; i < n * 5; i++) board.add("");
        for(int k = 0; k < 5; k++) {
            for(int l = 0; l < 5; l++) {
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < n; j++) {
                        int value = tboard.get(i).charAt(j) - '0' + k + l;
                        if (value > 9) value = value % 10 + 1;
                        board.set(i + k * n, board.get(i + k * n) + (char)('0' + value));
                    }
                }
            }
        }
//        System.out.println(board);
        for(int i = 0; i < 5 * n; i++) System.out.println(board.get(i));
        System.out.println(board.size() + " " + board.get(0).length());

        n *= 5;
        List<Integer> next = new ArrayList<>();
        Map<Integer, Integer> best = new HashMap<>();
        best.put(0, 0);
        next.add(0);
        int[] di = new int[]{-1, 0, 1, 0};
        int[] dj = new int[]{0, 1, 0, -1};
        for(int q = 0; q < next.size(); q++) {
            int cur = next.get(q);
            int i = cur / n;
            int j = cur % n;
            for(int d = 0; d < 4; d++) {
                int ii = i + di[d];
                int jj = j + dj[d];
                if (ii < 0 || ii >= n || jj < 0 || jj >= n) continue;
                int pos = ii * n + jj;
                if (!best.containsKey(pos) || best.get(pos) > best.get(cur) + board.get(ii).charAt(jj) - '0') {
                    best.put(pos, best.get(cur) + board.get(ii).charAt(jj) - '0');
                    next.add(pos);
//                    System.out.println(cur + " " + ii + " "  + jj + " " + pos + " " + d + " " + best);
                }
            }
        }
//        System.out.println(best);
        int finalPos = n * (n - 1) + (n - 1);
        System.out.println(best.get(finalPos));
    }
}
