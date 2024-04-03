import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int R, C, T, ans;
    static int[][] board;
    static List<Pair> ac;
    final static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };

    /**
     * Pair
     */
    public class Pair {

        int row, col;

        Pair(int r, int c) {
            this.row = r;
            this.col = c;
        }

        @Override
        public String toString() {

            return row + " " + col;
        }
    }

    public void print() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void check() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] > 0) {
                    ans += board[i][j];
                }
            }
        }
        System.out.println(ans);
    }

    public void spread() {
        List<Pair> dusts = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] > 0) {
                    dusts.add(new Pair(i, j));
                }
            }
        }
        int[][] copy = new int[R][C];
        for (int i = 0; i < R; i++) {
            copy[i] = board[i].clone();
        }
        for (Pair p : dusts) {
            int r = p.row;
            int c = p.col;
            int count = 0;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 0 || nc < 0 || nr >= R || nc >= C || board[nr][nc] == -1)
                    continue;
                count++;
                copy[nr][nc] += board[r][c] / 5;
            }
            copy[r][c] -= (board[r][c] / 5) * count;
        }
        board = copy;
    }

    public void ac() {
        Pair[] up = { new Pair(0, 1), new Pair(-1, 0), new Pair(0, -1), new Pair(1, 0) },
                down = { new Pair(0, 1), new Pair(1, 0), new Pair(0, -1), new Pair(-1, 0) };
        Pair upAC = ac.get(0);
        Pair downAC = ac.get(1);

        int r = upAC.row;
        int c = upAC.col;
        int d = 0;
        int val = 0;
        while (true) {
            Pair dir = up[d];
            int nr = r + dir.row;
            int nc = c + dir.col;
            if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                d++;
                continue;
            }
            if (board[nr][nc] == -1) {
                break;
            }
            int tmp = board[nr][nc];
            board[nr][nc] = val;
            val = tmp;
            r = nr;
            c = nc;
        }

        r = downAC.row;
        c = downAC.col;
        d = 0;
        val = 0;
        while (true) {
            Pair dir = down[d];
            int nr = r + dir.row;
            int nc = c + dir.col;
            if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                d++;
                continue;
            }
            if (board[nr][nc] == -1) {
                break;
            }
            int tmp = board[nr][nc];
            board[nr][nc] = val;
            val = tmp;
            r = nr;
            c = nc;
        }

    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[R][C];
        ac = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == -1 && ac.size() == 0) {
                    ac.add(new Pair(i, j));
                    ac.add(new Pair(i + 1, j));
                }
            }
        }
        for (int i = 0; i < T; i++) {
            spread();
            ac();
        }
        // System.out.println();
        check();

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}