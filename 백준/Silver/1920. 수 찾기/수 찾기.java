import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
//        List<Integer> A = new ArrayList<>();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
//            A.add(Integer.parseInt(st.nextToken()));
            A[i] = Integer.parseInt(st.nextToken());
        }
//        Collections.sort(A);
        Arrays.sort(A);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < M; i++) {
            boolean flag = true;
            if (Arrays.binarySearch(A, list.get(i)) < 0) {
                flag = false;
            }
            System.out.println(flag == true ? 1 : 0);
        }
    }
}
