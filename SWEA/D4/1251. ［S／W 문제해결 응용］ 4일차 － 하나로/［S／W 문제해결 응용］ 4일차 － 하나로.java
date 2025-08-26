import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static Island[] islands;
    static double E;
    static boolean[] v;

    static class Island {
        long x, y;
        Island(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge {
        int to;
        double cost;

        Edge(int to, double cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            islands = new Island[N];

            StringTokenizer stX = new StringTokenizer(br.readLine());
            StringTokenizer stY = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                long x = Long.parseLong(stX.nextToken());
                long y = Long.parseLong(stY.nextToken());
                islands[i] = new Island(x, y);
            }
            E = Double.parseDouble(br.readLine());
            v = new boolean[N];
            PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) ->  Double.compare(o1.cost, o2.cost));
            double mst = 0;
            int cnt = 0;

            pq.offer(new Edge(0, 0.0));

            while (!pq.isEmpty()) {
                Edge cur = pq.poll();
                int to = cur.to;
                double cost = cur.cost;

                if (v[to]) continue;

                v[to] = true;
                mst += cost;
                cnt++;

                if (cnt == N) break;

                for (int i = 0; i < N; i++) {
                    if (!v[i]) {
                        long dx = islands[to].x - islands[i].x;
                        long dy = islands[to].y - islands[i].y;
                        double nCost = E * (dx*dx + dy*dy);
                        pq.offer(new Edge(i, nCost));
                    }
                }
            }

            long ans = Math.round(mst);
            System.out.println("#" + tc + " " + ans);
        }
    }
}