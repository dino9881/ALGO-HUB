import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {
    static int N, M;
    static int[][] board, dp;
    static PriorityQueue<Data> pq;
    final static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };

    /**
     * Data
     */
    public class Data implements Comparable<Data> {
        int row, col, val;

        Data(int r, int c, int v) {
            this.row = r;
            this.col = c;
            this.val = v;
        }

        public int compareTo(Data o) {

            if (row == o.row && val == o.val) {
                return Integer.compare(col, o.col);
            }
            if (val == o.val) {
                return Integer.compare(row, o.row);
            }
            return Integer.compare(o.val, val);
        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        dp = new int[N][M];
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                pq.add(new Data(i, j, board[i][j]));
            }
        }
        dp[0][0] = 1;

        while (!pq.isEmpty()) {
            Data data = pq.poll();
            int row = data.row;
            int col = data.col;
            int val = data.val;
            for (int d = 0; d < 4; d++) {
                int nr = row + dr[d];
                int nc = col + dc[d];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M)
                    continue;
                if (val < board[nr][nc])
                    dp[row][col] += dp[nr][nc];
            }
        }
        System.out.println(dp[N - 1][M - 1]);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();

    }
}