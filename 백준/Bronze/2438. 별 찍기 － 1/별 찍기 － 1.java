import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            for (int j = N-i-1; j < N; j++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }
}