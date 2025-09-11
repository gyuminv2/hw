import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[] arr;
    static int[] dp;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            arr = new int[N+1];
            dp = new int[N+1];
            ans = Integer.MIN_VALUE;
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                int max = 0;
                for (int j = 1; j < i; j++) {
                    if (arr[j] < arr[i] && max < dp[j]) {
                        max = dp[j];
                    }
                }
                dp[i] = max + 1;
                ans = Math.max(ans, dp[i]);
            }

            System.out.println("#" + tc + " " + ans);
        }
    }
}