import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            char[] result = new char[10000];
            int[] from =  new int[10000];

            Deque<Integer> q = new  ArrayDeque<>();
            boolean[] v = new boolean[10000];
            q.offer(A);
            v[A] = true;

            while (!q.isEmpty()) {
                int cur = q.poll();

                if (cur == B) break;

                int nextD = (cur * 2) % 10000;
                if (!v[nextD]) {
                    v[nextD] = true;
                    result[nextD] = 'D';
                    from[nextD] = cur;
                    q.offer(nextD);
                }

                int nextS = (cur == 0) ? 9999 : cur - 1;
                if (!v[nextS]) {
                    v[nextS] = true;
                    result[nextS] = 'S';
                    from[nextS] = cur;
                    q.offer(nextS);
                }

                int nextL = (cur % 1000) * 10 + (cur / 1000);
                if (!v[nextL]) {
                    v[nextL] = true;
                    result[nextL] = 'L';
                    from[nextL] = cur;
                    q.offer(nextL);
                }

                int nextR = (cur % 10) * 1000 + (cur / 10);
                if (!v[nextR]) {
                    v[nextR] = true;
                    result[nextR] = 'R';
                    from[nextR] = cur;
                    q.offer(nextR);
                }
            }

            StringBuilder sb = new StringBuilder();
            int cur = B;
            while (cur != A) {
                sb.append(result[cur]);
                cur = from[cur];
            }
            System.out.println(sb.reverse());
        }
    }
}
