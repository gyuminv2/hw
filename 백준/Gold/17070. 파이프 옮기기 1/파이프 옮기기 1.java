import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] grid;
    static int cnt = 0;

    public static void dfs(int i, int j, int dir) {
        if (i == N-1 && j == N-1) {
            cnt++;
            return ;
        }

        // dir = {0 : 가로, 1 : 세로, 2 : 대각}
        if (dir == 0 || dir == 2) {
            // 가로 이동
            int nj = j + 1;
            if (nj < N && grid[i][nj] == 0) {
                dfs(i, nj, 0);
            }
        }

        if (dir == 1 || dir == 2) {
            // 세로 이동
            int ni = i + 1;
            if (ni < N && grid[ni][j] == 0) {
                dfs(ni, j, 1);
            }
        }

        int ni = i + 1;
        int nj = j + 1;
        if (ni < N && nj < N && grid[i][nj] == 0 && grid[ni][j] == 0 && grid[ni][nj] == 0) {
            dfs(ni, nj, 2);
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 시작 위치 = (0, 1), 방향 = 0 (가로)
        dfs(0, 1, 0);

        System.out.print(cnt);
    }
}
