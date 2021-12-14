import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class d14p2 {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner in = new Scanner(new File("test.in"));
//        Scanner in = new Scanner(System.in);
        String pattern = in.nextLine();
        in.nextLine();
        // how many of each char will be produced after i steps from adjacent letters (j, k)
        long[][][][] dp = new long[50][26][26][26];
        boolean[][][] mem = new boolean[50][26][26];
        int[][] insert = new int[26][26];
        for(int i = 0; i < 26; i++) {
            for(int j = 0; j < 26; j++) {
                insert[i][j] = -1;
            }
        }
        while(in.hasNextLine()) {
            String s = in.nextLine();
            String pair = s.split("->")[0];
            insert[pair.charAt(0) - 'A'][pair.charAt(1) - 'A'] = s.split(" -> ")[1].charAt(0) - 'A';
//            dp[1][pair.charAt(0) - 'A'][pair.charAt(1) - 'A'][s.split(" -> ")[1].charAt(0) - 'A'] = 1;
//            System.out.println(s.split("->")[1].charAt(0));
        }
        int step = 40;
        long[] count = new long[26];
        for(int i = 0; i < pattern.length(); i++) {
            count[pattern.charAt(i) - 'A']++;
            if (i + 1 < pattern.length()) {
                long[] x = rec(step, pattern.charAt(i) - 'A', pattern.charAt(i + 1) - 'A', insert, dp, mem);
                System.out.println(pattern.charAt(i) + " " + pattern.charAt(i + 1) +  " " +  Arrays.toString(x));
                for(int j = 0; j < 26; j++) {
                    count[j] += x[j];
                }
            }
        }
        long max = 0;
        for(int i = 0; i < 26; i++) {
            max = Math.max(max, count[i]);
        }
        long min = 1L << 60;
        for(int i = 0; i < 26; i++) {
            if (count[i] == 0) continue;
            min = Math.min(min, count[i]);
        }
        System.out.println(max - min);
    }

    static long[] rec(int step, int left, int right, int[][] rules, long[][][][] dp, boolean[][][] mem) {
        if (mem[step][left][right]) return dp[step][left][right];
        if (step == 1) {
            mem[step][left][right] = true;
            // double-check
            if (rules[left][right] != -1) dp[step][left][right][rules[left][right]]++;
            return dp[step][left][right];
        }
        if (rules[left][right] != -1) {
            long[] a = rec(step - 1, left, rules[left][right], rules, dp, mem);
            long[] b = rec(step - 1, rules[left][right], right, rules, dp, mem);
            for(int i = 0; i < 26; i++) {
                dp[step][left][right][i] = a[i] + b[i];
            }
            dp[step][left][right][rules[left][right]]++;
        }
        mem[step][left][right] = true;
        return dp[step][left][right];
    }
}
