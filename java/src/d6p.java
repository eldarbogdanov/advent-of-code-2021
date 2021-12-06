import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class d6p {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        List<Integer> nums = new ArrayList<>();
        final int MAX = 10;
        long[] cnt7 = new long[MAX];
        long[] cnt9 = new long[MAX];
        for (String s : str.split(",")) {
            int x = Integer.parseInt(s);
            cnt7[x]++;
        }
        for (int day = 0; day < 256; day++) {
            long[] newCnt7 = new long[MAX];
            long[] newCnt9 = new long[MAX];
            if (cnt7[0] > 0) {
                newCnt9[8] += cnt7[0];
                newCnt7[6] = cnt7[0];
            }
            if (cnt9[0] > 0) {
                newCnt9[8] += cnt9[0];
                newCnt9[6] = cnt9[0];
            }
            for(int i = 1; i < MAX; i++) {
                newCnt7[i - 1] += cnt7[i];
                newCnt9[i - 1] += cnt9[i];
            }
            long ans = 0;
            for(int i = 0; i < MAX; i++) {
                ans += newCnt7[i];
                ans += newCnt9[i];
            }
            cnt7 = newCnt7;
            cnt9 = newCnt9;
            System.out.println(day + " " + ans);
        }
    }
}
