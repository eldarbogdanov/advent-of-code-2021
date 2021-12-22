import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class d22p1 {


    public static void main(String args[]) throws FileNotFoundException {
        Scanner in = new Scanner(new File("test.in"));
//        Scanner in = new Scanner(System.in);
        int max = 101;
        boolean[][][] on = new boolean[max][max][max];
        List<int[]> commands = new ArrayList<>();
        while(in.hasNextLine()) {
            String s = in.nextLine();
            int[] x = new int[7];
            x[6] = s.startsWith("on") ? 1 : 0;
            String[] parts = s.replace("x", " ").
                    replace("y", " ").
                    replace("z", " ").
                    replace("=", " ").
                    replace(".", " ").
                    replace(",", " ").
                    split(" ");
            System.out.println(Arrays.asList(parts));
            int index = 0;
            for(String part : parts) {
                if (!part.equals("") && !part.startsWith("o")) x[index++] = Integer.parseInt(part);
            }
            commands.add(x);
        }
        System.out.println(commands);
        for(int[] command : commands) {
            if (command[0] < -50) command[0] = -50;
            if (command[1] > 50) command[1] = 50;
            if (command[2] < -50) command[2] = -50;
            if (command[3] > 50) command[3] = 50;
            if (command[4] < -50) command[4] = -50;
            if (command[5] > 50) command[5] = 50;
            for(int x = command[0]; x <= command[1]; x++) {
                for(int y = command[2]; y <= command[3]; y++) {
                    for(int z = command[4]; z <= command[5]; z++) {
                        on[x + 50][y + 50][z + 50] = command[6] == 1;
                    }
                }
            }
        }
        int ans = 0;
        for(int x = -50; x <= 50; x++)
            for(int y = -50; y <= 50; y++)
                for(int z = -50; z <= 50; z++) ans += on[x + 50][y + 50][z + 50] ? 1 : 0;
        System.out.println(ans);
    }

}
