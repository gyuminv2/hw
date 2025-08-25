import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] grid;
    static ArrayList<Point> home;
    static ArrayList<Point> chicken;
    static ArrayList<Integer> selected;
    static int ans;

    static class Point {
        int i;
        int j;
        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static void backtrack(int start, int cnt) {
        if (cnt == M) {
            int sum = 0;
            for (Point p : home) {
                int min = Integer.MAX_VALUE;
                for (Integer i : selected) {
                    int ci = chicken.get(i).i;
                    int cj = chicken.get(i).j;
                    min = Math.min(min, Math.abs(p.i - ci) + Math.abs(p.j - cj));
                }
                sum += min;
            }
            ans = Math.min(ans, sum);
            return;
        }
        for (int i = start; i < chicken.size(); i++ ) {
            selected.add(i);
            backtrack(i + 1, cnt + 1);
            selected.remove(selected.size() - 1);
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][N];
        home = new ArrayList<>();
        chicken = new ArrayList<>();
        selected = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 1) {
                    home.add(new Point(i, j));
                }
                if (grid[i][j] == 2) {
                    chicken.add(new Point(i, j));
                }
            }
        }

        ans = Integer.MAX_VALUE;

        backtrack(0, 0);

        System.out.println(ans);
    }
}