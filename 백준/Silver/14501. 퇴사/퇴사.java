import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] T = new int[N];
        int[] P = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N+1];
        for (int i = N-1; i >= 0; i--) {
            if (i + T[i] <= N) // i일에 상담 O
                dp[i] = Math.max(dp[i+1], dp[i + T[i]] + P[i]);
            else dp[i] = dp[i+1]; // i일에 상담 X
        }

        System.out.println(dp[0]);
    }
}
