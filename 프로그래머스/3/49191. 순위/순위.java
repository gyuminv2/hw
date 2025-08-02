import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        
        boolean[][] win = new boolean[n + 1][n + 1];

        // 1. 초기 정보 채우기
        for (int[] result : results) {
            win[result[0]][result[1]] = true;
        }

        // 2. 플로이드 워셜 전파
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (win[i][k] && win[k][j]) {
                        win[i][j] = true;
                    }
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print((win[i][j] ? "1" : "0") + " ");
            }
            System.out.println();
        }

        // 3. 순위 확정 가능한 사람 수 계산
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int winCount = 0, loseCount = 0;
            for (int j = 1; j <= n; j++) {
                if (win[i][j]) winCount++;
                if (win[j][i]) loseCount++;
            }
            if (winCount + loseCount == n - 1) count++;
        }

        return count;
    }
}