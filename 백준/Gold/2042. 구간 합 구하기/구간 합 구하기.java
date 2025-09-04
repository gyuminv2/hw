import java.io.*;
import java.util.*;

public class Main {

    static long N, M, K, sqrtN;
    static long[] arr;
    static long[] buk;

    static void init() {
        sqrtN = (long)Math.sqrt(N);
        for (long i = 1; i <= N; i++) {
            buk[(int)(i/sqrtN)] += arr[(int)i];
        }
    }

    static void update(long x, long val) {
        long idx = x / sqrtN;
        buk[(int)idx] -= arr[(int)x];
        buk[(int)idx] += val;
        arr[(int)x] = val;
    }

    static long sum(long l, long r) {
        long ret = 0;

        while (l%sqrtN != 0 && l <= r) {
            ret += arr[(int)l];
            l++;
        }
        while ((r+1)%sqrtN != 0 && l <= r) {
            ret += arr[(int)r];
            r--;
        }
        while (l <= r) {
            ret += buk[(int)(l/sqrtN)];
            l += sqrtN;
        }
        return ret;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());
        arr = new long[(int)N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Long.parseLong(st.nextToken());
        }
        buk = new long[(int)N*4];

        init();
        for (long i = N+2; i <= N+M+K+1; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            // update
            if (a == 1) update(b, c);
            // prlong
            else System.out.println(sum(b, c));
        }


    }
}
