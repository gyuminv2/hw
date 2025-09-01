import java.io.*;
import java.util.*;

public class Solution {

    static int[][] grid;
    static HashSet<String> save;
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};

    static boolean inRange(int i, int j) {
        return 0 <= i && i < 4 && 0 <= j && j < 4;
    }

    static void dfs(int ci, int cj, int depth, String str) {
        if (depth == 7) {
            save.add(str);
            return ;
        }
        for (int d = 0; d < 4; d++) {
            int ni = ci + dis[d];
            int nj = cj + djs[d];
            if (inRange(ni, nj)) {
                dfs(ni, nj, depth+1, str+grid[ni][nj]);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            grid = new int[4][4];
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            save = new HashSet<>();

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    dfs(i, j, 0, "");
                }
            }
//
//            for (String l : save) {
//                System.out.println(l);
//            }

            System.out.println("#" + tc + " " + save.size());
        }
    }
}
