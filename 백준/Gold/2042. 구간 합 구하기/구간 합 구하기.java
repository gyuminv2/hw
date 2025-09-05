import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static long[] a;
    static long[] tree;

    // i번째 수까지의 누적 합을 계산하는 함수
    static long sum(int i) {
        long ans = 0;
        while (i > 0) {
            ans += tree[i];
            i -= (i & -i); // 마지막 1비트만큼 빼면서 다음 구간으로 이동
        }
        return ans;
    }

    // i번째 수를 diff만큼 변화시켰을 때, 관련 구간들을 업데이트하는 함수
    static void update(int i, long diff) {
        while (i <= n) {
            tree[i] += diff;
            i += (i & -i); // 마지막 1비트만큼 더하면서 상위 구간으로 이동
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        a = new long[n + 1];
        tree = new long[n + 1];

        // 초기 배열 값으로 펜윅 트리 구성
        for (int i = 1; i <= n; i++) {
            a[i] = Long.parseLong(br.readLine());
            update(i, a[i]);
        }

        StringBuilder sb = new StringBuilder();
        int commands = m + k;

        while (commands-- > 0) {
            st = new StringTokenizer(br.readLine());
            int t1 = Integer.parseInt(st.nextToken());

            if (t1 == 1) {
                // 값 변경
                int t2 = Integer.parseInt(st.nextToken());
                long t3 = Long.parseLong(st.nextToken());
                long diff = t3 - a[t2]; // 변화량 계산
                a[t2] = t3; // 원래 배열 값 변경
                update(t2, diff); // 펜윅 트리 업데이트
            } else {
                // 구간 합 출력
                int t2 = Integer.parseInt(st.nextToken());
                int t3 = Integer.parseInt(st.nextToken());
                long result = sum(t3) - sum(t2 - 1);
                sb.append(result).append("\n");
            }
        }
        System.out.print(sb);
    }
}