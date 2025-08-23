import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Long.parseLong(st.nextToken());
        Arrays.sort(arr);

        int start = 0;
        int end = 0;
        int mid = 0;
        long rtn = Long.MAX_VALUE;
        int[] best = new int[3];

        for (int i = 0; i < N-2; i++) {
            start = i;
            mid = i+1;
            end = N-1;
            while (mid < end) {
                long sum = (arr[start] + arr[mid] + arr[end]);

                if (Math.abs(sum) < rtn) {
                    rtn = Math.abs(sum);
                    best[0] = start;
                    best[1] = mid;
                    best[2] = end;
                }
                if (sum == 0) break;
                else if (sum > 0) end --;
                else mid ++;
            }
        }
        System.out.println(arr[best[0]] + " " + arr[best[1]] + " " + arr[best[2]]);
    }
}
