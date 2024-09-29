import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    final static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };
    static int N, M;
    static int S, W; // 양, 늑대 수

    static boolean[][] visit;
    static int[][] board;

    /**
     * Point
     */
    public class Point {
        int row, col;

        Point(int r, int c) {
            row = r;
            col = c;
        }
    }

    public void bfs(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();

        int tmpS = 0, tmpW = 0;

        q.add(new Point(r, c));
        while (!q.isEmpty()) {
            Point p = q.poll();
            int row = p.row;
            int col = p.col;
            visit[row][col] = true;
            if (board[row][col] == 1) {
                tmpS++;
            } else if (board[row][col] == -1) {
                tmpW++;
            }
            for (int i = 0; i < 4; i++) {
                int nr = dr[i] + row;
                int nc = dc[i] + col;
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visit[nr][nc])
                    continue;
                visit[nr][nc] = true;
                q.add(new Point(nr, nc));
            }
        }
        if (tmpS != 0 && tmpW != 0) {
            if (tmpS > tmpW) {
                tmpW = 0;
            } else {
                tmpS = 0;
            }
        }
        S += tmpS;
        W += tmpW;
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char ch = s.charAt(j);
                switch (ch) {
                    case '#':
                        visit[i][j] = true;
                        break;
                    case 'o':
                        board[i][j] = 1;
                        break;
                    case 'v':
                        board[i][j] = -1;
                        break;
                    default:
                        break;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visit[i][j]) {
                    bfs(i, j);
                }
            }
        }
        System.out.println(S + " " + W);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}