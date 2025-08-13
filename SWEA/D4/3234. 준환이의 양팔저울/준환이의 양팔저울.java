import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[] chu; // 현재 순열을 담을 배열
    static int answer;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());
            chu = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                chu[i] = Integer.parseInt(st.nextToken());
            }

            answer = 0;

            Arrays.sort(chu);

            do {
                solve(0, 0, 0);
            } while (nextPermutation(chu));

            System.out.println("#" + tc + " " + answer);
        }
    }

    static void solve(int count, int leftSum, int rightSum) {
        if (count == N) {
            answer++;
            return;
        }

        solve(count + 1, leftSum + chu[count], rightSum);

        if (rightSum + chu[count] <= leftSum) {
            solve(count + 1, leftSum, rightSum + chu[count]);
        }
    }

    static boolean nextPermutation(int[] arr) {
        int i = N - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) {
            i--;
        }
        if (i == 0) return false;

        int j = N - 1;
        while (arr[i - 1] >= arr[j]) {
            j--;
        }

        swap(arr, i - 1, j);

        int k = N - 1;
        while (i < k) {
            swap(arr, i++, k--);
        }
        return true;
    }

    static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}