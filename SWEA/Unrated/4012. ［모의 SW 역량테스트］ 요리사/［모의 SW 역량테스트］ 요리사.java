import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[][] grid;
    static boolean[] v;
    static int min;

    static int calc(List<Integer> lst) {
        int score = 0;
        for (int i = 0; i < lst.size(); i++) {
            for (int j = 0; j < lst.size(); j++) {
                int ii = lst.get(i), jj = lst.get(j);
                score += grid[ii][jj];
            }
        }
        return score;
    }

    static void comb(int cnt, int start) {
        if (cnt == N/2) {
            List<Integer> A = new ArrayList<>();
            List<Integer> B = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (v[i]) A.add(i);
                else B.add(i);
            }

            int scoreA =  calc(A);
            int scoreB =  calc(B);

            min = Math.min(min, Math.abs(scoreA - scoreB));
            return ;
        }
        for (int i = start; i < N; i++) {
            v[i] = true;
            comb(cnt+1, i+1);
            v[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            grid = new int[N][N];
            v = new boolean[N];
            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    grid[i][j] = Integer.parseInt(input[j]);
                }
            }
            min = Integer.MAX_VALUE;
//            for(int[] row : grid) System.out.println(Arrays.toString(row));

            comb(0, 0);

            System.out.println("#" + tc + " " + min);
        }
    }
}