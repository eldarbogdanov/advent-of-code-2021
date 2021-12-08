import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class d8p2 {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int ans = 0;
        while(in.hasNextLine()) {
            String s = in.nextLine();
            System.out.println(Arrays.asList(s.split(" \\| ")[1]));
            String a = "", bd, cf = "", bcdf = "", acf = "";
            String[] combinations = s.split(" \\| ")[0].split(" ");
            String[] digits = s.split(" \\| ")[1].split(" ");
            for(String combination : combinations) {
//                System.out.println(combination);
                if (combination.length() == 2) {
                    cf = combination;
                }
                if (combination.length() == 4) {
                    bcdf = combination;
                }
                if (combination.length() == 3) {
                    acf = combination;
                }
            }
            for(int i = 0; i < acf.length(); i++) {
                if (!cf.contains("" + acf.charAt(i))) a = "" + acf.charAt(i);
            }
            bd = "";
            for(int i = 0; i < bcdf.length(); i++) {
                if (!cf.contains("" + bcdf.charAt(i))) bd += bcdf.charAt(i);
            }
            int cur = 0;
//            System.out.println(Arrays.asList(a, bd, cf, acf, bcdf));
            for(String digit : digits) {
                if (digit.length() == 2) cur += 1;
                if (digit.length() == 3) cur += 7;
                if (digit.length() == 4) cur += 4;
                if (digit.length() == 7) cur += 8;
                if (digit.length() == 6) {
                    if (!digit.contains("" + bd.charAt(0)) || !digit.contains("" + bd.charAt(1))) cur += 0; else
                    if (!digit.contains("" + cf.charAt(0)) || !digit.contains("" + cf.charAt(1))) cur += 6; else
                        cur += 9;
                }
                if (digit.length() == 5) {
//                    System.out.println(digit + " " + digit.contains("" + cf.charAt(0)) + " " + digit.contains("" + cf.charAt(1)));
                    if ((digit.contains("" + bd.charAt(0)) ^ digit.contains("" + bd.charAt(1)) &&
                        (digit.contains("" + cf.charAt(0)) ^ digit.contains("" + cf.charAt(1))))) cur += 2; else
                    if (!digit.contains("" + bd.charAt(0)) || !digit.contains("" + bd.charAt(1))) cur += 3; else cur += 5;
                }
                cur *= 10;
            }
            ans += cur / 10;
            System.out.println(cur + " " + ans);
        }
    }
}

/*
0: abc efg (6)
1:   c  f  (2)
2: a cde g (5)
3: a cd fg (5)
4:  bcd f  (4)
5: ab d fg (5)
6: ab defg (6)
7: a c  f  (3)
8: abcdefg (7)
9: abcd fg (6)

0 <-> 6 <-> 9
0: d missing - so it's the one missing bd
6: c missing - so it's the one missing cf
9: e missing - so it's the one not missing bd, cf
2 <-> 3 <-> 5
2: bf missing - so it's the one missing bd and cf
3: be missing - so it's the one missing bd
5: ce missing - so it's the one missing cf

a known
cf known
bd known


be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce
*/
