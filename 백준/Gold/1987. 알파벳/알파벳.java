import java.io.*;
import java.util.*;

public class Main {

    static int R;
    static int C;
    static char[][] grid;
    static boolean[] alpha;
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};
    static int max;

    static boolean inRange(int i, int j) {
        return i >= 0 && i < R && j >= 0 && j < C;
    }

    static void dfs(int[] start, int cnt) {
        int ci = start[0];
        int cj = start[1];
        alpha[grid[ci][cj]-'A'] = true;
        max = Math.max(max, cnt);
        for (int d = 0; d < 4; d++) {
            int ni = ci + dis[d];
            int nj = cj + djs[d];
            if (inRange(ni, nj) && !alpha[grid[ni][nj]-'A']) {
                dfs(new int[] {ni, nj}, cnt + 1);
            }
        }
        alpha[grid[ci][cj]-'A'] = false;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        grid = new char[R][C];
        for (int i = 0; i < R; i++) {
            grid[i] = br.readLine().toCharArray();
        }
        alpha = new boolean[26];
        max = 0;

        dfs(new int[] {0, 0}, 1);

        System.out.println(max);
    }
}