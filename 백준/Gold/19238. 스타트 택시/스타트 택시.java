import java.io.*;
import java.util.*;


public class Main {

    static int N, M, F, cr, cc;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    static int[][] board;
    static int[][] idx;
    static int[][] arrr, arrc;
    
    /**
     * Data
     */
    public class Data implements Comparable<Data>{
    
        int row, col, count;
        Data(int r, int c, int cnt){
            row = r;
            col = c;
            count = cnt;
        }     
        @Override
        public int compareTo(Main.Data o) {
            if (count == o.count){
                if (row == o.row)
                    return Integer.compare(col, o.col);
                return Integer.compare(row, o.row);
            }
            return Integer.compare(count, o.count);
        }
    }
    

    public int calDist(int sr, int sc, int er, int ec){

        int[][] dist = new int[N + 1][N + 1];
        Queue<Data> q = new ArrayDeque<>();
        q.add(new Data(sr, sc, 1));
        dist[sr][sc] = 1;
        while (!q.isEmpty()) {
            Data d = q.poll();
            if (dist[er][ec] != 0)
                break;
            int row = d.row;
            int col = d.col;
            int count = d.count;
            for (int i = 0; i < 4; i++){
                int nr = row + dr[i];
                int nc = col + dc[i];
                if (nr <= 0 || nc <= 0 || nr > N || nc > N)
                    continue;
                if (dist[nr][nc] != 0 || board[nr][nc] == -1)
                    continue;
                dist[nr][nc] = count + 1;
                q.add(new Data(nr, nc, count + 1));
            }
        }
        return dist[er][ec] != 0 ?  dist[er][ec] -1 : 100000000;
    }

    public void solution()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        board = new int[N + 1][N + 1];
        idx = new int[N + 1][N + 1];
        arrr = new int[N + 1][N + 1];
        arrc = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++){
                if (Integer.parseInt(st.nextToken()) == 1){
                    board[i][j] = -1;
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        cr = Integer.parseInt(st.nextToken());
        cc = Integer.parseInt(st.nextToken());
        // -1 벽, 0 -> 빈공간,  1 -> 사람
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());
            int er = Integer.parseInt(st.nextToken());
            int ec = Integer.parseInt(st.nextToken());
            board[sr][sc] = 1;
            arrr[sr][sc] = er;
            arrc[sr][sc] = ec;
            idx[sr][sc] = calDist(sr, sc, er, ec);
            // System.out.println(idx[sr][sc] + " !!");
        }
        
        while (M > 0){
            // 가장 가까운 위치를 잡는다.
            Queue<Data> q = new ArrayDeque<>();
            PriorityQueue<Data> pq = new PriorityQueue<>();
            boolean[][] visit = new boolean[N + 1][N + 1];
            visit[cr][cc] = true;
            q.add(new Data(cr, cc, 0));
            while (!q.isEmpty()){
                Data cur = q.poll();
                int row = cur.row;
                int col = cur.col;
                int count = cur.count;
                if (board[row][col] == 1)
                    pq.add(new Data(row, col, count));
                for (int i = 0; i < 4; i++){
                    int nr = row + dr[i];
                    int nc = col + dc[i];
                    if (nr <= 0 || nc <= 0 || nr > N || nc > N)
                        continue;
                    if (visit[nr][nc] || board[nr][nc] == -1)
                        continue;
                    visit[nr][nc] = true;
                    q.add(new Data(nr, nc, count + 1));
                }
            }
            if (pq.isEmpty()){
                System.out.println(-1);
                return;
            }
            Data next = pq.poll();
            int toPerson = next.count;
            int toFlag = idx[next.row][next.col];
            if (toPerson + toFlag > F){
                System.out.println(-1);
                return;
            }
            // System.out.println(F + " " + toPerson + " " + toFlag);
            board[next.row][next.col] = 0;
            F -= toPerson;
            F += toFlag;
            M--;
            cr = arrr[next.row][next.col];
            cc = arrc[next.row][next.col];
        }
        System.out.println(F);
    }
    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}
