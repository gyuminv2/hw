import java.io.*;
import java.util.*;

public class Solution {

    static int[] pay;
    static int[] plan;
    static int min;

    static void dfs(int cnt, int money) {
        if (cnt >= 13) {
            min = Math.min(min, money);
            return;
        }
        if (money >= min) return ;
        if (plan[cnt] == 0) dfs(cnt+1, money);
        else {
            dfs(cnt+1, money + plan[cnt] * pay[0]);
            dfs(cnt+1, money + pay[1]);
            dfs(cnt+3, money + pay[2]);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            pay = new int[4];
            String[] input = br.readLine().split(" ");
            for (int i = 0; i < 4; i++) pay[i] = Integer.parseInt(input[i]);
            plan = new int[13];
            input = br.readLine().split(" ");
            for (int i = 1; i <= 12; i++) plan[i] = Integer.parseInt(input[i-1]);
            min = Integer.MAX_VALUE;

            dfs(1, 0);
            min = Math.min(min, pay[3]);
            System.out.println("#" + tc + " " + min);
        }
    }
}