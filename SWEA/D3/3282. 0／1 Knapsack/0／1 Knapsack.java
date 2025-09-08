import java.io.*;
import java.util.*;

public class Solution {

    static int N, K;
    static int[] V;
    static int[] C;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            V = new int[N+1];
            C = new int[N+1];
            dp = new int[N+1][K+1];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                V[i] = Integer.parseInt(st.nextToken());
                C[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < N+1; i++) dp[i][0] = 0;
            for (int i = 0; i < K+1; i++) dp[0][i] = 0;

            for (int i = 1; i < N+1; i++) {
                for (int j = 1; j < K+1; j++) {
                    if (j < V[i]) {
                        dp[i][j] = dp[i-1][j];
                    }
                    else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-V[i]] + C[i]);
                    }
                }
            }

            System.out.println("#" + tc + " " + dp[N][K]);
        }
    }
}