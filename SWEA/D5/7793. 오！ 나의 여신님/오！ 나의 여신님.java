import java.io.*;
import java.util.*;

public class Solution {

    static int N, M;
    static char[][] grid;
    static int si, sj;
    static List<int[]> devil;
    static int[][] dv;
    static int[][] v;
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};

    static boolean inRange(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < M;
    }

    static void devilBfs() {
        Deque<int[]> q = new ArrayDeque<>();
        for (int[] dev : devil) {
            q.offer(new int[] {dev[0], dev[1]});
            dv[dev[0]][dev[1]] = 0;
        }
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cdvi = cur[0];
            int cdvj = cur[1];
            for (int d = 0; d < 4; d++) {
                int ndvi = cdvi + dis[d];
                int ndvj = cdvj + djs[d];
                if (inRange(ndvi, ndvj) && dv[ndvi][ndvj] == Integer.MAX_VALUE && grid[ndvi][ndvj] != 'X' && grid[ndvi][ndvj] != 'D') {
                    q.offer(new int[] {ndvi, ndvj});
                    dv[ndvi][ndvj] = dv[cdvi][cdvj] + 1;
                }
            }
        }
    }

    static int bfs() {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {si, sj});
        v[si][sj] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            if (grid[ci][cj] == 'D') {
                return v[ci][cj];
            }

            for (int d = 0; d < 4; d++) {
                int ni = ci + dis[d];
                int nj = cj + djs[d];
                if (inRange(ni, nj) && v[ni][nj] == -1 && v[ci][cj] + 1 < dv[ni][nj] && grid[ni][nj] != 'X') {
                    q.offer(new int[] {ni, nj});
                    v[ni][nj] = v[ci][cj] + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            String[] line = br.readLine().split(" ");
            N = Integer.parseInt(line[0]);
            M = Integer.parseInt(line[1]);
            grid = new char[N][M];
            devil = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < M; j++) {
                    grid[i][j] = input.charAt(j);
                    if (grid[i][j] == 'S') {
                        si = i; sj = j;
                    }
                    if (grid[i][j] == '*') {
                        devil.add(new int[] {i, j});
                    }
                }
            }
            dv = new int[N][M];
            for (int i = 0; i < N; i++) Arrays.fill(dv[i], Integer.MAX_VALUE);
            v = new int[N][M];
            for (int i = 0; i < N; i++) Arrays.fill(v[i], -1);

            devilBfs();
            int result = bfs();

            System.out.println("#" + tc + " " + (result == -1 ? "GAME OVER" : result));
        }
    }
}