import java.io.FileNotFoundException;

public class d17p2 {
    public static void main(String args[]) throws FileNotFoundException {
//        Scanner in = new Scanner(new File("test.in"));
//        String s = in.next();
        int xmin = 211;
        int xmax = 232;
        int ymin = -124;
        int ymax = -69;

        int maxY = -100000;
        int ret = 0;
        for(int x = -500; x < 500; x++) {
//            System.out.println(x);
            for(int y = -200; y < 1500; y++) {
                int xx = 0;
                int yy = 0;
                int vx = x;
                int vy = y;
                int maxReached = -100000;
                for(int step = 0; step < 1000; step++) {
//                    System.out.println(xx + " " + yy + " " + vx + " " + vy);
                    xx += vx;
                    yy += vy;
                    maxReached = Math.max(yy, maxReached);
                    if (xmin <= xx && xx <= xmax && ymin <= yy && yy <= ymax) {
                        ret++;
                        break;
                    }
                    if (vx < 0) vx += 1;
                    if (vx > 0) vx -= 1;
                    vy -= 1;
                }
//                break;
            }
//            break;
        }
        System.out.println(ret);
    }
}
