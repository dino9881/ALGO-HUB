import java.io.*;
import java.util.*;

class Main{

    // R -> G -> B -> R
    static int N, ans;
    static boolean flag;
    static String s;
    static int[] arr, board;

    static void change(int idx) {
        board[idx - 1] = (board[idx - 1] + 1) % 3;
        board[idx] = (board[idx] + 1) % 3;
        board[idx + 1] = (board[idx + 1] + 1) % 3;
    }
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        board = new int[N];
        s = br.readLine();
        ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            switch(s.charAt(i)){
                case 'R':
                    arr[i] = 0;
                    break;
                case 'G':
                    arr[i] = 1;
                    break;
                default:
                    arr[i] = 2;
                    break;
            }
        }
        if (N == 3){
            if (arr[0] != arr[1] || arr[1] != arr[2]){
                System.out.println(-1);
                return;
            }
        }

        for (int i = 0; i < 3; i++){
            board = arr.clone();
//            System.out.println(Arrays.toString(board));
            int count = 0;
            for (int j = 1; j < N - 1; j++){
                while (board[j - 1] != i){
                    change(j);
                    count++;
//                    System.out.println(Ar rays.toString(board));
                }
            }
            if (board[N - 1] == i){
                flag = true;
                ans = Math.min(ans, count);

            }
//            System.out.println(Arrays.toString(board));
//            System.out.println(count);
        }
        System.out.println(flag ? ans : -1);
    }

    public static void main(String[] args) throws Exception{
        new Main().solution();

    }
}