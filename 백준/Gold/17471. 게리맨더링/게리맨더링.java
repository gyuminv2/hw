import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] numbers;
    static List<Integer>[] graph;
    static boolean[] v;
    static int minDiff;

    static boolean bfs(List<Integer> group) {
        int cnt = 1;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(group.get(0));
        boolean[] visited = new boolean[N+1];
        visited[group.get(0)] = true;
        while (!q.isEmpty()) {
            int c = q.poll();
            for (Integer g : graph[c]) {
                if (!visited[g] && group.contains(g)) {
                    visited[g] = true;
                    cnt++;
                    q.offer(g);
                }
            }
        }
        return cnt == group.size();
    }

    static void comb(int cnt) {
        if (cnt == N+1) {
            List<Integer> groupA = new ArrayList<>();
            List<Integer> groupB = new ArrayList<>();

            for (int i = 1; i <= N; i++) {
                if (v[i]) groupA.add(i);
                else groupB.add(i);
            }

            if (groupA.isEmpty() || groupB.isEmpty()) return;

            if (bfs(groupA) && bfs(groupB)) {
                int sumA = 0, sumB = 0;
                for (int i = 1; i <= N; i++) {
                    if (groupA.contains(i)) sumA += numbers[i];
                    else sumB += numbers[i];
                }
                minDiff = Math.min(minDiff, Math.abs(sumA - sumB));
            }
            return;
        }
        v[cnt] = true;
        comb(cnt + 1);
        v[cnt] = false;
        comb(cnt + 1);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        graph = new List[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            for (int j = 0; j < len; j++)
                graph[i].add(Integer.parseInt(st.nextToken()));
        }
        v = new boolean[N+1];
        minDiff = Integer.MAX_VALUE;

        comb(1);

        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);

//        for(List<Integer> list : graph){
//            System.out.println(list);
//        }
    }
}
