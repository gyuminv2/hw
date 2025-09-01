import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static char[][] grid;
    static int[][] visited;
    static int si, sj, ei, ej;
    static int[] dis = {-1, 1, 0, 0};
    static int[] djs = {0, 0, -1, 1};

    static boolean inRange(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < M;
    }

	static void bfs() {
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {si, sj});
        visited[si][sj] = 0;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int ci = cur[0];
			int cj = cur[1];

			if (ci == ei && cj == ej) break;

            for (int d = 0; d < 4; d++) {
			    for (int mv = 1; mv <= K; mv++) {
					int ni = ci + mv * dis[d];
					int nj = cj + mv * djs[d];

					if (!inRange(ni, nj)) break;
                    if (grid[ni][nj] == '#') break;

                    if (visited[ni][nj] != 0 && visited[ni][nj] < visited[ci][cj] + 1) break;
                    else if (visited[ni][nj] == 0) {
                        q.offer(new int[]{ni, nj});
                        visited[ni][nj] = visited[ci][cj] + 1;
                    }
				}
			}
		}
	}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        grid = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                grid[i][j] = input.charAt(j);
            }
        }
        visited = new int[N][M];
        st = new StringTokenizer(br.readLine());
        si = Integer.parseInt(st.nextToken()) - 1;
        sj = Integer.parseInt(st.nextToken()) - 1;
        ei = Integer.parseInt(st.nextToken()) - 1;
        ej = Integer.parseInt(st.nextToken()) - 1;

		bfs();

        System.out.println(visited[ei][ej] == 0 ? -1 : visited[ei][ej]);
    }

}
