import java.io.*;
import java.util.*;

// 1. 부분집합 K개만큼
// 2. 브루트포스
public class Solution {

    static int D, W, K;
    static int[][] grid;
    static boolean[] isSelected;
    static int ans;

    static int[][] copy() {
        int[][] rtn = new int[D][W];
        for (int i = 0; i < D; i++) {
            for (int j = 0; j < W; j++) {
                rtn[i][j] = grid[i][j];
            }
        }
        return rtn;
    }

    static void dfs(int[][] ngrid, boolean[] mark, int cnt, int idx) {
        if (cnt >= ans) return ;

        if (idx == D) {
            if (check(ngrid)) ans = Math.min(ans, cnt);
            return ;
        }

        if (mark[idx]) {
            // A
            Arrays.fill(ngrid[idx], 0);
            dfs(ngrid, mark, cnt+1, idx+1);

            // B
            Arrays.fill(ngrid[idx], 1);
            dfs(ngrid, mark, cnt+1, idx+1);
        }
        else dfs(ngrid, mark, cnt, idx+1);
    }

    static void subs(int cnt) {
        if (cnt == D) {
            int[][] ngrid = copy();
            dfs(ngrid, isSelected, 0, 0);
            return ;
        }
        isSelected[cnt] = true;
        subs(cnt+1);
        isSelected[cnt] = false;
        subs(cnt+1);
    }

    static boolean check(int[][] g) {
        for (int j = 0; j < W; j++) {
            boolean pass = false;
            int cnt = 1;

            for (int i = 1; i < D; i++) {
                if (g[i][j] == g[i-1][j]) cnt++;
                else cnt = 1;
                if (cnt >= K) {
                    pass = true;
                    break;
                }
            }
            if (!pass) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            grid = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            isSelected = new boolean[D];
            ans = Integer.MAX_VALUE;

            if (check(grid)) {
                System.out.println("#"+tc+" "+0);
            }
            else {
                subs(0);
                System.out.println("#"+tc+" "+ans);
            }
        }
    }
}