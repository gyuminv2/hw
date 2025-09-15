import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] arr;
	
	static void reverse(int s, int e, int sz) {
		int mid = sz - 1;
		for (int i = s; i < mid; i++) {
			int tmp = arr[i];
			arr[i] = arr[arr.length-1 - i];
			arr[sz-1 - i] = tmp;
		}
		System.out.println(Arrays.toString(arr));
	}
	
	static void adapt(int[] sub, int s, int e) {
		for (int i = s; i < e; i++) {
			arr[i] = sub[i];
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		
		for (int i = 1; i <= N; i++) arr[i] = i;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int mid = (end - start) / 2 + start;
			int sz = (end - start) + 1;
			for (int j = start; j < mid+1; j++) {
				int tmp = arr[j];
				arr[j] = arr[end - j + start];
				arr[end - j + start] = tmp;
			}
		}
		for (int i = 1; i <= N; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
