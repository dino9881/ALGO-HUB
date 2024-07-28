import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    int N, M, H, ans;

    int[][] lad;

    public void bt(int count) {
        // 갈 수 있는지 체크
        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = i;
        }
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                // 사다리 있으면
                if (lad[i][j] == 1) {
                    // System.out.println(i + ", " + j);
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        // System.out.println(Arrays.toString(arr));
        boolean flag = true;
        for (int i = 1; i <= N; i++) {
            if (arr[i] != i) {
                flag = false;
                // System.out.println("!");
                break;
            }
        }
        if (flag) {
            ans = Math.min(count, ans);
            // System.out.println("?!");
            return;
        }
        // 안맞으면 사다리를 놓아본다.
        if (count < ans - 1) {
            for (int i = 1; i <= H; i++) {
                for (int j = 1; j < N; j++) {
                    // System.out.println("!@");
                    if (lad[i][j] == 0) {
                        int t0 = lad[i][j - 1];
                        int t1 = lad[i][j];
                        int t2 = lad[i][j + 1];
                        // 되돌아 오는거
                        int t3 = -1;
                        if (i < H)
                            t3 = lad[i + 1][j];
                        if (i < H && lad[i + 1][j] == 0)
                            lad[i + 1][j] = -1;
                        // 놓기
                        lad[i][j] = 1;
                        lad[i][j - 1] = -1;
                        lad[i][j + 1] = -1;
                        bt(count + 1);
                        lad[i][j] = t1;
                        lad[i][j - 1] = t0;
                        lad[i][j + 1] = t2;
                        if (i < H)
                            lad[i + 1][j] = t3;
                    }
                }
            }
        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        lad = new int[H + 1][N + 2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 높이 위치
            int b = Integer.parseInt(st.nextToken()); // 이어진 애들
            // 사다리 놓은 곳
            lad[a][b] = 1;
            // 사다리 이제 놓을 수 없는 곳
            lad[a][b - 1] = -1;
            lad[a][b + 1] = -1;
        }
        // System.out.println(Arrays.deepToString(lad));
        ans = 4;
        bt(0);
        System.out.println(ans == 4 ? -1 : ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}