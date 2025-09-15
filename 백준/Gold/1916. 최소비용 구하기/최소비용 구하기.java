import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static ArrayList<Edge>[] busList;
	static int s, e;
	static boolean[] v;
	static int[] distance;
	
	static class Edge {
		int to, cost;
		
		Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	
	static int dijkstra(int start, int end) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->Integer.compare(o1[1], o2[1]));
		distance = new int[N+1];
		v = new boolean[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		pq.offer(new int[] {start, 0});
		
		int total = 0;
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			if (node == end) {
				return distance[node];
			}
			
			for (Edge e : busList[node]) {
				int next = e.to;
				int cost = e.cost;
				
				if (distance[next] > distance[node] + cost) {
					distance[next] = distance[node] + cost;
					pq.offer(new int[] {next, distance[next]});
				}
			}
		}
		return total;
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		busList = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) busList[i] = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			busList[from].add(new Edge(to, cost));
		}
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		System.out.println(dijkstra(s, e));
	}

}
