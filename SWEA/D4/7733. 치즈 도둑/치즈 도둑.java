import java.io.*;
import java.util.*;

class Solution {
    static int N;
    static int[][] chs;
    static boolean[][] visited;

    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};

    static boolean inRange(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < N;
    }

    static void bfs(int si, int sj, int day) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{si, sj});
        visited[si][sj] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0], cj = cur[1];

            for (int d = 0; d < 4; d++) {
                int ni = ci + dis[d];
                int nj = cj + djs[d];

                if (inRange(ni, nj) && !visited[ni][nj] && chs[ni][nj] > day) {
                    q.offer(new int[]{ni, nj});
                    visited[ni][nj] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine());
            chs = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    chs[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int rtn = 1;
            for (int day = 1; day <= 100; day++) {
                visited = new boolean[N][N];
                int cnt = 0;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (chs[i][j] != 0 && !visited[i][j] && chs[i][j] > day) {
                            bfs(i, j, day);
                            cnt++;
                        }
                    }
                }
                rtn = Math.max(rtn, cnt);
            }
            sb.append("#").append(tc).append(" ").append(rtn).append("\n");
        }
        System.out.print(sb);
    }
}
