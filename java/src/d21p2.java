import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class d21p2 {
    public static void main(String args[]) throws FileNotFoundException {
//        Scanner in = new Scanner(new File("test.in"));
        long[][][][][] ways = new long[2][22][22][10][10];
        ways[0][0][0][1][0] = 1;
        for(int s1 = 0; s1 < 21; s1++) {
            for(int s2 = 0; s2 < 21; s2++) {
                for(int p1 = 0; p1 < 10; p1++) {
                    for(int p2 = 0; p2 < 10; p2++) {
                        for(int p = 0; p < 2; p++) {
                            for(int d1 = 0; d1 < 3; d1++) {
                                for(int d2 = 0; d2 < 3; d2++) {
                                    for(int d3 = 0; d3 < 3; d3++) {
                                        int total = d1 + d2 + d3 + 3;
                                        if (p == 0) {
                                            int newP1 = (p1 + total) % 10;
                                            int newS1 = Math.min(s1 + newP1 + 1, 21);
                                            ways[1][newS1][s2][newP1][p2] += ways[p][s1][s2][p1][p2];
                                        } else {
                                            int newP2 = (p2 + total) % 10;
                                            int newS2 = Math.min(s2 + newP2 + 1, 21);
                                            ways[0][s1][newS2][p1][newP2] += ways[p][s1][s2][p1][p2];
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        long[] ans = new long[2];
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                for(int p = 0; p < 2; p++) {
                    for(int s1 = 0; s1 < 21; s1++) ans[1] += ways[p][s1][21][i][j];
                    for(int s2 = 0; s2 < 21; s2++) ans[0] += ways[p][21][s2][i][j];
                }
            }
        }
        System.out.println(Arrays.asList(ans[0], ans[1] ));
        System.out.println(Math.max(ans[0], ans[1]));
    }
}
