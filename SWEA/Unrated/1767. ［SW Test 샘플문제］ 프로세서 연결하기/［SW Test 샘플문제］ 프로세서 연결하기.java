import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[][] grid;
    static ArrayList<int[]> core;
    static int maxCoreCnt;
    static int minWireLength;
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};

    static boolean inRange(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < N;
    }

    static int check(int ci, int cj, int d) {
        int len = 0;
        int ni = ci, nj = cj;
        while (true) {
            ni += dis[d];
            nj += djs[d];
            if (!inRange(ni, nj)) return len;
            if (grid[ni][nj] != 0) return 0;
            len++;
        }
    }

    static void fillWire(int ci, int cj, int d, int len, int f) {
        int ni = ci, nj = cj;
        for (int i = 0; i < len; i++) {
            ni += dis[d];
            nj += djs[d];
            grid[ni][nj] = f;
        }
    }

    static void dfs(int idx, int connectCnt, int wireLSum) {
        if (idx == core.size()) {
            if (connectCnt > maxCoreCnt) {
                maxCoreCnt = connectCnt;
                minWireLength = wireLSum;
            }
            else if (connectCnt == maxCoreCnt) {
                minWireLength = Math.min(minWireLength, wireLSum);
            }
            return;
        }

        int ci = core.get(idx)[0];
        int cj = core.get(idx)[1];
        for (int d = 0; d < 4; d++) {
            int len = check(ci, cj, d);
            if (len > 0) {
                fillWire(ci, cj, d, len, 2);
                dfs(idx+1, connectCnt+1, wireLSum+len);
                fillWire(ci, cj, d, len, 0);
            }
        }

        dfs(idx+1, connectCnt, wireLSum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            grid = new int[N][N];
            core = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    grid[i][j] = Integer.parseInt(input[j]);
                    if (grid[i][j] == 1 && i != 0 && j != 0 && i != N-1 && j != N-1)
                        core.add(new int[] {i, j});
                }
            }

            maxCoreCnt = 0;
            minWireLength = Integer.MAX_VALUE;

            dfs(0, 0, 0);

            System.out.println("#" + tc + " " + minWireLength);
        }
    }
}