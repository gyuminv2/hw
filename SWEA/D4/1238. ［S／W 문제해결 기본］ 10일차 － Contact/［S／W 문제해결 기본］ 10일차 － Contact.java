import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int start;
    static List<Integer>[] list;
    static boolean[] v;

    static int bfs(int start) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        v[start] = true;

        int size = 0;
        int last = 0;
        while (!q.isEmpty()) {
            size = q.size();
            last = 0;
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                last = Math.max(last, cur);
                for (Integer n : list[cur]) {
                    if (!v[n]) {
                        v[n] = true;
                        q.offer(n);
                    }
                }
            }
        }
        return last;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        int TC =  Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= 10; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            list = new ArrayList[101];
            for (int i = 1; i <= 100; i++) {
                list[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i+=2) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                list[from].add(to);
            }
            v = new boolean[101];

            System.out.println("#" + tc + " " + bfs(start));
        }
    }
}

