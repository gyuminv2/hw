import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static char[][] grid;
    static int[] dr = {-1, 0, 1};
    static int answer;

    static boolean inRange(int i, int j) {
        return 0 < i && i < R && 0 < j && j < C;
    }

    static boolean dfs(int r, int c) {
        grid[r][c] = 'x';

        if (c == C - 1) {
            return true;
        }

        for (int i = 0; i < 3; i++) {
            int nr = r + dr[i];
            int nc = c + 1;

            if (nr >= 0 && nr < R && grid[nr][nc] == '.') {
                if (dfs(nr, nc)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        grid = new char[R][C];
        answer = 0;

        for (int i = 0; i < R; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < R; i++) {
            if (dfs(i, 0)) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
