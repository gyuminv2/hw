import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static char[][] grid;
    static int si, sj;
    static boolean[][][] v;
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};

    static boolean inRange(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < M;
    }

    static int bfs(int si, int sj) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {si, sj, 0, 0});
        v[si][sj][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int cnt = cur[2];
            int mask = cur[3];

            if (grid[ci][cj] == '1') return cnt;

            for (int d = 0; d < 4; d++) {
                int ni = ci + dis[d];
                int nj = cj + djs[d];

                if (!inRange(ni, nj) || grid[ni][nj] == '#') continue;

                int nextMask = mask;
                if (grid[ni][nj] >= 'a' && grid[ni][nj] <= 'f') {
                    nextMask |= (1 << (grid[ni][nj] - 'a'));
                }
                else if (grid[ni][nj] >= 'A' && grid[ni][nj] <= 'F') {
                    if ((mask & (1 << (grid[ni][nj] - 'A'))) == 0) continue;
                }
                if (!v[ni][nj][nextMask]) {
                    v[ni][nj][nextMask] = true;
                    q.offer(new int[] {ni, nj, cnt+1, nextMask});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == '0') {
                    si = i;
                    sj = j;
                }
            }
        }
        v = new boolean[N][M][64];

        System.out.println(bfs(si, sj));
    }
}
