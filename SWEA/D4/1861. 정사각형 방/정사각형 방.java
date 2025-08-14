import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[][] grid;
//    static boolean[][] v;
//    static int[][] save;
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};
    static int mi, mj;
    static int max;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC =  Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {

            N = Integer.parseInt(br.readLine());
            StringTokenizer st;
            grid = new int[N][N];
//            save = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            mi = 0; mj = 0;
            max = -1;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
//                    v = new boolean[N][N];
                    int rtn = bfs(i, j);
                    if (max < rtn) {
                        mi = i;
                        mj = j;
                        max = rtn;
                    }
                    else if (max == rtn) {
                        if (grid[mi][mj] > grid[i][j]) {
                            mi = i;
                            mj = j;
                        }
                    }
                }
            }
//
//            for (int[] row : save) {
//                System.out.println(Arrays.toString(row));
//            }

            System.out.println("#" + tc + " " + grid[mi][mj] + " " + max);
        }
    }

    static boolean inRange(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < N;
    }

    static int bfs(int si, int sj) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {si, sj});
//        v[si][sj] = true;

        int cnt = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0], cj = cur[1];

            for (int d = 0; d < 4; d++) {
                int ni = ci + dis[d];
                int nj = cj + djs[d];

//                if (inRange(ni, nj) && !v[ni][nj] && (grid[ci][cj] + 1 == grid[ni][nj])) {
                if (inRange(ni, nj) && (grid[ci][cj] + 1 == grid[ni][nj])) {
                    q.offer(new int[] {ni, nj});
//                    v[ni][nj] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}

