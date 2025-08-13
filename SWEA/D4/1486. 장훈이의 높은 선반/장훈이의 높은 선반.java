import java.io.*;
import java.util.*;

public class Solution {

    static int N, B;
    static int[] ps;
    static int min;

    static void subs(int cnt, int height) {
        if (cnt == N) {
            if (height >= B) {
                min = Math.min(min, height);
            }
            return;
        }
        subs(cnt + 1, height + ps[cnt]);
        subs(cnt + 1, height);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            ps = new int[N];
            min = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                ps[i] = Integer.parseInt(st.nextToken());
            }

            subs(0, 0);
            if (min == Integer.MAX_VALUE) {
                System.out.println("#" + t + " " + 0);
            }
            else {
                System.out.println("#" + t + " " + (min - B));
            }
        }
    }
}