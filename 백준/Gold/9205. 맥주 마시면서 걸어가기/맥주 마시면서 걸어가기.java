import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<Point> home;
    static ArrayList<Point> cu;
    static ArrayList<Point> rock;
    static boolean[] v;

    static class Point {
        int i, j;

        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static boolean bfs() {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {home.get(0).i, home.get(0).j});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            if ((Math.abs(ci - rock.get(0).i) + Math.abs(cj - rock.get(0).j)) <= 1000) return true;

            for (int f = 0; f < N; f++) {
                if (!v[f]) {
                    int ni = cu.get(f).i;
                    int nj = cu.get(f).j;
                    if ((Math.abs(ni - ci) + Math.abs(nj - cj)) <= 1000) {
                        q.offer(new int[]{ni, nj});
                        v[f] = true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            home = new ArrayList<>();
            cu = new ArrayList<>();
            rock = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            home.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                cu.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }
            st = new StringTokenizer(br.readLine());
            rock.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            v = new boolean[N];

            String ans = "";
            if (bfs()) ans = "happy";
            else ans = "sad";

            System.out.println(ans);
        }
    }
}