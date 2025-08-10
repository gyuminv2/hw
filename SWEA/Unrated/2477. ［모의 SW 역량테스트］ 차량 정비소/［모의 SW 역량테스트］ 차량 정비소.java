import java.io.*;
import java.util.*;

public class Solution {

    static int N, M, K, A, B;
    static int[] a, b;
    static List<Customer> everybody;
    static List<Customer> w_q1;
    static List<Customer> w_q2;
    static Customer[] desk1;
    static Customer[] desk2;

    static class Customer implements Comparable<Customer> {
        int num;
        int startTime;
        int desk1No;
        int desk1EndTime;
        int desk2No;

        public Customer(int num, int startTime) {
            this.num = num;
            this.startTime = startTime;
        }

        @Override
        public int compareTo(Customer o) {
            return this.num - o.num;
        }
    }

    static void loop() {
        int t = 0;
        int finished = 0;
        int c_idx = 0;

        while (finished < K) {
            // 1. 정비 완료 처리
            for (int j = 1; j <= M; j++) {
                if (desk2[j] != null) {
                    if (desk2[j].desk1EndTime + b[j] <= t) {
                        desk2[j] = null;
                        finished++;
                    }
                }
            }

            // 2. 접수 완료 처리 -> 정비 대기열로 이동
            for (int i = 1; i <= N; i++) {
                if (desk1[i] != null) {
                    if (desk1[i].desk1EndTime <= t) {
                        w_q2.add(desk1[i]);
                        desk1[i] = null;
                    }
                }
            }

            // 3. 신규 고객 도착 -> 접수 대기열로 이동
            while (c_idx < K && everybody.get(c_idx).startTime <= t) {
                w_q1.add(everybody.get(c_idx));
                c_idx++;
            }

            // 4. 접수 창구 배정
            if (!w_q1.isEmpty()) {
                Collections.sort(w_q1); // 우선순위 1번 : 고객 번호 순
                for (int i = 1; i <= N; i++) {
                    if (desk1[i] == null && !w_q1.isEmpty()) {
                        Customer customer = w_q1.remove(0);
                        customer.desk1No = i;
                        customer.desk1EndTime = t + a[i];
                        desk1[i] = customer;
                    }
                }

            }

            // 5. 정비 창구 배정
            if (!w_q2.isEmpty()) {
                Collections.sort(w_q2, (c1, c2) -> {
                    if (c1.desk1EndTime == c2.desk1EndTime) {
                        return c1.desk1No - c2.desk1No;
                    }
                    return c1.desk1EndTime - c2.desk1EndTime;
                });
                for (int j = 1; j <= M; j++) {
                    if (desk2[j] == null && !w_q2.isEmpty()) {
                        Customer customer = w_q2.remove(0);
                        customer.desk2No = j;
                        customer.desk1EndTime = t; // 정비 시작 시간으로 재활용
                        desk2[j] = customer;
                    }
                }
            }
            t++;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            a = new int[N+1];
            b = new int[M+1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) a[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) b[i] = Integer.parseInt(st.nextToken());

            everybody = new ArrayList<>();
            w_q1 = new ArrayList<>();
            w_q2 = new ArrayList<>();
            desk1 = new Customer[N+1];
            desk2 = new Customer[M+1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) everybody.add(new Customer(i, Integer.parseInt(st.nextToken())));

            loop();

            int rtn = 0;
            for (Customer c : everybody) {
                if (c.desk1No == A && c.desk2No == B) {
                    rtn += c.num;
                }
            }

            System.out.println("#" + tc + " " + (rtn == 0 ? -1 : rtn));
        }
    }
}
