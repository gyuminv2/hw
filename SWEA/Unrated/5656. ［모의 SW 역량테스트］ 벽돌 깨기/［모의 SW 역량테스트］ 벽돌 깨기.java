import java.io.*;
import java.util.*;

public class Solution {

    static int N, W, H;
    static int[][] grid;
    static int min;
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};

    static boolean inRange(int i, int j) {
        return i >= 0 && i < H && j >= 0 && j < W;
    }

    static void gravity(int[][] nGrid) {
        for (int j = 0; j < W; j++) {
            Queue<Integer> q = new LinkedList<>();
            for (int i = H-1; i >= 0; i--) {
                if (nGrid[i][j] > 0) {
                    q.offer(nGrid[i][j]);
                    nGrid[i][j] = 0;
                }
            }

            int i = H-1;
            while (!q.isEmpty()) {
                nGrid[i--][j] = q.poll();
            }
        }
    }

    static void boom(int[][] nGrid, int sj) {
        // 벽돌찾기
        int si = -1;
        for (int i = 0; i < H; i++) {
            if (nGrid[i][sj] > 0) {
                si = i;
                break;
            }
        }
        if (si == -1) return ;

        // 연쇄
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {si, sj});

        boolean[][] visited = new boolean[H][W];
        visited[si][sj] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int uranium = nGrid[ci][cj];

            for (int i = 0; i < uranium; i++) {
                for (int d = 0; d < 4; d++) {
                    int ni = ci + i * dis[d];
                    int nj = cj + i * djs[d];
                    if (inRange(ni, nj) && nGrid[ni][nj] > 0 && !visited[ni][nj]) {
                        visited[ni][nj] = true;
                        q.offer(new int[] {ni, nj});
                    }
                }
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (visited[i][j]) nGrid[i][j] = 0;
            }
        }
    }

    static void simul(int[][] nGrid, int[] beads) {
        for (int i = 0; i < N; i++) {
            int sj = beads[i];
            boom(nGrid, sj);
            gravity(nGrid);
        }

        int remain = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (nGrid[i][j] > 0) remain++;
            }
        }
        min = Math.min(min, remain);
    }

    static void perm(int cnt, int[] beads) {
        if (cnt == N) {
            int[][] nGrid = new int[H][W];
            for (int i = 0; i < H; i++) nGrid[i] = grid[i].clone();
            simul(nGrid, beads);
            return ;
        }
        for (int i = 0; i < W; i++) {
            beads[cnt] = i;
            perm(cnt+1, beads);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            grid = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            min = Integer.MAX_VALUE;

            int[] beads = new int[N];

            perm(0, beads);

            System.out.println("#" + tc + " " + min);
        }
    }
}

/**
 * 중복 순열로 구슬 뽑아서 (0 ~ W-1 까지의 중복순열)
 * 떨어뜨리는 시뮬
 * 연쇄 파괴 함수
 * 중력 함수
 * 블록 계산 함수
 */


/*
5
3 10 10
0 0 0 0 0 0 0 0 0 0
1 0 1 0 1 0 0 0 0 0
1 0 3 0 1 1 0 0 0 1
1 1 1 0 1 2 0 0 0 9
1 1 4 0 1 1 0 0 1 1
1 1 4 1 1 1 2 1 1 1
1 1 5 1 1 1 1 2 1 1
1 1 6 1 1 1 1 1 2 1
1 1 1 1 1 1 1 1 1 5
1 1 7 1 1 1 1 1 1 1
 */