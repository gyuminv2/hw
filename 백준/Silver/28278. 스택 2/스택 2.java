import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        Deque<Integer> stack = new ArrayDeque<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            st = new StringTokenizer(line, " ");

            String cmd = st.nextToken();

            switch (cmd) {
                case "1": {
                    int x = Integer.parseInt(st.nextToken());
                    stack.push(x);  // 스택에 넣기
                    break;
                }
                case "2": {
                    sb.append(stack.isEmpty() ? -1 : stack.pop()).append('\n');
                    break;
                }
                case "3": {
                    sb.append(stack.size()).append('\n');
                    break;
                }
                case "4": {
                    sb.append(stack.isEmpty() ? 1 : 0).append('\n');
                    break;
                }
                case "5": {
                    sb.append(stack.isEmpty() ? -1 : stack.peek()).append('\n');  // 맨 위 보기
                    break;
                }
            }
        }

        System.out.print(sb);
    }
}
