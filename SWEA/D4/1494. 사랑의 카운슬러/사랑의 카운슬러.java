import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static Point[] worm;
    static Point[] b;
    static long totalX;
    static long totalY;
    static long ans;

    static class Point {
        long i, j;

        Point(long i, long j) {
            this.i = i;
            this.j = j;
        }
    }

    static void comb(int cnt, int start) {
        if (cnt == N/2) {
            long sumX = 0;
            long sumY = 0;
            for (Point p : b) {
                sumX += p.i;
                sumY += p.j;
            }
            sumX = totalX - 2 * sumX;
            sumY = totalY - 2 * sumY;
            ans = Math.min(ans, sumX * sumX + sumY * sumY);
            return ;
        }
        for (int i = start; i < N; i++) {
            b[cnt] = worm[i];
            comb(cnt+1, i+1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            worm = new Point[N];
            b = new Point[N/2];
            totalX = 0;
            totalY = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                worm[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                totalX += worm[i].i;
                totalY += worm[i].j;
            }
            ans = Long.MAX_VALUE;

            comb(0, 0);

            System.out.println("#" + tc + " " + ans);
        }
    }
}