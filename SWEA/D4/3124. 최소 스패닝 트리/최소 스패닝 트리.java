import java.io.*;
import java.util.*;

public class Solution {

    static int V, E;
    static ArrayList<Edge> edgeList;
    static int[] parent;

    static class Edge {
        int from, to;
        long cost;

        Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return false;
        parent[rootB] = rootA;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            edgeList = new ArrayList<>();
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());
                edgeList.add(new Edge(a, b, c));
            }
            parent = new int[V+1];
            for (int i = 1; i < V+1; i++) parent[i] = i;

            edgeList.sort((o1, o2) -> Long.compare(o1.cost, o2.cost));

            long mst = 0, cnt = 0;
            for (Edge e : edgeList) {
                if (union(e.from, e.to)) {
                    mst += e.cost;
                    if (++cnt == V-1) break;
                }
            }

            System.out.println("#" + tc + " " + mst);
        }
    }
}
