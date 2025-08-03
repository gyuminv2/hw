import java.io.*;
import java.util.*;

public class Main {
    static int[] drs = {2, -1, 1};

    static boolean in_range(int next) {
        return 0 <= next && next < 100001;
    }

    static void bfs(int start, int[] v) {
        Deque<Integer> q = new ArrayDeque<>();
        q.add(start);
        v[start] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int dr : drs) {
                int next;
                if (dr == 2) next = cur * 2;
                else next = cur + dr;

                if (in_range(next) && v[next] == 0) {
                    v[next] = v[cur] + 1;
                    q.add(next);
                }

            }

        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] v = new int[100001];
        Arrays.fill(v, 0);

        bfs(N, v);
        System.out.println(v[K] - 1);
    }
}
