import java.io.*;
import java.util.*;

public class Solution {

    static long N;
    static boolean[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            numbers = new boolean[10];
            int cnt = 0;
            long time = 1;
            while (true) {
                String s = String.valueOf(time * N);

                for (int i = 0; i < s.length(); i++) {
                    int digit = s.charAt(i) - '0';
                    if (!numbers[digit]) {numbers[digit] = true; cnt++;}
                }

                if (cnt == 10) break;
                time++;
            }


            System.out.println("#" + tc + " " + N * time);
        }
    }
}