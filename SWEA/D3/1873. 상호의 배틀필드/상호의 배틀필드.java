import java.io.*;
import java.util.*;

public class Solution {

    static int H, W;
    static char[][] grid;
    static int N;
    static char[] cmd;
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};
    static int ti, tj;

    static boolean inRange(int i, int j) {
        return 0 <= i && i < H && 0 <= j && j < W;
    }

    static void findTargetAndShoot(int si, int sj, int sd) {
        int ci = si, cj = sj, cd = sd;
        while (true) {
            int ni = ci + dis[cd];
            int nj = cj + djs[cd];
            if (!inRange(ni, nj)) return ;
            if (grid[ni][nj] == '#') return ;
            if (grid[ni][nj] == '*') {
                grid[ni][nj] = '.';
                return ;
            }
            ci = ni; cj = nj;
        }
    }

    static void loop(int si, int sj, int sd) {
        int ci = si, cj = sj, cd = sd;

        int t = 0;
        while (t != cmd.length) {
            if (cmd[t] == 'U') {
                cd = 0;
                grid[ci][cj] = '^';
                int ni = ci + dis[cd];
                if (inRange(ni, cj) && grid[ni][cj] == '.') {
                    grid[ci][cj] = '.';
                    ci = ni;
                    grid[ci][cj] = '^';
                }
            }
            if (cmd[t] == 'D') {
                cd = 1;
                grid[ci][cj] = 'v';
                int ni = ci + dis[cd];
                if (inRange(ni, cj) && grid[ni][cj] == '.') {
                    grid[ci][cj] = '.';
                    ci = ni;
                    grid[ci][cj] = 'v';
                }
            }
            if (cmd[t] == 'L') {
                cd = 2;
                grid[ci][cj] = '<';
                int nj = cj + djs[cd];
                if (inRange(ci, nj) && grid[ci][nj] == '.') {
                    grid[ci][cj] = '.';
                    cj = nj;
                    grid[ci][cj] = '<';
                }
            }
            if (cmd[t] == 'R') {
                cd = 3;
                grid[ci][cj] = '>';
                int nj = cj + djs[cd];
                if (inRange(ci, nj) && grid[ci][nj] == '.') {
                    grid[ci][cj] = '.';
                    cj = nj;
                    grid[ci][cj] = '>';
                }
            }
            if (cmd[t] == 'S') {
                findTargetAndShoot(ci, cj, cd);
            }
            t++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            String[] input = br.readLine().split(" ");
            H = Integer.parseInt(input[0]);
            W = Integer.parseInt(input[1]);
            grid = new char[H][W];

            int ci = -1, cj = -1, cd = -1;
            for (int i = 0; i < H; i++) {
                String line = br.readLine();
                for (int j = 0; j < W; j++) {
                    grid[i][j] = line.charAt(j);
                    if (grid[i][j] == '^' || grid[i][j] == 'v' || grid[i][j] == '<' || grid[i][j] == '>') {
                        ci = i;
                        cj = j;
                        if (grid[i][j] == '^') cd = 0;
                        else if (grid[i][j] == 'v') cd = 1;
                        else if (grid[i][j] == '<') cd = 2;
                        else cd = 3;
                    }
                }
            }
            N = Integer.parseInt(br.readLine());
            cmd = new char[N];
            cmd = br.readLine().toCharArray();

            loop(ci, cj, cd);

            System.out.print("#" + tc + " ");
            for(char[] row : grid) System.out.println(row);
        }
    }
}