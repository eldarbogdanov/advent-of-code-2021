import java.io.FileNotFoundException;
import java.util.*;

public class d9p2 {
    static int n = 100;
    static int m = 100;
    public static void main(String args[]) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        List<String> board = new ArrayList<>();
        // god damn while(in.hasNextLine()) on System.in!
        for(int i = 0; i < n; i++) {
            String s = in.nextLine();
            board.add(s);
        }
        int[][] mem = new int[n][m];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if (board.get(i).charAt(j) == '9') continue;
                int sink = basinSink(i, j, board, mem);
                map.put(sink, map.getOrDefault(sink, 0) + 1);
            }
        }
        System.out.println(map);
        int[] values = new int[map.size()];
        int sz = 0;
        for(int x : map.values()) values[sz++] = x;
        Arrays.sort(values);
        System.out.println(values[values.length - 1] * values[values.length - 2] * values[values.length - 3]);
    }

    static final int[] di = {-1, 0, 1, 0};
    static final int[] dj = {0, 1, 0, -1};

    static int basinSink(int i, int j, List<String> board, int[][] mem) {
        if (mem[i][j] != 0) return mem[i][j];
        for(int d = 0; d < 4; d++) {
            int newi = i + di[d];
            int newj = j + dj[d];
            if (newi < 0 || newi >= n || newj < 0 || newj >= m) continue;
            if (board.get(newi).charAt(newj) < board.get(i).charAt(j)) return mem[i][j] = basinSink(newi, newj, board, mem);
        }
        return mem[i][j] = i * 100 + j;
    }
}
