import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[] mount;
    static int total;

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int T = Integer.parseInt(br.readLine().trim());
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
//            StringTokenizer st = new StringTokenizer(br.readLine().trim());
//            N = Integer.parseInt(st.nextToken());
            N = sc.nextInt();
//            st = new StringTokenizer(br.readLine().trim());
            mount = new int[N];
//            for (int i = 0; i < N; i++) mount[i] = Integer.parseInt(st.nextToken());
            for (int i = 0; i < N; i++) mount[i] = sc.nextInt();
            total = 0;

            for (int i = 1; i < N-1; i++) {
                if (mount[i-1] < mount[i] && mount[i] > mount[i+1]) {
                    int lCnt = 0, rCnt = 0;
                    int idx = i-1;
                    while (idx > 0 && mount[idx-1] < mount[idx]) {
                        idx--;
                    }
                    lCnt = i - idx;

                    idx = i+1;
                    while (idx < N-1 && mount[idx] > mount[idx+1]) {
                        idx++;
                    }
                    rCnt = idx - i;
                    total += lCnt * rCnt;
                }
            }
            System.out.println("#" + tc + " " + total);
        }
    }
}