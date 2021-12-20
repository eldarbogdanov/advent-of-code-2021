import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner in = new Scanner(new File("test.in"));
        String s = in.nextLine();

        in.nextLine();

        List<String> board = new ArrayList<>();
        while(in.hasNextLine()) {
            board.add(in.nextLine());
        }

        board = enhance(board, s, 0);
        int outer = 0;
        for(int step = 1; step < 50; step++) {
            outer = s.charAt(outer * 511) == '.' ? 0 : 1;
            board = enhance(board, s, outer);
        }
        int ans = 0;
        for(int i = 0; i < board.size(); i++) {
            for(int j = 0; j < board.get(i).length(); j++) {
                ans += board.get(i).charAt(j) == '#'? 1 : 0;
            }
        }
        System.out.println(ans);
    }

    static List<String> enhance(List<String> board, String map, int outer) {
        int n = board.size();
        int m = board.get(0).length();
        int frame = 9;
        String[] newBoard = new String[n + frame * 2];
        for(int i = 0; i < n + frame * 2; i++) {
            newBoard[i] = "";
            for(int j = 0; j < m + frame * 2; j++) {
                int bitmask = 0;
                for(int di = -1; di < 2; di++) {
                    for(int dj = -1; dj < 2; dj++) {
                        int ii = i + di - frame;
                        int jj = j + dj - frame;
                        int value = outer;
                        if (ii >= 0 && ii < n && jj >= 0 && jj < m) {
                            value = board.get(ii).charAt(jj) == '#' ? 1 : 0;
                        }
                        bitmask = bitmask * 2 + value;
                    }
                }
                newBoard[i] += map.charAt(bitmask);
            }
        }
        return Arrays.asList(newBoard);
    }
}
