import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class Main {
    static int N, ans;
    static int[][] board, dp;

    static PriorityQueue<Data> pq = new PriorityQueue<>();

    static final int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

    class Data implements Comparable<Data>{
        int row, col, count;
        Data(int r, int c, int cnt){
            this.row = r;
            this.col = c;
            this.count = cnt;
        }

        @Override
        public int compareTo(Data d){
            return Integer.compare(d.count, count);
        }

    }
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                pq.add(new Data(i, j, board[i][j]));
            }
        }

        while (!pq.isEmpty()){
            Data d = pq.poll();
            int row = d.row;
            int col = d.col;
            int count = d.count;
            for (int i = 0; i < 4; i++){
                int nr = row + dr[i];
                int nc = col + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N)
                    continue;
                if (board[nr][nc] > count){
                    dp[row][col] = Math.max(dp[row][col], dp[nr][nc] + 1);
                }
            }
            ans = Math.max(ans, dp[row][col]);
        }
        System.out.println(ans + 1);
    }
    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}