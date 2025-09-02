import java.io.*;
import java.util.*;

public class Solution {

    static int N, M, C;
    static int[][] grid;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            grid = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("#" + tc + " " + solve());
        }
    }

    static int solve() {
        int prof = 0;

        for (int i1 = 0; i1 < N; i1++) {
            for (int j1 = 0; j1 <= N - M; j1++) {
                for (int i2 = 0; i2 < N; i2++) {
                    for (int j2 = 0; j2 <= N - M; j2++) {
                        if (i1 == i2 && j2 < j1 + M) {
                            continue;
                        }

                        int profitA = getMaxProfitFrom(i1, j1);
                        int profitB = getMaxProfitFrom(i2, j2);

                        prof = Math.max(prof, profitA + profitB);
                    }
                }
            }
        }
        return prof;
    }

    static int getMaxProfitFrom(int r, int c) {
        int[] honey = new int[M];
        for (int i = 0; i < M; i++) {
            honey[i] = grid[r][c + i];
        }

        max = Integer.MIN_VALUE;
        subs(honey, 0, 0, 0);
        return max;
    }

    static void subs(int[] honey, int index, int sum, int profit) {
        if (sum > C) return;

        if (index == M) {
            max = Math.max(max, profit);
            return;
        }

        int currentHoney = honey[index];
        subs(honey, index + 1, sum + currentHoney, profit + currentHoney * currentHoney);
        subs(honey, index + 1, sum, profit);
    }
}