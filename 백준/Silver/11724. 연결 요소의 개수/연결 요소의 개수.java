import java.io.*;
import java.util.*;

public class Main {

    public static void dfs(int idx, boolean[] v, List<List<Integer>> graph) {
        v[idx] = true;

        for (int neighbor : graph.get(idx)) {
            if (!v[neighbor]) dfs(neighbor, v, graph);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int answer = 0;
        boolean[] v = new boolean[N+1];
        for (int i = 0; i <= N; i++) {
            if (!v[i]) {
                dfs(i, v, graph);
                answer++;
            }
        }

        br.close();
        System.out.println(answer-1);
    }
}
