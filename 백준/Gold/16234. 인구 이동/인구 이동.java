import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N, L, R, ans;
    static int[][] board;
    static boolean[][] visit;
    static Queue<Queue<Data>> q;
    final static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };

    /**
     * Data
     */
    public class Data {

        int row, col;

        Data(int r, int c) {
            this.row = r;
            this.col = c;
        }
    }

    public void bfs(int r, int c) {
        Queue<Data> tmp = new ArrayDeque<>();
        Queue<Data> ret = new ArrayDeque<>();
        tmp.add(new Data(r, c));
        while (!tmp.isEmpty()) {
            Data data = tmp.poll();
            int row = data.row;
            int col = data.col;
            ret.add(new Data(row, col));
            for (int d = 0; d < 4; d++) {
                int nr = row + dr[d];
                int nc = col + dc[d];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc])
                    continue;
                int diff = Math.abs(board[row][col] - board[nr][nc]);
                if (diff < L || diff > R)
                    continue;
                visit[nr][nc] = true;
                tmp.add(new Data(nr, nc));
            }
        }
        if (ret.size() > 1) {
            q.add(ret);
        }
    }

    public void move() {
        while (!q.isEmpty()) {
            Queue<Data> cur = q.poll();
            Queue<Data> tmp = new ArrayDeque<>();
            int sum = 0;
            int size = cur.size();

            while (!cur.isEmpty()) {
                Data pos = cur.poll();
                int row = pos.row;
                int col = pos.col;
                tmp.add(new Data(row, col));
                sum += board[row][col];
            }
            sum /= size;
            while (!tmp.isEmpty()) {
                Data pos = tmp.poll();
                int row = pos.row;
                int col = pos.col;
                board[row][col] = sum;
            }
        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            q = new ArrayDeque<>();
            visit = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visit[i][j]) {
                        visit[i][j] = true;
                        bfs(i, j);
                    }
                }
            }

            if (q.isEmpty())
                break;
            move();
            ans++;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}