import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {
    static int N, M;
    static int[] left, right;
    static int[][] arr;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        left = new int[M]; // 왼쪽으로 갔을때 최적값
        right = new int[M]; // 오른쪽으로 갔을때 최적값
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        right[0] = arr[0][0];
        for (int i = 1; i < M; i++) {
            right[i] = right[i - 1] + arr[0][i];
        }
        for (int i = 0; i < M; i++) {
            left[i] = Integer.MIN_VALUE;
        }
        // System.out.println(Arrays.toString(right));
        if (N == 1) {
            System.out.print(right[M - 1]);
            return;
        }
        for (int i = 1; i < N; i++) {
            int tmp[] = new int[M];
            for (int j = 0; j < M; j++) {
                tmp[j] = Math.max(left[j], right[j]) + arr[i][j];
            }
            // System.out.println("tmp = " + Arrays.toString(tmp));
            right = tmp.clone();
            // right[0] = tmp[0];
            for (int j = 1; j < M; j++) {
                right[j] = Math.max(right[j - 1] + arr[i][j], right[j]);
            }
            left = tmp.clone();
            // left[M - 1] = tmp[M - 1];
            for (int j = M - 2; j >= 0; j--) {
                left[j] = Math.max(left[j + 1] + arr[i][j], left[j]);
            }
            // System.out.println("right = " + Arrays.toString(right));
            // System.out.println("left = " + Arrays.toString(left));
            if (i == N - 1) {
                System.out.println(right[M - 1]);
                return;
            }
        }
        // System.out.println(Arrays.deepToString(left));
        // System.out.println(Arrays.deepToString(right));

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();

    }
}