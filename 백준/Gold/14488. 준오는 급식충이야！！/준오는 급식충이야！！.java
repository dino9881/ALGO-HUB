import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N;
    static double m, M, T;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer pos, speed;

        N = Integer.parseInt(st.nextToken());
        T = 10000 * Double.parseDouble(st.nextToken());
        // 자연수 이므로
        m = 0;
        M = Double.MAX_VALUE;
        pos = new StringTokenizer(br.readLine());
        speed = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            // 시간과 거리를 10000 배 해서 소수점을 없앤다.
            double curPos = Double.parseDouble(pos.nextToken()) * 10000;
            double curSpeed = Double.parseDouble(speed.nextToken());
            // 최소 위치는 0
            double min = Math.max(curPos - (curSpeed * T), 0);
            double max = curPos + (curSpeed * T);
            // 가능한 범위 바깥으로 갔다면
            if (max < m || min > M) {
                System.out.println(0);
                return;
            }
            m = Math.max(m, min);
            M = Math.min(M, max);
        }
        System.out.println(1);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}