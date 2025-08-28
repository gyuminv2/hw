import java.io.*;
import java.util.*;

public class Solution {

    static int N, W, H;
    static int[][] orgGrid;
    static int min;
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};

    static void gravity(int[][] grid) {
        Deque<Integer> q = new ArrayDeque<>();

        for (int j = 0; j < W; j++) {
            for (int i = H-1; i >= 0; i--) {
                if (grid[i][j] > 0) {
                    q.offer(grid[i][j]);
                    grid[i][j] = 0;
                }
            }

            int i = H-1;
            while (!q.isEmpty()) {
                grid[i--][j] = q.poll();
            }
        }
    }

    static boolean inRange(int i, int j) {
        return 0 <= i && i < H && 0 <= j && j < W;
    }

    static void boom(int[][] grid, int si, int sj) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {si, sj});

        boolean[][] v = new boolean[H][W];
        v[si][sj] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            for (int i = 0; i < grid[ci][cj]; i++) {
                for (int d = 0; d < 4; d++) {
                    int ni = ci + i * dis[d];
                    int nj = cj + i * djs[d];

                    if (inRange(ni, nj) && grid[ni][nj] > 0 && !v[ni][nj]) {
                        v[ni][nj] = true;
                        q.offer(new int[] {ni, nj});
                    }
                }
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0 ; j < W; j++) {
                if (v[i][j]) grid[i][j] = 0;
            }
        }
    }

    static int findTop(int[][] grid, int j) {
        for (int i = 0; i < H; i++) {
            if (grid[i][j] > 0) return i;
        }
        return -1;
    }

    static void simulate(int[][] grid, int[] beads) {
        int si;
        for (int i = 0; i < N; i++) {
            si = findTop(grid, beads[i]);
            if (si == -1) break;

            boom(grid, si, beads[i]);

            gravity(grid);
        }

        int sum = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (grid[i][j] > 0) sum++;
            }
        }
        min = Math.min(min, sum);
    }

    static void perm(int cnt, int[] beads) {
        if (min == 0) return;

        if (cnt == N) {
            int[][] grid = new int[H][W];
            for (int i = 0; i < H; i++) grid[i] = orgGrid[i].clone();
            simulate(grid, beads);
            return;
        }
        for (int i = 0; i < W; i++) {
            beads[cnt] = i;
            perm(cnt+1, beads);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringTokenizer st;

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            orgGrid = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < W; j++) {
                    orgGrid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            min = Integer.MAX_VALUE;

            int[] beads = new int[N];
            perm(0, beads);

            System.out.println("#" + tc + " " + min);
        }
    }
}