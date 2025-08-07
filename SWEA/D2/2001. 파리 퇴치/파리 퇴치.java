import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int M;
    static int[][] grid;
    static int max;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            grid = new int[N][N];
            max = -1;

            for (int i = 0; i < N; i++) {
                String[] line = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    grid[i][j] = Integer.parseInt(line[j]);
                }
            }

            for (int i = 0; i <= N-M; i++) {
                for (int j = 0; j <= N-M; j++) {
                    int tmp = 0;
                    for (int k = 0; k < M; k++) {
                        for (int l = 0; l < M; l++) {
                            tmp += grid[i+k][j+l];
                        }
                    }
                    max = Math.max(max, tmp);
                }
            }
            System.out.println("#" + t + " " + max);
        }
    }
}