import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        String[] words = new String[5];
        int maxLen = 0;

        for (int i = 0; i < 5; i++) {
            words[i] = br.readLine();
            maxLen = Math.max(maxLen, words[i].length());
        }

        for (int j = 0; j < maxLen; j++) {
            for (int i = 0; i < 5; i++) {
                if (j < words[i].length()) {
                    sb.append(words[i].charAt(j));
                }
            }
        }

        System.out.println(sb);
    }
}