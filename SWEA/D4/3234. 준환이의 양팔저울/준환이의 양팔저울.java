import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[] chu;
    static boolean[] v;
    static int rtn;

    static void go(int cnt, int left, int right) {
        if (cnt == N) {
            rtn++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!v[i]) {
                v[i] = true;
                go(cnt+1, left + chu[i], right);
                if (left >= right + chu[i])  go(cnt+1, left, right + chu[i]);
                v[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            chu = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)  chu[i] = Integer.parseInt(st.nextToken());
            v = new boolean[N];
            rtn = 0;

            go(0, 0, 0);
            System.out.println("#" + t + " " + rtn);
        }
    }
}