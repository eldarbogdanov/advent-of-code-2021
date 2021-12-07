import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class d7p {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        List<Integer> nums = new ArrayList<>();
        for(String s : str.split(",")) {
            nums.add(Integer.parseInt(s));
        }
        int ans = 1000000000;
        for(int x = 0; x < 2000; x++) {
            int cur = 0;
            for(int i = 0; i < nums.size(); i++) {
                int y = Math.abs(nums.get(i) - x);
                cur += (1 + y) * y / 2;
            }
            ans = Math.min(ans, cur);
        }
        System.out.println(ans);
    }
}
