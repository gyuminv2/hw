import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static String[] input;
    static boolean[] v;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        v = new boolean[N+1];
        graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            graph[a].add(b);
            graph[b].add(a);
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Deque<int[]> q = new ArrayDeque<>();
        int cnt = 0;

        q.offer(new int[] {1, 0});
        v[1] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int person = cur[0];
            int depth = cur[1];

            if (depth > 1) continue;

            for (int f : graph[person]) {
                if (!v[f]) {
                    v[f] = true;
                    cnt++;
                    q.offer(new int[] {f, depth+1});
                }
            }
        }
        return cnt;
    }
}