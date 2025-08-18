import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }
        int[] inDegree = new int[N + 1];
        int[] result = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a).add(b);
            inDegree[b]++;
        }

        Deque<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                result[i] = 1;
            }
        }

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : adj.get(current)) {
                inDegree[next]--;

                result[next] = result[current] + 1;

                if (inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
    }
}
