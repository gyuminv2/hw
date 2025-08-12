import java.io.*;
import java.util.*;

public class Main {

    static class Emo {
        int monitor;
        int clip;
        int time;

        Emo(int monitor, int clip, int time) {
            this.monitor = monitor;
            this.clip = clip;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int S = Integer.parseInt(br.readLine());
        Deque<Emo> q = new ArrayDeque<>();
        boolean[][] v = new boolean[20001][10001];
        q.offer(new Emo(1, 0, 0));
        v[1][0] = true;

        while (!q.isEmpty()) {
            Emo e = q.poll();
            if (e.monitor == S) {
                System.out.println(e.time);
                break;
            }

            int newClip = e.monitor;
            if (!v[e.monitor][newClip]) {
                v[e.monitor][newClip] = true;
                q.offer(new Emo(e.monitor, newClip, e.time + 1));
            }

            if (e.clip == 0) continue;
            int newMonitor = e.monitor + e.clip;
            if (!v[newMonitor][e.clip]) {
                v[newMonitor][e.clip] = true;
                q.offer(new Emo(newMonitor, e.clip, e.time + 1));
            }

            if (e.monitor == 0) continue;
            int newMonitor2 = e.monitor - 1;
            if (!v[newMonitor2][e.clip]) {
                v[newMonitor2][e.clip] = true;
                q.offer(new Emo(newMonitor2, e.clip, e.time + 1));
            }
        }
    }
}
