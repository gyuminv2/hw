import java.io.*;
import java.util.*;

public class Solution {

    static int N, M;
    static List<Integer>[] small;
    static List<Integer>[] big;
    static int rtn;

    static int dfs(int v, List<Integer>[] graph, boolean[] visited) {
        visited[v] = true;

        int cnt = 1;
        for (int n : graph[v]) {
            if (!visited[n]) {
                cnt += dfs(n, graph, visited);
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC =  Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine().trim());
            M = Integer.parseInt(st.nextToken());
            small = new List[N+1];
            big = new List[N+1];
            for (int i = 1; i <= N; i++) {
                small[i] = new LinkedList<>();
                big[i] = new LinkedList<>();
            }
            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine().trim());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                small[b].add(a);
                big[a].add(b);
            }
            rtn = 0;

            for (int v = 1; v <= N; v++) {
                int shCnt = dfs(v, small, new boolean[N+1]);
                int bgCnt = dfs(v, big, new boolean[N+1]);
                if (shCnt + bgCnt - 1 == N) rtn++;
            }

            System.out.println("#" + tc + " " + rtn);
        }
    }
}

