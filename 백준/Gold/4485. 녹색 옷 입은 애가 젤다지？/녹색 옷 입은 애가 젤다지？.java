import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] grid;
    static int[][] dist;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    static class Node implements Comparable<Node>{
        int ci, cj, cost;

        public Node(int ci, int cj, int cost) {
            this.ci = ci;
            this.cj = cj;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static boolean inRange(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < N;
    }

    static void dijktra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[0][0] = grid[0][0];
        pq.offer(new Node(0, 0, dist[0][0]));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.cost > dist[cur.ci][cur.cj]) continue;

            for (int d = 0; d < 4; d++) {
                int ni = cur.ci + di[d];
                int nj = cur.cj + dj[d];

                if (inRange(ni, nj)) {
                    int nc = cur.cost + grid[ni][nj];
                    if (nc < dist[ni][nj]) {
                        dist[ni][nj] = nc;
                        pq.offer(new Node(ni, nj, nc));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int tc = 0;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) break;
            grid = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) grid[i][j] = Integer.parseInt(st.nextToken());
            }
            dist = new int[N][N];
            for (int i = 0; i < N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

            dijktra();

            System.out.println("Problem " + ++tc + ": " + dist[N-1][N-1]);
        }
    }
}
