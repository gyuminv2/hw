import java.io.*;
import java.util.*;

public class Solution {

    static int N, M, K;
    static ArrayList<Instance> lst;
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};

    static class Instance {
        int ci, cj, cnt, cdr;

        public Instance(int ci, int cj, int cnt, int cdr) {
            this.ci = ci;
            this.cj = cj;
            this.cnt = cnt;
            this.cdr = cdr;
        }

        @Override
        public String toString() {
            return "Instance{" +
                    "ci=" + ci +
                    ", cj=" + cj +
                    ", cnt=" + cnt +
                    ", cdr=" + cdr +
                    '}';
        }
    }

    static void removeDup() {
        for (int i = 0; i < lst.size()-1; i++) {
            Instance i1 = lst.get(i);
            for (int j = i + 1; j < lst.size(); j++) {
                Instance i2 = lst.get(j);
                if (i1.ci == i2.ci && i1.cj == i2.cj) {
                    i1.cnt += i2.cnt;
                    lst.remove(j);
                    j--;
                }
            }
        }
    }

    static int selectDr(int d) {
        if (d == 0) d = 1;
        else if (d == 1) d = 0;
        else if (d == 2) d = 3;
        else if (d == 3) d = 2;
        return d;
    }

    static boolean inEdge(int i, int j) {
        return i == 0 || i == N - 1 || j == 0 || j == N - 1;
    }

    static void move() {
        for (int x = 0; x < lst.size(); x++) {
            Instance instance = lst.get(x);
            instance.ci = instance.ci + dis[instance.cdr];
            instance.cj = instance.cj + djs[instance.cdr];

            if (inEdge(instance.ci, instance.cj)) {
                instance.cdr = selectDr(instance.cdr);
                instance.cnt = instance.cnt / 2;
                if (instance.cnt == 0) {
                    lst.remove(x);
                    x--;
                }
            }
        }

        Collections.sort(lst, ((o1, o2) -> o2.cnt - o1.cnt));

        removeDup();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            lst = new ArrayList<>();

            for (int k = 1; k <= K; k++) {
                st = new StringTokenizer(br.readLine());
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dr = Integer.parseInt(st.nextToken()) - 1;
                lst.add(new Instance(i, j, cnt, dr));
            }

            for (int i = 0; i < M; i++) move();

            int ans = 0;
            for (int i = 0; i < lst.size(); i++) ans += lst.get(i).cnt;

            System.out.println("#" + tc + " " + ans);
        }
    }
}