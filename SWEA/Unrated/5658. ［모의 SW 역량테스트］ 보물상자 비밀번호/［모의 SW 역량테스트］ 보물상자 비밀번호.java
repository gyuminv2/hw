import java.io.*;
import java.util.*;

public class Solution {

    static int N, K;
    static int W;
    static char[] arr;
    static TreeSet<String> cand;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            W = N/4;
            arr = new char[N];
            String input = br.readLine();
            arr = input.toCharArray();
            cand = new TreeSet<>((s1, s2) -> s2.compareTo(s1));

            for (int i = 0; i < N; i+=W) {
                String sub = new String(arr, i, W);
                cand.add(sub);
            }

            for (int t = 0; t < W; t++) {
                char tmp = arr[arr.length-1];
                for (int j = arr.length-2; j >= 0; j--) {
                    arr[j+1] = arr[j];
                }
                arr[0] = tmp;

                for (int i = 0; i < N; i+=W) {
                    String sub = new String(arr, i, W);
                    cand.add(sub);
                }
            }

            List<String> list = new ArrayList<>(cand);

            System.out.println("#" + tc + " " + Integer.parseInt(list.get(K-1), 16));
        }
    }
}