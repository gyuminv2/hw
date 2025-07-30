import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int[] arr = new int[9];
        int maxVal = -1;
        int idx = -1;
        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            maxVal = Math.max(maxVal, arr[i]);
            if (maxVal == arr[i]) idx = i;
        }
        sb.append(maxVal).append("\n").append(idx + 1);
        System.out.println(sb);
    }
}