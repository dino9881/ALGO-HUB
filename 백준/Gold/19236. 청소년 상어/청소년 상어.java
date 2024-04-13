import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * Main
 */
public class Main {
    static int ans;
    static final int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}, dc = {0, -1, -1, -1, 0, 1, 1, 1};
    static Fish[][] board;
    /**
     * Fish
     */
    public class Fish implements Comparable<Fish>{
        int num, row, col, dir;
        Fish(int n, int r, int c, int d){
            row = r;
            col = c;
            num = n;
            dir = d;
        }

        void move(Fish[][] arr, int sr, int sc){
            // System.out.print(num + " move! ");
            for (int i = 0; i < 8; i++){
                int ndir = (dir + i) % 8;
                int nr = row + dr[ndir];
                int nc = col + dc[ndir];
                if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4 || (nr == sr && nc == sc))
                    continue;
                // System.out.println("from " + row + ", " + col + " to " + nr + ", " + nc);
                this.dir = ndir; // 방향은 이동한 방향으로 바꿈
                if (arr[nr][nc] == null){ // 빈 공간이면
                    // System.out.println("there is empty");
                    arr[nr][nc] = this;
                    arr[row][col] = null;
                    this.row = nr;
                    this.col = nc;
                }else{
                    Fish targetFish = arr[nr][nc];
                    // System.out.println("switch with " + arr[nr][nc].num);
                    // System.out.println("before " + this + " --- " + targetFish);
                    targetFish.row = this.row;
                    targetFish.col = this.col;
                    arr[row][col] = targetFish;
                    arr[nr][nc] = this;
                    this.row = nr;
                    this.col = nc;
                    // System.out.println("after " + this + " --- " + targetFish);
                }
                return;
            }
        }

        @Override
        public int compareTo(Main.Fish o) {
            return Integer.compare(num, o.num);
        }
        @Override
        public String toString() {
            return Integer.toString(num) + "(" + dir + ")" ;
        }
    }

    public void fishmove(Fish[][] copyboard, int sr, int sc){

        // System.out.println(Arrays.deepToString(copyboard));
        PriorityQueue<Fish> pq = new PriorityQueue<>();
        for (int i = 0 ; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (copyboard[i][j] != null){
                    pq.add(copyboard[i][j]);
                }
            }
        }
        while (!pq.isEmpty()){
            pq.poll().move(copyboard, sr, sc);
        }
        // System.out.println("MOVE");
        // System.out.println(Arrays.deepToString(copyboard));
    }

    public void solve(int count, Fish[][] copyboard, int sr, int sc, int sd){
        // if (count > 80)
            // return;
            // 
            // System.out.println("shark " + sr + " " + sc + " " + sd + " : " + count);
            // System.out.println(Arrays.deepToString(copyboard));
            ans = Math.max(ans, count);
            fishmove(copyboard, sr, sc);
            // System.out.println(Arrays.deepToString(copyboard));
        for (int i = 1; i <= 4; i++){
            int nr = sr + (i * dr[sd]);
            int nc = sc + (i * dc[sd]);
            if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4)
                return;
            if (copyboard[nr][nc] == null)
                continue;
            Fish[][] nextboard = new Fish[4][4];
            for (int j = 0; j < 4; j++){
                for (int k = 0; k < 4; k++){
                    if (copyboard[j][k] != null){
                        Fish fish = copyboard[j][k];
                        nextboard[j][k] = new Fish(fish.num, fish.row, fish.col, fish.dir);
                    }
                }
            }
            Fish targetFish = nextboard[nr][nc];
            nextboard[nr][nc] = null;
            solve(count + targetFish.num, nextboard, nr, nc, targetFish.dir);
        }
    }
    public void solution()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        board = new Fish[4][4];
        for (int i = 0 ; i < 4; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++){
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                board[i][j] = new Fish(num, i, j, dir - 1);
            }
        }
        Fish fish = board[0][0];
        ans += fish.num;
        board[0][0] = null;
        solve(ans, board, 0, 0, fish.dir);
        System.out.println(ans);
        // fishmove(board);
        // System.out.println(Arrays.deepToString(board));
        // sr = 1;
        // sc = 1;
        // sd = 4;
        // board[1][1] = null;
        // fishmove(board);
        // System.out.println(Arrays.deepToString(board));
    }
    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}