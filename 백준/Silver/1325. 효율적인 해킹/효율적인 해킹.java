
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static String[] input;
    static ArrayList<Integer>[] graph;
    static HashMap<Integer, Integer> info;
    static int max = Integer.MIN_VALUE;

    static int bfs(int s, boolean[] v) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(s);
        v[s] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            int c = q.poll();

            for (int n : graph[c]) {
                if (!v[n]) {
                    v[n] = true;
                    q.offer(n);
                    cnt ++;
                }
            }
        }
        max = Math.max(max, cnt);
        return cnt;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        info = new HashMap<>();
        graph = new ArrayList[N+1]; for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            graph[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            boolean[] v = new boolean[N+1];
            info.put(i, bfs(i, v));
        }

        StringBuilder sb = new StringBuilder();
        // https://pangtrue.tistory.com/232
        for (Integer key : info.keySet()) {
            if (max == info.get(key)) {
                sb.append(key).append(" ");
            }
        }
        System.out.println(sb);
    }
}