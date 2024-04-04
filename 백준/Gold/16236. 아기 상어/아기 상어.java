import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N;
    static Shark shark;
    static int[][] board;
    static boolean[][] visit;
    final static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };

    /**
     * Shark
     */
    public class Shark {
        int row, col, size, fish;

        Shark(int r, int c, int s) {
            row = r;
            col = c;
            size = s;
            fish = 0;
        }

        public void grow() {
            fish++;
            if (fish == size) {
                size++;
                fish = 0;
            }
        }

        public int eat() {
            Queue<Data> q = new ArrayDeque<>();
            PriorityQueue<Data> pq = new PriorityQueue<>();
            q.add(new Data(row, col, 0));
            int m = Integer.MAX_VALUE;
            visit = new boolean[N][N];
            visit[row][col] = true;
            while (!q.isEmpty()) {
                Data d = q.poll();
                int cr = d.row;
                int cc = d.col;
                int count = d.count;
                if (count > m)
                    continue;
                for (int i = 0; i < 4; i++) {
                    int nr = cr + dr[i];
                    int nc = cc + dc[i];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc] || board[nr][nc] > shark.size)
                        continue;
                    if (board[nr][nc] != 0 && board[nr][nc] < shark.size) {
                        m = count;
                        pq.add(new Data(nr, nc, count + 1));
                    }
                    visit[nr][nc] = true;
                    q.add(new Data(nr, nc, count + 1));
                }
            }
            if (pq.isEmpty())
                return 0;
            Data data = pq.poll();
            board[data.row][data.col] = 0;
            shark.row = data.row;
            shark.col = data.col;
            shark.grow();
            return data.count;
        }
    }

    /**
     * Data
     */
    public class Data implements Comparable<Data> {
        int row, col, count;

        Data(int r, int c, int cnt) {
            row = r;
            col = c;
            count = cnt;
        }

        @Override
        public int compareTo(Main.Data o) {
            if (row == o.row)
                return Integer.compare(col, o.col);
            return Integer.compare(row, o.row);
        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    board[i][j] = 0;
                    shark = new Shark(i, j, 2);
                } else if (board[i][j] != 0) {
                }
            }
        }

        int count = 0;
        while (true) {
            int sec = shark.eat();
            if (sec == 0)
                break;
            count += sec;
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}