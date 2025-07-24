import java.io.*;
import java.util.*;

class Solution {    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                int n = Integer.parseInt(st.nextToken());
                if (n%2 == 1) sum += n;
            }
            sb.append("#").append(tc).append(" ").append(sum).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
}