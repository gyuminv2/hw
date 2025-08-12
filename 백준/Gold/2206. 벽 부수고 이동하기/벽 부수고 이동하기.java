import java.io.*;
import java.util.*;

public class Main {

    static int[][] grid;
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};
    static int N, M;

    static class Point {
        int i;
        int j;
        int broken;

        Point(int i, int j, int broken) {
            this.i = i;
            this.j = j;
            this.broken = broken;
        }
    }

    static boolean inRange(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < M;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                grid[i][j] = line.charAt(j) - '0';
            }
        }

        Deque<Point> q = new ArrayDeque<>();
        int[][][] v = new int[N][M][2];

        q.push(new Point(0, 0, 0));
        v[0][0][0] = 1;

        int rtn = -1;
        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.i == N - 1 && p.j == M - 1) {
                rtn = v[p.i][p.j][p.broken];
                break;
            }

            for (int d = 0; d < 4; d++) {
                int ni = p.i + dis[d];
                int nj = p.j + djs[d];

                if (inRange(ni, nj) && v[ni][nj][p.broken] == 0 && grid[ni][nj] == 0) {
                    v[ni][nj][p.broken] = v[p.i][p.j][p.broken] + 1;
                    q.offer(new Point(ni, nj, p.broken));
                }

                if (inRange(ni, nj) && v[ni][nj][1] == 0 && (grid[ni][nj] == 1 && p.broken == 0)) {
                    v[ni][nj][1] = v[p.i][p.j][0] + 1;
                    q.offer(new Point(ni, nj, p.broken + 1));
                }
            }
        }
        System.out.println(rtn);
    }
}
