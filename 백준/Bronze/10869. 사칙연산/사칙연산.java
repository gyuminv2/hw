// 첫째 줄에 A+B, 둘째 줄에 A-B, 셋째 줄에 A*B, 넷째 줄에 A/B, 다섯째 줄에 A%B를 출력한다.

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine(), " ");
        
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        
        sb.append(A+B);
        System.out.println(sb);
        sb.setLength(0);
        
        sb.append(A-B);
        System.out.println(sb);
        sb.setLength(0);
        
        sb.append(A*B);
        System.out.println(sb);
        sb.setLength(0);
        
        sb.append(A/B);
        System.out.println(sb);
        sb.setLength(0);
        
        sb.append(A%B);
        System.out.println(sb);
        sb.setLength(0);
        
        br.close();
    }
}