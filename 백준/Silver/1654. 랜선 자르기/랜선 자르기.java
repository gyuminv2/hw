import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        long[] lansun = new long[K];

        long max = 0;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            lansun[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, lansun[i]);
        }

        long mid = 0;
        long min = 0;
        max+=1;
        while (min < max) {
            mid = (max + min) / 2;

            int cnt = 0;
            for (int i = 0; i < K; i++) {
                cnt += (lansun[i] / mid);
            }

            if (cnt < N) max = mid;
            else min = mid + 1;
        }

        System.out.println(min-1);
    }
}