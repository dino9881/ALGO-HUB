import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N, K, ans;
    static int[] arr;
    static int[][] board;
    static boolean[] dead;


    void bt(int count, int nk){
        if (dead[K])
            return;
            // System.out.println(count);
            // System.out.println(Arrays.toString(arr));
        ans = Math.max(nk, ans);
        // 밤
        if ((N - count) % 2 == 0){
            // System.out.println("night");
            for (int i = 0; i< N; i++){
                // 본인이 아니고 살아있는 사람
                if (!dead[i]){
                    dead[i] = true;
                    for (int j = 0; j < N; j++){
                        arr[j] += board[i][j];
                    }
                    // System.out.println("night kill " + i);
                    // System.out.println(Arrays.toString(arr));
                    bt(count + 1, nk + 1);
                    dead[i] = false;
                    for (int j = 0; j < N; j++){
                        arr[j] -= board[i][j];
                    }
                }
            }
        }else{
            // 낮일 경우
            int idx = -1, max = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++){
                if (!dead[i] && arr[i] > max){
                    max = arr[i];
                    idx = i;
                }
            }
            // System.out.println("day kill " + idx);
            dead[idx] = true;
            bt(count + 1, nk);
            dead[idx] = false;

        }
    }

    public void solution()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        board = new int[N][N];
        dead = new boolean[N];
        ans = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        K = Integer.parseInt(br.readLine());
        bt(0, 0);
       System.out.println(ans);
    }

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}       