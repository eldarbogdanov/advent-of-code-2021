import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class d19p12 {
    static class Beacon {
        int[] coord;

        Beacon(int x, int y, int z) {
            this.coord = new int[]{x, y, z};
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Beacon beacon = (Beacon) o;
            return this.coord[0] == beacon.coord[0] && this.coord[1] == beacon.coord[1] && this.coord[2] == beacon.coord[2];
        }

        @Override
        public int hashCode() {
            return Objects.hash(coord[0], coord[1], coord[2]);
        }

        @Override
        public String toString() {
            return "Beacon{" +
                    "coord=" + Arrays.toString(coord) +
                    '}';
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        Scanner in = new Scanner(new File("test.in"));
        List<List<Beacon>> scanners = new ArrayList<>();
        List<Beacon> currentScanner = new ArrayList<>();
        while(in.hasNextLine()) {
            String s = in.nextLine();
            if (s.equals("")) continue;
            if (s.startsWith("---")) {
                currentScanner = new ArrayList<>();
                scanners.add(currentScanner);
            } else {
                String[] nums = s.split(",");
                currentScanner.add(new Beacon(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]), Integer.parseInt(nums[2])));
            }
        }
        int n = scanners.size();
        Set<Beacon> beacons = new HashSet<>();
        List<Beacon> scannerPos = new ArrayList<>();
        scannerPos.add(new Beacon(0, 0, 0));
        for(Beacon beacon : scanners.get(0)) beacons.add(beacon);
        boolean[] finished = new boolean[n];
        boolean[][] tried = new boolean[n][n];
        finished[0] = true;
        int totalFinished = 1;
        while(totalFinished < n) {
            for(int i = 0; i < scanners.size(); i++) {
                if (finished[i]) continue;
                for(int j = 0; j < scanners.size(); j++) {
                    if (!finished[j] || tried[i][j]) continue;
                    System.out.println("Trying (" + i + "," + j + ")");
                    List<Beacon> processed = match(scanners.get(j), scanners.get(i), scannerPos);
                    System.out.println(processed);
                    tried[i][j] = true;
                    if (processed != null) {
                        finished[i] = true;
                        scanners.set(i, processed);
                        beacons.addAll(processed);
                        totalFinished++;
                        break;
                    }
                }
            }
//            break;
        }
        System.out.println(beacons.size());
        System.out.println(scannerPos);
        int maxDistance = 0;
        for(Beacon a : scannerPos) {
            for(Beacon b : scannerPos) {
                maxDistance = Math.max(maxDistance, Math.abs(a.coord[0] - b.coord[0]) + Math.abs(a.coord[1] - b.coord[1]) + Math.abs(a.coord[2] - b.coord[2]));
            }
        }
        System.out.println(maxDistance);
    }

    static List<Beacon> match(List<Beacon> A, List<Beacon> B, List<Beacon> scanners) {
        for(int x = -1; x <= 1; x += 2) {
            for(int y = -1; y <= 1; y += 2) {
                for(int z = -1; z <= 1; z += 2) {
                    for(int first = 0; first < 3; first++) {
                        for(int second = 0; second < 3; second++) {
                            for(int third = 0; third < 3; third++) {
                                if (first == second || first == third || second == third) continue;
                                List<Beacon> processed = new ArrayList<>();
                                for(Beacon beacon : B) {
                                    Beacon newBeacon = new Beacon(
                                            beacon.coord[first] * x,
                                            beacon.coord[second] * y,
                                            beacon.coord[third] * z
                                    );
                                    processed.add(newBeacon);
                                }
                                for(Beacon a : A) {
                                    for(Beacon b : processed) {
                                        int dx = a.coord[0] - b.coord[0];
                                        int dy = a.coord[1] - b.coord[1];
                                        int dz = a.coord[2] - b.coord[2];
                                        List<Beacon> processedB = new ArrayList<>();
                                        for(Beacon c : processed) {
                                            processedB.add(new Beacon(
                                                    c.coord[0] + dx,
                                                    c.coord[1] + dy,
                                                    c.coord[2] + dz
                                            ));
                                        }
                                        Set<Beacon> firstSet = new HashSet<>(A);
                                        Set<Beacon> secondSet = new HashSet<>(processedB);
                                        firstSet.retainAll(secondSet);
                                        if (firstSet.size() >= 12) {
                                            scanners.add(new Beacon(
                                                    dx, dy, dz
                                            ));
                                            return processedB;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
