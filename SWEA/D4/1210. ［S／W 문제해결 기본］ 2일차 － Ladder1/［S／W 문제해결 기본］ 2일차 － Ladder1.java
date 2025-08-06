
import java.io.*;
import java.util.*;

public class Solution {

    static int[][] grid;
    static boolean[][] visited;
    // 좌 우 상
    static int[] dis = {0, 0, -1};
    static int[] djs = {-1, 1, 0};

    static class Pos {
        int row, col;

        Pos (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static boolean inRange(int i, int j) {
        return 0 <= i && i < 100 && 0 <= j && j < 100;
    }

    static int bfs(Pos end) {
        int ei = end.row;
        int ej = end.col;

        Deque<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(ei, ej));
        visited[ei][ej] = true;

        while (!q.isEmpty()) {
            Pos cur = q.poll();
            int ci = cur.row;
            int cj = cur.col;

            if (ci == 0) return cj;

            for (int d = 0; d < 3; d++) {
                int ni = ci + dis[d];
                int nj = cj + djs[d];

                if (inRange(ni, nj) && !visited[ni][nj] && grid[ni][nj] == 1) {
                    q.offer(new Pos(ni, nj));
                    visited[ni][nj] = true;
                    break ;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {

            br.readLine();
            Pos end = null;
            grid = new int[100][100];
            visited = new boolean[100][100];

            for (int i = 0; i < 100; i++) {
                String[] input = br.readLine().split(" ");
                for (int j = 0; j < 100; j++) {
                    grid[i][j] = Integer.parseInt(input[j]);
                    if (grid[i][j] == 2) end = new Pos(i, j);
                }
            }
            System.out.println("#" + tc + " " + bfs(end));
        }
    }
}
