import java.io.*;
import java.util.*;

public class Solution {

    static int V, E;
    static List<Integer>[] graph;
    static int[] indegree;
    static boolean[] visited;

    static List<Integer> bfs() {
        List<Integer> lst = new LinkedList<>();
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= V; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                visited[i] = true;
            }
        }
        while (!q.isEmpty()) {
            int n = q.poll();
            lst.add(n);
            for (Integer g : graph[n]) {
                if (!visited[g]) {
                    indegree[g]--;
                    if (indegree[g] == 0) {
                        visited[g] = true;
                        q.offer(g);
                    }
                }
            }
        }
        return lst;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        int TC =  Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= 10; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            graph = new ArrayList[V+1];
            indegree = new int[V+1];
            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine().trim());
            for (int i = 1; i <= E; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                indegree[b]++;
            }
            visited = new boolean[V+1];

            List<Integer> rtn = bfs();

            System.out.print("#" + tc + " ");
            for(Integer i : rtn) {System.out.print(i + " ");}
            System.out.println();
        }
    }
}

