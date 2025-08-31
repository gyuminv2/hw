import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] grid;
    static int whiteCnt = 0;
    static int blueCnt = 0;

    static void solve(int ci, int cj, int sz) {
        int color = grid[ci][cj];
        boolean isSame = true;

        for (int i = ci; i < ci + sz; i++) {
            for (int j = cj; j < cj + sz; j++) {
                if (grid[i][j] != color) {
                    isSame = false;
                    break;
                }
            }
            if (!isSame) break;
        }

        if (isSame) {
            if (color == 0) whiteCnt++;
            else blueCnt++;
            return;
        }

        int newSz = sz/2;
        solve(ci, cj, newSz);
        solve(ci, cj + newSz, newSz);
        solve(ci + newSz, cj, newSz);
        solve(ci + newSz, cj + newSz, newSz);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, N);

        System.out.println(whiteCnt);
        System.out.println(blueCnt);
    }
}
