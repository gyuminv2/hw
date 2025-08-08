import java.io.*;
import java.util.*;

public class Solution {

    static int N, L;
    static int[] taste, cal;
    static int rtn;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            L = Integer.parseInt(input[1]);
            taste = new int[N];
            cal = new int[N];
            rtn = 0;
            for (int i = 0; i < N; i++) {
                input = br.readLine().split(" ");
                taste[i] = Integer.parseInt(input[0]);
                cal[i] = Integer.parseInt(input[1]);
            }

            subs(0, 0, 0);

            System.out.println("#" + t + " " + rtn);
        }
    }

    static void subs(int cnt, int sumT, int sumC) {
        if (sumC > L) return ;
        if (cnt == N) {
            rtn = Math.max(rtn, sumT);
            return ;
        }
        subs(cnt+1, sumT + taste[cnt], sumC + cal[cnt]);
        subs(cnt+1, sumT, sumC);
    }
}