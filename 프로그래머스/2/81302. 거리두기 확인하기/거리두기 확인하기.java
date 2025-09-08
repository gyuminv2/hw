import java.io.*;
import java.util.*;

class Solution {
    
    static int N = 5;
    static char[][] grid;
    static boolean[][] v;
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};
    
    static boolean inRange(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }
        
    static int bfs(int si, int sj, int dist) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {si, sj, dist});
        v[si][sj] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int cdist = cur[2];
            
            if (cdist >= 2) continue;
            
            for (int d = 0; d < 4; d++) {
                int ni = ci + dis[d];
                int nj = cj + djs[d];
                
                if (inRange(ni, nj) && !v[ni][nj] && grid[ni][nj] != 'X') {
                    if (grid[ni][nj] == 'P') return -1;
                    q.offer(new int[] {ni, nj, cdist+1});
                    v[ni][nj] = true;
                }
            }
        }
        return 1;
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[N];
        Arrays.fill(answer, 1);
        
        for (int n = 0; n < N; n++) {
            
            grid = new char[N][N];
            for (int i = 0; i < N; i++) {
                grid[i] = places[n][i].toCharArray();
            }
            v = new boolean[N][N];
            boolean flag = false;
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!v[i][j] && grid[i][j] == 'P') {
                        if (bfs(i, j, 0) == -1) {
                            flag = true;
                            break;
                        }
                    }
               }
                if (flag) break;
           }
            if (flag) answer[n] = 0;
        }
        
        return answer;
    }
}