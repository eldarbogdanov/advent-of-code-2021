import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class d12p {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner in = new Scanner(new File("test.in"));
//        Scanner in = new Scanner(System.in);
        Map<String, List<String>> edges = new HashMap<>();
        while(in.hasNext()) {
            String s = in.next();
            String a = s.split("-")[0];
            String b = s.split("-")[1];
            if (!edges.containsKey(a)) {
                edges.put(a, new ArrayList<>());
            }
            edges.get(a).add(b);
            if (!edges.containsKey(b)) {
                edges.put(b, new ArrayList<>());
            }
            edges.get(b).add(a);
        }
        Map<String, Integer> visited =  new HashMap<>();
        visited.put("start", 2);
        System.out.println(rec("start", visited, edges, 0));
    }

    static int rec(String current, Map<String, Integer> visited, Map<String, List<String>> edges, int second) {
        if (current.equals("end")) {
            return 1;
        }
        System.out.println(current + " " + second);
        int ans = 0;
        for(String next : edges.get(current)) {
            if (visited.getOrDefault(next, 0) + second < 2 || next.charAt(0) >= 'A' && next.charAt(0) <= 'Z') {
                visited.put(next, visited.getOrDefault(next, 0) + 1);
                ans += rec(next, visited, edges, visited.get(next) == 1 || next.charAt(0) <= 'Z' ? second : second + 1);
                visited.put(next, visited.get(next) - 1);
            }
        }
        return ans;
    }
}
