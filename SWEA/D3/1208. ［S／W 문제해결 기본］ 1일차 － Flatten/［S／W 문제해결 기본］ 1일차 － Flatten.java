import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 1; t <= 10; t++) {
            int dump =  Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            int[] arr = new int[100];
            for (int i = 0; i < 100; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            while (dump > 0) {
                Arrays.sort(arr);
                arr[0] += 1;
                arr[arr.length - 1] -= 1;
                dump -= 1;
            }

            Arrays.sort(arr);
            System.out.println("#" + t + ' ' + (arr[arr.length - 1] - arr[0]));
        }
    }
}