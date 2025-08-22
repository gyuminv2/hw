import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);

//        System.out.println(Arrays.toString(A));

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int cnt = 0;
//            int idx = lowerBound(A, list.get(i));
//            if (idx < A.length && A[idx] == list.get(i)) {
//                while (idx < A.length && A[idx] == list.get(i)) {
//                    cnt++;
//                    idx++;
//                }
//            }
            int len = upperBound(A, list.get(i)) - lowerBound(A, list.get(i));
            if (len == -1) len = 0;
//            System.out.print(cnt + " ");
//            System.out.print(len + " ");
            sb.append(len).append(" ");
        }
        System.out.println(sb);
    }

    static int lowerBound(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] >= target) end = mid - 1;
            else start = mid + 1;
        }
        return start;
    }

    static int upperBound(int[] arr, int target) {
        int start = 0;
        int end = arr.length;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] > target) end = mid;
            else start = mid + 1;
        }
        return start;
    }
}