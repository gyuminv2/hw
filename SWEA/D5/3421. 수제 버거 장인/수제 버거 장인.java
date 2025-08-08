import java.io.*;
import java.util.*;

public class Solution {

    static HashMap<Integer, Set<Integer>> map;
    static List<Integer> arr;
    static int N, M, count;

    static void subs(int cnt, List<Integer> picked) {
        if (cnt == N + 1) {
            count++;
            return;
        }

        subs(cnt + 1, picked);

        boolean canPick = true;
        for (int p : picked) {
            if (map.containsKey(cnt) && map.get(cnt).contains(p)) {
                canPick = false;
                break;
            }
        }

        if (canPick) {
            picked.add(cnt);
            subs(cnt + 1, picked);
            picked.remove(picked.size() - 1);
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            String[] input = br.readLine().split(" ");
            map = new HashMap<>();
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            arr = new ArrayList<>();
            count = 0;

            for (int i = 0; i < M; i++) {
                input = br.readLine().split(" ");
                int a, b;
                a = Integer.parseInt(input[0]);
                b = Integer.parseInt(input[1]);
                map.computeIfAbsent(a, x -> new HashSet<>()).add(b);
                map.computeIfAbsent(b, x -> new HashSet<>()).add(a);
            }

            subs(1, new ArrayList<>());

            System.out.println("#" + tc + " " + count);
        }
    }
}
