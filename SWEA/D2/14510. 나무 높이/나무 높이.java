import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] trees = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            int maxHeight = 0;
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
                if (trees[i] > maxHeight) {
                    maxHeight = trees[i];
                }
            }

            int ones = 0;
            int twos = 0;

            for (int i = 0; i < N; i++) {
                int diff = maxHeight - trees[i];
                if (diff > 0) {
                    twos += diff / 2;
                    ones += diff % 2;
                }
            }

            int resultDays = 0;
            
            // 1. 1:1 매칭이 가능한 날짜 계산
            int minCount = Math.min(ones, twos);
            resultDays += minCount * 2;
            ones -= minCount;
            twos -= minCount;

            // 2. 남은 작업 처리
            if (ones > 0) {
                resultDays += ones * 2 - 1;
            } else if (twos > 0) {
                resultDays += (twos / 3) * 4;
                if (twos % 3 == 1) {
                    resultDays += 2; 
                } else if (twos % 3 == 2) {
                    resultDays += 3;
                }
            }
            
            System.out.println("#" + tc + " " + resultDays);
        }
    }
}