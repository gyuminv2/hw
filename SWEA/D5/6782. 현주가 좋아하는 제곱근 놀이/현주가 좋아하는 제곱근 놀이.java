import java.io.*;
import java.util.*;

public class Solution {

    static long N;
    static Map<Long, Long> memory;

    static long dfs(long num) {
        if (num == 2) {
            return 0;
        }

        if (memory.containsKey(num)) return memory.get(num);

        long result1 = Long.MAX_VALUE;
        long result2 = Long.MAX_VALUE;

        if (num > 3) {
            long sqrtN = (long) Math.sqrt(num);
            if (sqrtN * sqrtN == num) {
                result1 = 1 + dfs(sqrtN);
            }
        }

        long k = (long) Math.sqrt(num);
        long next = (k+1) * (k+1);
        result2 = (next - num) + 1 + dfs(k+1);

        long min = Math.min(result1, result2);
        memory.put(num, min);

        return min;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
//        int T = Integer.parseInt(br.readLine().trim());
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
//            N = Integer.parseInt(br.readLine());
            N = sc.nextLong();
            memory = new HashMap<>();

            System.out.println("#" + tc + " " + dfs(N));
        }
    }
}