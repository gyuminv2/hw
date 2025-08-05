import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 1; test_case <= 10; test_case++) {
            int N = Integer.parseInt(br.readLine());

            boolean isValid = true;

            for (int i = 0; i < N; i++) {
                String[] parts = br.readLine().split(" ");

                String token = parts[1];
                int childCount = parts.length - 2;

                if (token.matches("[+\\-*/]")) {
                    // 연산자는 반드시 자식 2개
                    if (childCount != 2) isValid = false;
                } else {
                    // 숫자는 자식이 없어야 함
                    if (childCount != 0) isValid = false;
                }
            }

            System.out.println("#" + test_case + " " + (isValid ? 1 : 0));
        }
    }
}
