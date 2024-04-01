import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {
    static int N, min, max;
    static int[] board;
    static int[][] small, large;
    final static int[] dc = { 1, 0, -1 };

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[3];
        small = new int[2][3];
        large = new int[2][3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 3; i++) {
            small[0][i] = 10000000;
            small[1][i] = board[i];
            large[1][i] = board[i];
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[j] = Integer.parseInt(st.nextToken());
            }
            small[0] = small[1];
            large[0] = large[1];
            small[1] = new int[3];
            large[1] = new int[3];
            for (int j = 0; j < 3; j++) {
                small[1][j] = 10000000;
            }
            for (int j = 0; j < 3; j++) {
                for (int d = 0; d < 3; d++) {
                    int idx = j + dc[d];
                    if (idx < 0 || idx >= 3)
                        continue;
                    small[1][j] = Math.min(small[1][j], small[0][idx] + board[j]);
                    large[1][j] = Math.max(large[1][j], large[0][idx] + board[j]);
                }
            }
        }
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            max = Math.max(max, large[1][i]);
            min = Math.min(min, small[1][i]);
        }
        System.out.println(max + " " + min);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();

    }
}