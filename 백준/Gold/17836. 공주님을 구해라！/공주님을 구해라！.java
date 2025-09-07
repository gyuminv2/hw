import java.io.*;
import java.util.*;

public class Main {

    static int N, M, T;
    static int[][] grid;
    static int[][][] v;
    static int ans;
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};

    static boolean inRange(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < M;
    }

    static void dfs(int ci, int cj, int has, int cnt) {

        if (cnt > T) return;
        if (cnt >= ans) return;
        if (cnt >= v[ci][cj][has]) return;

        v[ci][cj][has] = cnt;

        if (ci == N-1 && cj == M-1) {
            ans = Math.min(ans, cnt);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int ni = ci + dis[d];
            int nj = cj + djs[d];

            if (inRange(ni, nj)) {
                if (has == 0) {
                    if (grid[ni][nj] == 0) {
                        dfs(ni, nj, 0, cnt+1);
                    }
                    else if (grid[ni][nj] == 2) {
                        dfs(ni, nj, 1, cnt+1);
                    }
                }
                else {
                    dfs(ni, nj, 1, cnt+1);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        v = new int[N][M][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(v[i][j], Integer.MAX_VALUE);
            }
        }
        ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0, 0);

        if (ans <= T) System.out.println(ans);
        else System.out.println("Fail");
    }
}
