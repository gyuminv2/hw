import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[][] grid;
    static int[][] minTime;
    static boolean[][] visited;
    static PriorityQueue<int[]> pq;
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};

    static boolean inRange(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }

    static int dijkstra(int si, int sj) {
        pq = new PriorityQueue<>((o1, o2)-> Integer.compare(o1[2], o2[2]));
        minTime=new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(minTime[i], Integer.MAX_VALUE);

        minTime[si][sj] = 0;
        pq.offer(new int[] {si, sj, minTime[si][sj]});
        visited[si][sj] = true;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int ci = cur[0];
            int cj = cur[1];
            int totalTime = cur[2];

            if (ci == N-1 && cj == N-1) return totalTime;

            for (int d = 0; d < 4; d++) {
                int ni = ci + dis[d];
                int nj = cj + djs[d];

                if (inRange(ni, nj) && !visited[ni][nj] && minTime[ni][nj] > totalTime + grid[ni][nj]) {
                    visited[ni][nj] = true;
                    minTime[ni][nj] = totalTime + grid[ni][nj];
                    pq.offer(new int[] {ni, nj, minTime[ni][nj]});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            grid = new int[N][N];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    grid[i][j] = line.charAt(j) - '0';
                }
            }
            visited = new boolean[N][N];
            System.out.println("#"+tc+" "+dijkstra(0, 0));
        }
    }
}