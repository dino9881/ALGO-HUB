import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Solution {
    static int N, W, H, T, ans, total;
    static int[][] input;
    final static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };

    public int shoot(int idx, int board[][]) {
        int sum = 0;
        for (int i = 0; i < H; i++) {
            if (board[i][idx] != 0) {
                sum = pop(i, idx, board);
                break;
            }
        }

        for (int i = 0; i < W; i++) {
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < H; j++) {
                if (board[j][i] != 0) {
                    stack.push(board[j][i]);
                    board[j][i] = 0;
                }
            }
            int h = H - 1;
            while (!stack.empty()) {
                board[h][i] = stack.pop();
                h--;
            }
        }
        return sum;
    }

    public int pop(int row, int col, int board[][]) {
        if (board[row][col] == 0)
            return 0;
        int sum = 1;
        int range = board[row][col] - 1;
        board[row][col] = 0;
        for (int d = 0; d < 4; d++) {
            int nr, nc;
            for (int i = 1; i <= range; i++) {
                // System.out.println("row = " + row + "col = " + col);
                nr = row + (dr[d] * i);
                nc = col + (dc[d] * i);
                if (nr < 0 || nc < 0 || nr >= H || nc >= W)
                    break;
                sum += pop(nr, nc, board);
            }
        }
        return sum;
    }

    public void combination(int level, int count, int tmp[][]) {
        if (level == N) {
            // System.out.println(count + " count");
            ans = Math.max(ans, count);
            return;
        }
        for (int idx = 0; idx < W; idx++) { // k -> 쏘는 위치
            int[][] board = new int[H][];
            for (int i = 0; i < H; i++) {
                board[i] = tmp[i].clone();
            }

            int sum = 0;
            for (int i = 0; i < H; i++) {
                if (board[i][idx] != 0) {

                    // System.out.println("!@#");
                    sum = pop(i, idx, board);
                    break;
                }
            }

            for (int i = 0; i < W; i++) {
                Stack<Integer> stack = new Stack<>();
                for (int j = 0; j < H; j++) {
                    if (board[j][i] != 0) {
                        stack.push(board[j][i]);
                        board[j][i] = 0;
                    }
                }
                int h = H - 1;
                while (!stack.empty()) {
                    board[h][i] = stack.pop();
                    h--;
                }
            }
            // System.out.println(sum + " !!");
            if (sum != 0 || count == total) {
                combination(level + 1, count + sum, board);
            }

        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            ans = 0;
            total = 0;
            input = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    input[i][j] = Integer.parseInt(st.nextToken());
                    if (input[i][j] != 0)
                        total++;
                }
            }
            combination(0, 0, input);
            // for (int i = 0; i < H; i++) {
            // for (int j = 0; j < W; j++) {
            // System.out.print(board[i][j] + " ");
            // }
            // System.out.println();
            // }
            // System.out.println(ans);
            sb.append("#").append(tc).append(" ").append(total - ans).append("\n");
        }
        System.out.print(sb);

    }

    public static void main(String[] args) throws Exception {
        new Solution().solution();

    }
}