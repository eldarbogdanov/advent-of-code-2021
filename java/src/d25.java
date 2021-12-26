import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner in = new Scanner(new File("test.in"));
//        Scanner in = new Scanner(System.in);
        List<String> input = new ArrayList<>();
        while(in.hasNextLine()) {
            input.add(in.nextLine());
        }
        char[][] board = new char[input.size()][input.get(0).length()];
        int n = board.length;
        int m = board[0].length;
        for(int i = 0; i < n; i++) {
            board[i] = input.get(i).toCharArray();
        }
        int step = 0;
        final int[] di = {0, 1};
        final int[] dj = {1, 0};
        final char[] movers = new char[]{'>', 'v'};
        while(true) {
            ++step;
            boolean moved = false;
            for(char mover : movers) {
                char[][] newBoard = new char[n][m];
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < m; j++) {
                        newBoard[i][j] = board[i][j] == '.' || board[i][j] != mover ? board[i][j] : '.';
                    }
                }
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < m; j++) {
                        if (board[i][j] != mover) continue;
                        int d = mover == '>' ? 0 : 1;
                        int ii = (i + di[d] + n) % n;
                        int jj = (j + dj[d] + m) % m;
                        if (board[ii][jj] == '.') {
                            moved = true;
                            newBoard[ii][jj] = mover;
                        } else {
                            newBoard[i][j] = mover;
                        }
                    }
                }
                board = newBoard;
            }
            if (!moved) {
                System.out.println(step);
                break;
            }
            System.out.println(step);
//            for(int i = 0; i < n; i++) {
//                for(int j = 0; j < m; j++) System.out.print(board[i][j]);
//                System.out.println();
//            }
        }
    }
}
