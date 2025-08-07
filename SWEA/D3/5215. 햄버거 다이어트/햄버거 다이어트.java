import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int L;
    static int[] score;
    static int[] cal;
    static int rtn;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc =  Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            String[] input = br.readLine().split(" ");

            N = Integer.parseInt(input[0]);
            L = Integer.parseInt(input[1]);
            score = new int[N];
            cal = new int[N];
            rtn = 0;

            for (int i = 0; i < N; i++) {
                String[] burger = br.readLine().split(" ");
                score[i] = (Integer.parseInt(burger[0]));
                cal[i] = (Integer.parseInt(burger[1]));
            }
            subs(0, 0, 0);
            System.out.println("#" + t + " " + rtn);
        }
    }

    static void subs(int cnt, int sumScore, int sumCal){
        if (sumCal > L) {
            return;
        }

        if (cnt == N) {
            rtn = Math.max(rtn, sumScore);
            return;
        }
        subs(cnt+1, sumScore + score[cnt], sumCal + cal[cnt]);
        subs(cnt+1, sumScore, sumCal);
    }
}
