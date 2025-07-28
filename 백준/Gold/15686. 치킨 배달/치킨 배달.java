import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] city;
    static List<int[]> chicken = new ArrayList<>();
    static List<int[]> house = new ArrayList<>();
    static List<Integer> selected = new ArrayList<>();
    static int minDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        city = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if (city[i][j] == 1) {
                    house.add(new int[]{i, j});
                } else if (city[i][j] == 2) {
                    chicken.add(new int[]{i, j});
                }
            }
        }

        dfs(0, 0);
        System.out.println(minDistance);
    }

    static void dfs(int index, int count) {
        if (index > chicken.size()) return;

        if (count == M) {
            int totalDistance = 0;
            for (int[] h : house) {
                int distance = Integer.MAX_VALUE;
                for (int idx : selected) {
                    int[] ch = chicken.get(idx);
                    int dist = Math.abs(ch[0] - h[0]) + Math.abs(ch[1] - h[1]);
                    distance = Math.min(distance, dist);
                }
                totalDistance += distance;
            }
            minDistance = Math.min(minDistance, totalDistance);
            return;
        }

        if (index < chicken.size()) {
            selected.add(index);
            dfs(index + 1, count + 1);
            selected.remove(selected.size() - 1);

            dfs(index + 1, count);
        }
    }
}