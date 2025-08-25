import java.io.*;
import java.util.*;

public class Main {

    static int W;
    static int H;
    static int[][] grid;
    static boolean[][] v;
    // 상 상우 우 하우 하 하좌 좌 좌상
    static int[] dis = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] djs = {0, 1, 1, 1, 0, -1, -1, -1};
    static int ans;

    static boolean inRange(int i, int j) {
        return i >= 0 && i < H && j >= 0 && j < W;
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
                if (inRange(ni, nj) && !v[ni][nj] && grid[ni][nj] == 1) {
                    q.offer(new int[] {ni, nj});
                    v[ni][nj] = true;
                }
            }

        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            if (W == 0 && H == 0) {break;}
            grid = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            v = new boolean[H][W];
            ans = 0;

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (grid[i][j] == 1 && !v[i][j]) {
                        bfs(i, j);
                        ans++;
                    }
                }
            }

            System.out.println(ans);
        }
    }
}