import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[][] grid;
    static int max;
    static int si, sj;

    // 1:우하, 2:좌하, 3:좌상, 4:우상
    static int[] dis = {1, 1, -1, -1};
    static int[] djs = {1, -1, -1, 1};

    static boolean inRange(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }

    static void dfs(int ci, int cj, int dr, int cnt, boolean[] visitiedDesert) {
        if (!inRange(ci, cj)) return ;
        if (visitiedDesert[grid[ci][cj]]) {
            if (si == ci && sj == cj) {
                max = Math.max(max, cnt);
            }
            return ;
        }
        visitiedDesert[grid[ci][cj]] = true;
        dfs(ci + dis[dr], cj + djs[dr], dr, cnt + 1, visitiedDesert);
        if (dr < 3)
            dfs(ci + dis[dr + 1], cj + djs[dr + 1], dr + 1, cnt + 1, visitiedDesert);
        visitiedDesert[grid[ci][cj]] = false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC =  Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            
            N = Integer.parseInt(st.nextToken());
            grid = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            max = -1;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    boolean[] visitiedDesert = new boolean[101];
                    si = i; sj = j;
                    visitiedDesert[grid[i][j]] = true;
                    dfs(i+1, j+1, 0,  1, visitiedDesert);
                }
            }

            System.out.println("#" + tc + " " + max);
        }
    }
}

