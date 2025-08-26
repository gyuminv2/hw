import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[] job;
    static List<int[]> customers;
    static int[] home;
    static int[] a;
    static boolean[] v;
    static int min;

    static void perm(int cnt) {
        if (cnt == N) {
            int distance = Math.abs(home[1] - customers.get(a[N-1])[1]) + Math.abs(home[0] - customers.get(a[N-1])[0]);
            for (int i = N-1; i > 0; i--) {
                distance += Math.abs(customers.get(a[i])[1] - customers.get(a[i-1])[1]) + Math.abs(customers.get(a[i])[0] - customers.get(a[i-1])[0]);
            }
            distance += Math.abs(customers.get(a[0])[1] - job[1]) + Math.abs(customers.get(a[0])[0] - job[0]);
            min = Math.min(min, distance);
            return ;
        }
        for (int i = 0; i < N; i++) {
            if (!v[i]) {
                v[i] = true;
                a[cnt] = i;
                perm(cnt + 1);
                v[i] = false;
            }
        }
    }

    static void perm(int cnt, int distance) {
        if (min <= distance) return ;

        if (cnt == N) {
            distance += Math.abs(customers.get(a[N-1])[1] - home[1]) + Math.abs(customers.get(a[N-1])[0] - home[0]);
            min = Math.min(min, distance);
            return ;
        }
        for (int i = 0; i < N; i++) {
            if (!v[i]) {
                v[i] = true;
                a[cnt] = i;
                int nDistance;
                if (cnt == 0)
                    nDistance = Math.abs(job[1] - customers.get(a[0])[1]) + Math.abs(job[0] - customers.get(a[0])[0]);
                else {
                    int prev = a[cnt-1];
                    nDistance = Math.abs(customers.get(i)[1] - customers.get(prev)[1]) + Math.abs(customers.get(i)[0] - customers.get(prev)[0]);
                }
                perm(cnt + 1, distance + nDistance);
                v[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            customers = new ArrayList<>();
            for (int i = 0; i < N+2; i++) {
                if (i == 0) {
                    job = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
                }
                else if (i == 1) {
                    home = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
                }
                else {
                    customers.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
                }
            }

            a = new int[N];
            v = new boolean[N];
            min = Integer.MAX_VALUE;

//            perm(0);
            perm(0, 0);

            System.out.println("#" + tc + " " + min);

        }
    }
}