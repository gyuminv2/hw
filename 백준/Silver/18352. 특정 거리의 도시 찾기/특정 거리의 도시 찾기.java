import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K, X;
    static List<Integer>[] graph;
    static int[] v;
    static List<Integer> rtn;

    static void bfs(int start) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        v[start] = 0;

        while (!q.isEmpty()) {
            int cur =  q.poll();

            for (int i : graph[cur]) {
                if (v[i] == -1) {
                    v[i] = v[cur] + 1;
                    q.offer(i);
                }
            }

        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        v = new int[N+1];
        Arrays.fill(v, -1);
        rtn = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
        }

        bfs(X);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= N; i++) {
            if (v[i] == K) {
                sb.append(i).append("\n");
            }
        }

        if (sb.length() == 0) {
            sb.append(-1).append("\n");
            System.out.println(sb);
        }
        else System.out.println(sb);
    }
}
