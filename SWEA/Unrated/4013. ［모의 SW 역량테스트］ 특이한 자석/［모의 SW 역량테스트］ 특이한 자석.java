import java.io.*;
import java.util.*;

public class Solution {

    static List<Integer>[] magnet;
    static int K;

    static void simul(int target, int dr) {
        int[] rotateMag = new int[4];
        rotateMag[target] = dr;

        for (int i = target; i > 0; i--) {
            if (magnet[i-1].get(2) == magnet[i].get(6)) break;
            rotateMag[i-1] = -rotateMag[i];
        }
        for (int i = target; i < 3; i++) {
            if (magnet[i].get(2) == magnet[i+1].get(6)) break;
            rotateMag[i+1] = -rotateMag[i];
        }
        for (int i = 0; i < 4; i++) {
            if (rotateMag[i] == 1) {
                magnet[i].add(0, magnet[i].remove(7));
            }
            if (rotateMag[i] == -1) {
                magnet[i].add(7, magnet[i].remove(0));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringTokenizer st;

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            magnet = new ArrayList[4];
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                magnet[i] = new ArrayList<>();
                for (int j = 0; j < 8; j++) {
                    magnet[i].add(Integer.parseInt(st.nextToken()));
                }
            }
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int target = Integer.parseInt(st.nextToken()) - 1;
                int dr = Integer.parseInt(st.nextToken());
                simul(target, dr);
            }

            System.out.println("#" + tc + " " + (magnet[0].get(0) + 2 * magnet[1].get(0) + 4 * magnet[2].get(0) + 8 * magnet[3].get(0)));
        }
    }
}