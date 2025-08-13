import java.io.*;
import java.util.*;

public class Main {

    static int N, ans;
    static boolean[] col, slash, bSlash;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        col = new boolean[N+1];
        slash = new boolean[2*N+1]; // 합이 일정한 성질 (2 ~ 2N)
        bSlash = new boolean[2*N]; // 차가 일정한 성질 (-N-1 ~ N-1) : 음수 보정 +N

        ans = 0;
        setQueen(1);
        System.out.println(ans);
    }

    static void setQueen(int row) { // row : 퀸을 놓아야 하는 현재 행
        if (row > N) {
            ans++;
            return;
        }
        for (int c = 1; c <= N; c++) {
            if (col[c] || slash[row+c] || bSlash[row-c+N]) continue; // 가지치기
            // 퀸 배치 내용을 자료구조에 기록
            col[c] = slash[row+c] = bSlash[row-c+N] = true;
            // 다음 퀸 놓기
            setQueen(row+1);
            col[c] = slash[row+c] = bSlash[row-c+N] = false;
        }
    }
}