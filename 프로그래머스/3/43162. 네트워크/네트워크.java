import java.util.*;

class Solution {
    
    public void dfs(int node, boolean[] v, int[][] computers) {
        v[node] = true;
        for (int i = 0; i < computers.length; i++) {
            if (computers[node][i] == 1 && !v[i]) dfs(i, v, computers);
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] v = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!v[i]) {
                dfs(i, v, computers);
                answer++;
            }
        }
        
        return answer;
    }
}