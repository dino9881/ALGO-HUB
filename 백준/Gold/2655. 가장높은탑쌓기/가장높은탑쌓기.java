
import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] dp;
    static Data[] arr;
    static PriorityQueue<Data> pq;

    public class Data implements Comparable<Data>{
        int idx, width, height, weight;

        Data(int idx, int width, int height , int weight){
            this.idx = idx;
            this.width = width;
            this.height = height;
            this.weight = weight;
        }
        @Override
        public int compareTo(Main.Data o) {
            return Integer.compare(width, o.width);
        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp = new int[N];
        arr = new Data[N];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br. readLine());
            Data data = new Data(i + 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            arr[i] = data;
        }

        Arrays.sort(arr);
        int ans = 0;
        for (int i = 0; i < N; i++){
            for (int j = i - 1; j >=0; j--){
                if (arr[i].weight > arr[j].weight)
                    dp[i] = Math.max(dp[i], dp[j] + arr[i].height);
            }
            if (dp[i] == 0)
                dp[i] = arr[i].height;
            ans = Math.max(ans, dp[i]);
        }
        // System.out.println(Arrays.toString(dp));
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        while (N > 0){
            if (dp[N - 1] == ans){
                ans -= arr[N - 1].height;
                stack.add(arr[N - 1].idx);
                count++;
            }
            N--;
        }
        System.out.println(count);
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}