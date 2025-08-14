import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[][] grid;
    static HashMap<Integer, List<int[]>> wormhole;
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};
    static int max;
    static int[][] blockDirs = {
        {},
        {1, 3, 0, 2},
        {3, 0, 1, 2},
        {2, 0, 3, 1},
        {1, 2, 3, 0},
        {1, 0, 3, 2}
    };

    static boolean isBlackHole(int i, int j) {
        return grid[i][j] == -1;
    }

    static boolean isStart(int si, int sj, int ni, int nj) {
        return si == ni &&  sj == nj;
    }

    static boolean inRange(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }

    static int simul(int si, int sj, int sdr) {
        int ci = si;
        int cj = sj;
        int dr = sdr;
        int score = 0;

        while (true) {
            ci += dis[dr];
            cj += djs[dr];

            if (!inRange(ci, cj)) {
                score++;
                dr = (dr % 2 == 0) ? dr + 1 : dr - 1;
                continue;
            }

            if (isStart(si, sj, ci, cj) || isBlackHole(ci, cj)) {
                break;
            }

            int cur = grid[ci][cj];
            if (cur >= 6) {
                List<int[]> pair = wormhole.get(cur);
                int[] w1 = pair.get(0);
                int[] w2 = pair.get(1);
                if (ci == w1[0] && cj == w1[1]) {
                    ci = w2[0];
                    cj = w2[1];
                } else {
                    ci = w1[0];
                    cj = w1[1];
                }
            }
            else if (1 <= cur) {
                score++;
                dr = blockDirs[cur][dr];
            }
        }
        return score;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC =  Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {

            N = Integer.parseInt(br.readLine().trim());
            grid = new int[N][N];
            wormhole = new HashMap<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                    if (grid[i][j] >= 6) {
                        if (!wormhole.containsKey(grid[i][j])) {
                            wormhole.put(grid[i][j], new ArrayList<>());
                        }
                        wormhole.get(grid[i][j]).add(new int[]{i, j});
                    }
                }
            }
            max = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int d = 0; d < 4; d++) {
                        if (grid[i][j] == 0)
                            max = Math.max(max, simul(i, j, d));
                    }
                }
            }
            System.out.println("#" + tc + " " + max);
        }
    }
}