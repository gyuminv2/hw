import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        for (int t = 1; t <= TC; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            int l = 0;
            int r = N-1;
            int max = -1;

            while (l < r) {
                int sum = arr[l] + arr[r];

                if (sum > M) r--;
                else {
                    max = Math.max(max, sum);
                    l++;
                }
            }
            System.out.println("#" + t + " " + max);
        }
    }
}