import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static char[][] grid;
    static int[][] info;
    static boolean[][] v;
    static int ans;

    // 상, 우상, 우, 우하, 하, 좌하, 좌, 좌상
    static int[] dis = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] djs = {0, 1, 1, 1, 0, -1, -1, -1};

    static boolean inRange(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < N;
    }

    static void bfs(int si, int sj) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {si, sj});
        v[si][sj] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            for (int d = 0; d < 8; d++) {
                int ni = ci + dis[d];
                int nj = cj + djs[d];
                if (inRange(ni, nj) && !v[ni][nj] && grid[ni][nj] != '*') {
                    v[ni][nj] = true;
                    if (info[ni][nj] == 0)
                        q.offer(new int[] {ni, nj});
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC =  Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());

            N = Integer.parseInt(st.nextToken());
            grid = new char[N][N];
            info = new int[N][N];
            v = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                String input = br.readLine().trim();
                for (int j = 0; j < N; j++) {
                    grid[i][j] = input.charAt(j);
                }
            }
            int ni = 0, nj = 0;
            int num = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    num = 0;
                    for (int d = 0; d < 8; d++) {
                        ni = i + dis[d]; nj = j + djs[d];
                        if (inRange(ni, nj) && grid[ni][nj] == '*') {
                            num++;
                        }
                    }
                    info[i][j] = num;
                }
            }
            ans = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (grid[i][j] == '.' && !v[i][j] && info[i][j] == 0) {
                        bfs(i, j);
                        ans++;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (grid[i][j] == '.' && !v[i][j]) {
                        ans++;
                    }
                }
            }

            System.out.println("#" + tc + " " + ans);
        }
    }
}

