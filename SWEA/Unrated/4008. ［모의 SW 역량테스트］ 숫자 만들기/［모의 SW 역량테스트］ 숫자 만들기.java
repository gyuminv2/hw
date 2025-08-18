import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[] oper;
    static int[] num;
    static int max, min;

    static void perm(int cnt, int rtn) {
        if (cnt == N) {
            max = Math.max(max, rtn);
            min = Math.min(min, rtn);
            return ;
        }

        if (Math.abs(rtn) < 1) rtn = 0;

        if (oper[0] > 0) {
            oper[0]--;
            perm(cnt+1, rtn + num[cnt]);
            oper[0]++;
        }

        if (oper[1] > 0) {
            oper[1]--;
            perm(cnt+1, rtn - num[cnt]);
            oper[1]++;
        }
        if (oper[2] > 0) {
            oper[2]--;
            perm(cnt+1, rtn * num[cnt]);
            oper[2]++;
        }
        if (oper[3] > 0) {
            oper[3]--;
            perm(cnt+1, rtn / num[cnt]);
            oper[3]++;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC =  Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine());
            oper = new int[4];
            num = new int[N];
            String[] input = br.readLine().split(" ");
            for (int i = 0; i < 4; i++) {
                oper[i] = Integer.parseInt(input[i]);
            }
            input = br.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                num[i] = Integer.parseInt(input[i]);
            }
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;

            perm(1, num[0]);

            System.out.println("#" + tc + " " + (max - min));
        }
    }
}