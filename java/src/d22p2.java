import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class d22p2 {


    public static void main(String args[]) throws FileNotFoundException {
        Scanner in = new Scanner(new File("test.in"));
//        Scanner in = new Scanner(System.in);
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
//            System.out.println(Arrays.asList(parts));
            int index = 0;
            for(String part : parts) {
                if (!part.equals("") && !part.startsWith("o")) x[index++] = Integer.parseInt(part);
            }
            if (x[0] == x[1] || x[2] == x[3] || x[4] == x[5]) throw new RuntimeException("Too smart");
            commands.add(x);
        }

        Map<Integer, Integer> xindex = new HashMap<>();
        int[] xreverseIndex = new int[1000];
        int[] yreverseIndex = new int[1000];
        int[] zreverseIndex = new int[1000];
        Map<Integer, Integer> yindex = new HashMap<>();
        Map<Integer, Integer> zindex = new HashMap<>();
        for(int[] command : commands) {
            xindex.put(command[0], -1);
            xindex.put(command[1] + 1, -1);
            yindex.put(command[2], -1);
            yindex.put(command[3] + 1, -1);
            zindex.put(command[4], -1);
            zindex.put(command[5] + 1, -1);
        }
        int cntx = 0, cnty = 0, cntz = 0;
        for(int i = -1000000; i <= 1000000; i++) {
            if (xindex.containsKey(i)) {
                xindex.put(i, cntx);
                xreverseIndex[cntx] = i;
                cntx++;
            }
            if (yindex.containsKey(i)) {
                yindex.put(i, cnty);
                yreverseIndex[cnty] = i;
                cnty++;
            }
            if (zindex.containsKey(i)) {
                zindex.put(i, cntz);
                zreverseIndex[cntz] = i;
                cntz++;
            }
        }

        System.out.println(xindex.size());
        System.out.println(yindex.size());
        System.out.println(zindex.size());

        boolean[][][] on = new boolean[xindex.size()][yindex.size()][zindex.size()];

        int cnt = 0;
        for(int[] command : commands) {
            System.out.println(cnt++);
            int xmin = xindex.get(command[0]);
            int xmax = xindex.get(command[1] + 1) - 1;
            int ymin = yindex.get(command[2]);
            int ymax = yindex.get(command[3] + 1) - 1;
            int zmin = zindex.get(command[4]);
            int zmax = zindex.get(command[5] + 1) - 1;
            for(int x = xmin; x <= xmax; x++) {
                for(int y = ymin; y <= ymax; y++) {
                    for(int z = zmin; z <= zmax; z++) {
                        on[x][y][z] = command[6] == 1;
                    }
                }
            }
        }
        long ans = 0;
        for(int x = 0; x < xindex.size(); x++) {
            for (int y = 0; y < yindex.size(); y++) {
                for (int z = 0; z < zindex.size(); z++) {
                    if (on[x][y][z]) {
                        long xx = xreverseIndex[x + 1] - xreverseIndex[x];
                        long yy = yreverseIndex[y + 1] - yreverseIndex[y];
                        long zz = zreverseIndex[z + 1] - zreverseIndex[z];
                        ans += xx * yy * zz;
                    }
                }
            }
        }
        System.out.println(ans);
    }

}
