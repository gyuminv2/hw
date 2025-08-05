import java.io.*;
import java.util.*;

public class Solution {
    static int si;
    static int sj;
    static int ei;
    static int ej;
    static int[][] arr = new  int[100][100];
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};

    public static boolean inRange(int i, int j) {
        return i >= 0 && i < 100 && j >= 0 && j < 100;
    }

    public static int bfs(int si, int sj) {
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[100][100];
        int rtn = 0;

        q.offer(new int[] {si, sj});
        visited[si][sj] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            for (int d = 0; d < 4; d++) {
                int ni = ci + dis[d];
                int nj = cj + djs[d];

                if (inRange(ni, nj) && !visited[ni][nj] && arr[ni][nj] != 1) {
                    if (arr[ni][nj] == 3) {
                        rtn = 1;
                        return rtn;
                    }
                    visited[ni][nj] = true;
                    q.offer(new int[] {ni, nj});
                }
            }
        }
        return rtn;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {
            int TC = Integer.parseInt(br.readLine());

            for (int i = 0; i < 100; i++) {
                String line = br.readLine();
                for (int j = 0; j < 100; j++) {
                    arr[i][j] = line.charAt(j) - '0';
                    if (arr[i][j] == 2) {
                        si = i;
                        sj = j;
                    }
                    else {
                        ei = i;
                        ej = j;
                    }
                }
            }
            System.out.println("#" + tc + " " + bfs(si, sj));
        }
    }
}
