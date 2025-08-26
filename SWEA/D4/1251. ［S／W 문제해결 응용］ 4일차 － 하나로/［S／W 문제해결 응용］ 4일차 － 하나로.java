import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static long[] xPos;
    static long[] yPos;
    static Point[] Island;
    static double E;
    static int[] parent;

    static class Point {
        long i;
        long j;

        Point(long i, long j) {
            this.i = i;
            this.j = j;
        }
    }

    static class Edge {
        int from, to;
        double cost;

        Edge(int from, int to, double cost) {
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

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            xPos = new long[N];
            yPos = new long[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) xPos[i] = Long.parseLong(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) yPos[i] = Long.parseLong(st.nextToken());
            Island = new Point[N];
            for (int i = 0; i < N; i++) Island[i] = new Point(xPos[i], yPos[i]);
            E = Double.parseDouble(br.readLine());

            // 1. 모든 간선 정보 만들기
            List<Edge> edgeList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    long dx = Island[i].i - Island[j].i;
                    long dy = Island[i].j - Island[j].j;
                    double cost = E * (dx * dx + dy * dy);
                    edgeList.add(new Edge(i, j, cost));
                }
            }

            // 2. 간선 리스트 정렬
            Collections.sort(edgeList, (o1, o2)-> Double.compare(o1.cost, o2.cost));

            // 3. 최소 비용으로 간선 선택 (Union-Find)
            parent = new int[N];
            for (int i = 0; i < N; i++) parent[i] = i;

            double mst = 0;
            int edgeCnt = 0;

            for (Edge edge : edgeList) {
                if (union(edge.from, edge.to)) {
                    mst += edge.cost;
                    edgeCnt++;
                    if (edgeCnt == N-1) break;
                }
            }

            long ans = Math.round(mst);
            System.out.println("#" + tc + " " + ans);

        }
    }
}