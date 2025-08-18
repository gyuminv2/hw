import java.io.*;
import java.util.*;

public class Solution {
    static int D, W, K;
    static int[][] film;
    static int minCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            film = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            minCnt = D;

            dfs(0, 0);

            System.out.println("#" + tc + " " + minCnt);
        }
    }

    static void dfs(int cnt, int row) {
        if (cnt >= minCnt) {
            return;
        }
        if (row == D) {
            if (seqCheck()) {
                minCnt = cnt;
            }
            return;
        }
        dfs(cnt, row + 1);
        int[] orgRow = new int[W];
        for(int i=0; i<W; i++){
            orgRow[i] = film[row][i];
        }
        for (int j = 0; j < W; j++) {
            film[row][j] = 0;
        }
        dfs(cnt + 1, row + 1);
        for (int j = 0; j < W; j++) {
            film[row][j] = 1;
        }
        dfs(cnt + 1, row + 1);
        for(int i=0; i<W; i++){
            film[row][i] = orgRow[i];
        }
    }

    static boolean seqCheck() {
        for (int j = 0; j < W; j++) {
            boolean ok = false;
            int seq = 1;

            if (K == 1) {
                ok = true;
                continue;
            }
            for (int i = 1; i < D; i++) {
                if (film[i][j] == film[i - 1][j]) {
                    seq++;
                } else {
                    seq = 1;
                }
                if (seq >= K) {
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                return false;
            }
        }
        return true;
    }
}