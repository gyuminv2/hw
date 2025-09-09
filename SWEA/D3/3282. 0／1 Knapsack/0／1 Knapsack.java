import java.io.*;
import java.util.*;

public class Solution {

    static int N, K;
    static int[] V, C;
    static int dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringTokenizer st;

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            V = new int[N+1];
            C = new int[N+1];
            dp = new int[N+1][K+1];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine().trim());
                V[i] = Integer.parseInt(st.nextToken());
                C[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= K; j++) {
                    if (V[i] > j) dp[i][j] = dp[i-1][j];
                    else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-V[i]] + C[i]);
                }
            }

            System.out.println("#" + tc + " " + dp[N][K]);
        }
    }
}