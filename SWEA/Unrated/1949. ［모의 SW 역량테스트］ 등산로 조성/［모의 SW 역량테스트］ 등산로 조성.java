import java.io.*;
import java.util.*;

public class Solution {

    static int N, K;
    static int[][] grid;
    static boolean[][] v;
    static int bong;
    static int max;
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};

    static boolean inRange(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }

    public static void dfs(int ci, int cj, boolean flag, int cnt) {
        v[ci][cj] = true;
        max = Math.max(max, cnt);

        for (int d = 0; d < 4; d++) {
            int ni = ci + dis[d];
            int nj = cj + djs[d];
            if (!inRange(ni, nj)) continue;
            if (v[ni][nj]) continue;
            if (grid[ni][nj] < grid[ci][cj]) {
                dfs(ni, nj, flag, cnt+1);
            }
            else if (flag && (grid[ni][nj] - K < grid[ci][cj])) {
                int org = grid[ni][nj];
                grid[ni][nj] = grid[ci][cj] - 1;
                dfs(ni, nj, false, cnt+1);
                grid[ni][nj] = org;
            }
        }
        v[ci][cj] = false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC =  Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            grid = new int[N][N];
            v = new boolean[N][N];
            bong = -1;
            max = -1;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                    bong = Math.max(bong, grid[i][j]);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (grid[i][j] == bong) {
                        dfs(i, j, true, 1);
                    }
                }
            }

            System.out.println("#" + tc + " " + max);
        }
    }
}

