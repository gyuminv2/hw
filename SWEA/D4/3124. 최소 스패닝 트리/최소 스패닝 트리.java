import java.io.*;
import java.util.*;

public class Solution {

    static int V, E;
    static ArrayList<Edge>[] edgeList;
    static boolean[] v;

    static class Edge {
        int to, cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringTokenizer st;

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());

            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            edgeList = new ArrayList[V+1];
            for (int i = 1; i <= V; i++) edgeList[i] = new ArrayList<>();
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                edgeList[a].add(new Edge(b, c));
                edgeList[b].add(new Edge(a, c));
            }
            v = new boolean[V+1];
            PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
            long mst = 0;
            int cnt = 0;

            pq.offer(new Edge(1, 0));

            while (!pq.isEmpty()) {
                Edge cur = pq.poll();
                int to = cur.to;
                int cost = cur.cost;

                if (v[to]) continue;

                v[to] = true;
                mst += cost;
                if (++cnt == V) break;

                for (Edge e : edgeList[to]) {
                    if (!v[e.to]) {
                        pq.offer(e);
                    }
                }
            }
            System.out.println("#" + tc + " " + mst);
        }
    }
}