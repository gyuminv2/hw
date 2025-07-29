import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        // 1부터 N까지 줄 반복
        for (int i = 1; i <= N; i++) {
            // i개의 별을 출력
            for (int j = 1; j <= N - i; j++) {
                sb.append(' ');
            }
            for (int j = 1; j <= i; j++) {
                sb.append('*');
            }
            sb.append('\n');  // 줄바꿈
        }
        System.out.print(sb);
    }
}