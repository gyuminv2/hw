import java.io.*;
import java.util.*;

/**
 * [목표]
 * 지갑 주인 찾기
 * 
 * [고객 설문지 info]
 * 접수 창구번호, 정비 창구번호
 * 
 * [총 2단계]
 * - 1단계 : 접수 창구에서 고장 접수
 * - 2단계 : 정비 창구에서 차량 정바
 * 차량 정비가 끝난 고객은 고객만족도 설문지 작성
 *
 * 접수 창구(i)에서 처리 시간 ai ( 1 <= i <= N )
 * 접수 창구(j)에서 처리 시간 bj ( 1 <= j <= M )
 *
 * 고객 수 : K ( 1-base )
 * 고객 번호 k의 정비소 도착 시간 tk ( 1 <= k <= K )
 *
 * [시뮬레이션]
 * 접수 ( 빈 접수 창구 ) => 정비 ( 빈 정비 창구 )
 * 접수 우선순위 : 1. 고객 번호( k ) 낮은 순, 2. 접수 창구 번호 작은 순
 * 정비 우선순위 : 1. 먼저 온 순, 2. 접수 창구 번호 작은 순, 3. 정비 창구 번호 작은 순
 *
 * [input]
 * 각 테스트 케이스의 첫 번째 줄에는 접수 창구의 개수 N,
 * 정비 창구의 개수 M, 차량 정비소를 방문한 고객의 수 K,
 * 지갑을 두고 간 고객이 이용한 접수 창구번호 A와 정비 창구번호 B가 주어진다.
 *
 * 두 번째 줄에는 i번째 접수 창구가 고장을 접수하는 데 걸리는 시간 ai가 N개 주어진다.
 * 세 번째 줄에는 j번째 정비 창구가 차량을 정비하는 데 걸리는 시간 bj가 M개 주어진다.
 * 네 번째 줄에는 k번째 고객이 차량 정비소를 방문하는 시간 tk가 순서대로 K개 주어진다.
 *
 * [output]
 * 지갑을 분실한 고객과 같은 (접수 창구, 정비 창구)이용 고객의 k의 합
 * else -1
 *
 *
 * [ 단순 설계 ]
 * K번째 사람까지 완료될때까지 반복
 * 일단 무조건 입장 대기열 입장
 * 1. 대기열 들어온 순서대로 접수 테이블 비어있는지 확인
 * (주의) 나갈 사람먼저 체크해야함
 * (나갈 사람 내보내기 )
 * 2. 접수 완료 후 정비 대기열 입장
 * 3. 정비 완료 후 끝난 사람 수 1증가
 * (들어오는 사람 들여보내기 )
 * 4. 접수 창구 배정
 * 5. 정비 창구 배정
 */

public class Solution {

    static int N, M, K, A, B;
    static int[] a, b;
    static List<Customer> everyone;
    static PriorityQueue<Customer> waitJupsuQ;
    static PriorityQueue<Customer> waitJungbiQ;
    static Customer[] jupsu;
    static Customer[] jungbi;

    static class Customer {
        int id;
        int startTime;
        int jupsuEndTime;
        int jupsuNo;
        int jungbiNo;

        Customer(int id, int startTime) {
            this.id = id;
            this.startTime = startTime;
        }
    }

    static int answer() {
        int ret = 0;
        for (Customer c : everyone) {
            if (c.jupsuNo == A && c.jungbiNo == B) {
                ret += c.id;
            }
        }
        return (ret == 0 ? -1 : ret);
    }

    static void simul() {
        int time = 0;
        int finished = 0;
        int cIdx = 0;

        // 고객 전부 끝날 때 까지
        while (finished < K) {
            // 1. 고객이 접수 대기열로 들어온다.
            while (cIdx < K && everyone.get(cIdx).startTime == time) {
                waitJupsuQ.add(everyone.get(cIdx));
                cIdx++;
            }

            // 2. 접수 끝났으면 정비 대기열로 들어간다.
            for (int i = 1; i <= N; i++) {
                if (jupsu[i] != null) {
                    if (jupsu[i].jupsuEndTime == time) {
                        waitJungbiQ.add(jupsu[i]);
                        jupsu[i] = null;
                    }
                }
            }
            
            // 3. 정비 끝났으면 나감
            for (int j = 1; j <= M; j++) {
                if (jungbi[j] != null) {
                    if (jungbi[j].jupsuEndTime + b[j] == time) {
                        jungbi[j] = null;
                        finished++;
                    }
                }
            }
            
            // 4. 접수 대기열 -> 접수 창구 입장
            for (int i = 1; i <= N; i++) {
                if (jupsu[i] == null && !waitJupsuQ.isEmpty()) {
                    Customer customer = waitJupsuQ.poll();
                    customer.jupsuNo = i;
                    customer.jupsuEndTime = a[i] + time;
                    jupsu[i] = customer;
                }
            }

            // 5. 정비 대기열 -> 정비 창구 입장
            for (int j = 1; j <= M; j++) {
                if (jungbi[j] == null && !waitJungbiQ.isEmpty()) {
                    Customer customer = waitJungbiQ.poll();
                    customer.jungbiNo = j;
                    customer.jupsuEndTime = time; // 정비 시작 시간으로 재활용
                    jungbi[j] = customer;
                }
            }
            time++;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
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
            everyone = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) everyone.add(new Customer(i, Integer.parseInt(st.nextToken())));
            waitJupsuQ = new PriorityQueue<>((o1, o2)-> Integer.compare(o1.id, o2.id));
            waitJungbiQ = new PriorityQueue<>((o1, o2)-> {
                if (o1.jupsuEndTime == o2.jupsuEndTime) {
                    return o1.jupsuNo - o2.jupsuNo;
                }
                return o1.jupsuEndTime - o2.jupsuEndTime;
            });
            jupsu = new Customer[N+1];
            jungbi = new Customer[M+1];

            simul();

            System.out.println("#" + tc + " " + answer());
        }
    }
}
