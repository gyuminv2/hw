import java.io.*;
import java.util.*;

public class Solution {

    static int game_cnt = 9;
    static int[] gyu;
    static int[] in;
    static int[] result;
    static boolean[] used;
    static boolean[] isSelected;
    static int win;
    static int loss;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc =  Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            gyu = new int[game_cnt];
            in = new int[game_cnt];
            used = new boolean[19];
            isSelected = new boolean[game_cnt];
            result = new int[game_cnt];
            win = 0;
            loss = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < game_cnt; i++) {
                gyu[i] = Integer.parseInt(st.nextToken());
                used[gyu[i]] = true;
            }

            int j = 0;
            for (int i = 1; i <= 18; i++) {
                if (used[i]) continue;
                in[j++] = i;
            }

            perm(0);
            System.out.println("#" + t + " " + win + " " + loss);
        }
    }

    public static void perm(int cnt){
        if(cnt == game_cnt){
            int sumG = 0, sumI = 0;
            for (int i = 0; i < game_cnt; i++) {
                if (gyu[i] > result[i]) sumG += gyu[i] + result[i];
                if (gyu[i] < result[i]) sumI += gyu[i] + result[i];
            }
            if (sumG > sumI) win++;
            else loss++;
        }
        for (int i = 0; i < game_cnt; i++) {
            if (isSelected[i]) continue;
            isSelected[i] = true;
            result[cnt] = in[i];
            perm(cnt + 1);
            isSelected[i] = false;
        }
    }
}
