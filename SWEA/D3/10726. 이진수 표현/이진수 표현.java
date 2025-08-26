import java.io.*;
import java.util.*;

public class Solution {

    static int N, M;
    static boolean[] integer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            integer = new boolean[32];

            int num = 1;
            int cnt = 0;
            while (true) {
                num *= 2;
                cnt++;
                if (M == 0)
                    break;
                if (M == 1) {
                    integer[31] = true;
                    break;
                }
                if (num > M) {
                    integer[32-cnt] = true;
                    M -= (num / 2);
                    num = 1;
                    cnt = 0;
                }
            }
            String s = "ON";
            for (int i = 32-N; i < 32; i++) {
                if (!integer[i]) {
                    s = "OFF";
                    break;
                }
            }

            System.out.println("#" + tc + " " + s);
        }
    }
}