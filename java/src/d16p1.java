import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class d16p1 {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner in = new Scanner(new File("test.in"));
        String s = in.next();
        int[] bits = new int[s.length() * 4];
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int digit = c >= '0' && c <= '9' ? c - '0' : c - 'A' + 10;
            for(int j = 0; j < 4; j++) {
                bits[i * 4 + 3 - j] = (digit & (1 << j)) > 0 ? 1 : 0;
            }
        }
        System.out.println(Arrays.toString(bits));
        int[] res = new int[1];
        traverse(bits, 0, bits.length, res);
        System.out.println(res[0]);
    }

    static int parseInt(int[] bits, int start, int end) {
        int ret = 0;
        for(int i = start; i < end; i++) {
            ret = ret * 2 + bits[i];
        }
        return ret;
    }

    static int traverse(int[] bits, int start, int end, int[] res) {
        int version = parseInt(bits, start, start + 3);
        res[0] += version;
        int typeId = parseInt(bits, start + 3, start + 6);
        if (typeId == 4) {
            // literal packet
            int literalEnd = -1;
            for(int i = start + 6; ; i += 5) {
                if (bits[i] == 0) {
                    literalEnd = i + 5;
                    break;
                }
            }
            return literalEnd;
        } else {
            int lengthTypeId = bits[start + 6];
            if (lengthTypeId == 0) {
                int subpacketLength = parseInt(bits, start + 7, start + 22);
                int nextPacketStart = start + 22;
                while(true) {
                    int nextPacketEnd = traverse(bits, nextPacketStart, end, res);
                    if (nextPacketEnd - start - 22 == subpacketLength) return nextPacketEnd;
                    nextPacketStart = nextPacketEnd;
                }
            } else {
                int subpacketNumber = parseInt(bits, start + 7, start + 18);
                int nextPacketStart = start + 18;
                for(int i = 0; i < subpacketNumber; i++) {
                    nextPacketStart = traverse(bits, nextPacketStart, end, res);
                }
                return nextPacketStart;
            }
        }
    }
}
