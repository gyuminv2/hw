import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[][] grid;
    static boolean[][] v;
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};

    static boolean inRange(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }

//    static int gain(int i, int j) {
//        int suc = 1;
//        for (int d = 0; d < 4; d++) {
//            int ni = i + dis[d];
//            int nj = j + djs[d];
//            if (!inRange(ni, nj) && !v[ni][nj]) {
//                suc = 0;
//                return 0;
//            }
//        }
//
//    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC =  Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {

            N = Integer.parseInt(br.readLine());
            grid = new int[N][N];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    grid[i][j] =line.charAt(j) - '0';
                }
            }
            if (N == 1) {
                System.out.println("#" + tc + " " + grid[0][0]);
                continue;
            }
            v = new boolean[N][N];

            for (int i = 0; i < N/2; i++) {
                for (int j = N/2 - i; j < N - (N/2 - i); j++) {
                    v[i][j] = true;
                }
            }
            for (int j = 0; j < N; j++) {
                v[N/2][j] = true;
            }
            for (int i = N/2 + 1; i < N; i++) {
                for (int j = i - N/2; j < N - (i - N/2); j++) {
                    v[i][j] = true;
                }
            }
//            for(boolean[] row : v){
//                System.out.println(Arrays.toString(row));
//            }

            int rtn = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                   if (v[i][j]) rtn += grid[i][j];
                }
            }

            System.out.println("#" + tc + " " + rtn);
        }
    }
}