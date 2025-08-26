import java.io.*;
import java.util.*;

public class Solution {

    static int V, E;
    static ArrayList<Edge> edgeLists;

    static class Edge {
        int from, to, cost;

        Edge(int from, int to, int cost) { this.from = from; this.to = to; this.cost = cost; }
    }

    static int find(int[] p, int x) {
        if (p[x] == x) return x;
        return p[x] = find(p, p[x]);
    }

    static boolean union(int[] p, int a, int b) {
        int rootA = find(p, a);
        int rootB = find(p, b);

        if (rootA == rootB) return false;
        p[rootB] = rootA;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            edgeLists = new ArrayList<>();

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                edgeLists.add(new Edge(a, b, c));
            }
            // 1. 모든 간선 정보 만들기
            int[] parent = new int[V+1];
            for (int i = 1; i <= V; i++) {parent[i] = i;}
            long mst = 0;
            int cnt = 0;

            // 2. 간선 리스트 정렬
            Collections.sort(edgeLists, (o1, o2)->Integer.compare(o1.cost, o2.cost));

            // 3. 최소 비용으로 간선 선택 (Union-Find)
            for (Edge e : edgeLists) {
                if (union(parent, e.from, e.to)) {
                    mst += e.cost;
                    cnt++;
                    if (cnt == V-1) break;
                }
            }

            System.out.println("#" + tc + " " + mst);
        }
    }
}