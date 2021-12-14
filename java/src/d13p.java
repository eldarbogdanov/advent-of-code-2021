import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class d13p {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner in = new Scanner(new File("test.in"));
//        Scanner in = new Scanner(System.in);
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        while(true) {
            String s = in.nextLine();
            System.out.println(s);
            if (s.equals("")) break;
            x.add(Integer.parseInt(s.split(",")[0]));
            y.add(Integer.parseInt(s.split(",")[1]));
        }

        while(in.hasNextLine()) {
            String fold = in.nextLine().split(" ")[2];
            int coord = Integer.parseInt(fold.split("=")[1]);
            if (fold.split("=")[0].equals("y")) {
                for(int i = 0; i < x.size(); i++) {
                    if (y.get(i) > coord) y.set(i, coord - (y.get(i) - coord));
                }
            }
            List<Integer> newX = new ArrayList<>();
            List<Integer> newY = new ArrayList<>();
            if (fold.split("=")[0].equals("x")) {
                for(int i = 0; i < x.size(); i++) {
                    if (x.get(i) > coord) x.set(i, coord - (x.get(i) - coord));
                }
            }
            for(int i = 0; i < x.size(); i++) {
                boolean good = true;
                for(int j = 0; j < i; j++) {
                    if (x.get(i).equals(x.get(j)) && y.get(i).equals(y.get(j))) good = false;
                }
                if (good) {
                    newX.add(x.get(i));
                    newY.add(y.get(i));
                }
            }
        }

        boolean[][] used = new boolean[2000][2000];
        for(int i = 0; i < x.size(); i++) {
            used[x.get(i)][y.get(i)] = true;
        }
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 100; j++) System.out.print(used[j][i] ? "#" : " ");
            System.out.println();
        }
    }
}
