import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static int N, K, M;
    static int info[][];
    static boolean used[];
    static boolean isFinish;
    static Person  b, c;
    static int AwinCnt, BwinCnt,CwinCnt;
    
    /**
     * 
     */
    public class Person {
        int match;
        int[] hand;
        int order;


        Person(String s, int o){
            order = o;
            match = 0;
            hand = new int[20];
            StringTokenizer st = new StringTokenizer(s);
            for (int i = 0; i < 20; i++){
                hand[i] = Integer.parseInt(st.nextToken()) - 1;
            }
        }
    }

    public void solve(int level, Person p1, Person p2){ // level 번째 게임을 하는 사람 p1, p2, wcnt만큼 이김
        if (isFinish)
            return;
        if (AwinCnt >= K){ // 만족했으면
            // System.out.println(AwinCnt + " " + BwinCnt + " " + CwinCnt +" " + level);
            // System.out.println(Arrays.toString(used));
            System.out.println(1);
            isFinish = true;
            return;
        }
        if (BwinCnt >= K || CwinCnt >= K) // 다른 사람이 이겼으면 
            return;
        if (level == M){ // 최대 게임수이면
            return;
        }
        boolean flag = false;
        for (int i = 0; i < N; i++){  // 낼게 없으면
            if (!used[i])
                flag = true;
        }
        if (!flag)
            return;
        if ((p1 == b || p1 == c) && (p2 == b || p2 == c)){ // 나 빼고 하는 경우
            int a1 = p1.hand[p1.match++];
            int a2 = p2.hand[p2.match++];
            // System.out.println(level + ": B AND C " + (a1 + 1) + " " + (a2 + 1));
            if (a1 == a2){ // 비긴 경우
                CwinCnt++;
                solve(level + 1, c, null);
                CwinCnt--;
            }
            else if (info[a1][a2] == 2){ // p1 이 이김
                if (p1 == b){ // b 가 이김
                    BwinCnt++;
                    solve(level + 1, b, null);
                    BwinCnt--;
                }else{
                    CwinCnt++;
                    solve(level + 1, c, null);
                    CwinCnt--;
                }
            }else if (info[a1][a2] == 0){
                if (p1 == c){ // b 가 이김
                    BwinCnt++;
                    solve(level + 1, b, null);
                    BwinCnt--;
                }else{
                    CwinCnt++;
                    solve(level + 1, c, null);
                    CwinCnt--;
                }
            }
            p1.match--;
            p2.match--;
        }else{
            Person player = p1 == null ? p2 : p1; // 나랑 게임 하는 사람은
            for (int i = 0; i < N; i++){
                if (!used[i]){ // 안냈으면
                    used[i] = true; // 내자
                    int a1 = player.hand[player.match++];
                    // System.out.println(a1 + " !!");
                    if (a1 == i){ // 비기면
                        if (player.order == 2){ // 내가 이긴거 c랑 게임한거
                            CwinCnt++;
                            // System.out.println(level + ": draw A AND " + (player == b ? "B " : "C ") + (i + 1) + " " + (a1 + 1));
                            solve(level + 1, c, b); // 너네끼리 해
                            CwinCnt--;
                        }else{ // b가 이긴거 b랑 게임한거 
                            BwinCnt++;
                            solve(level + 1, b, c); // 너네끼리 해
                            BwinCnt--;
                        }
                    }else if(info[i][a1] == 2){ // 내가 이긴건데
                        AwinCnt++;
                        // System.out.println(info[i][a1] + "!!");
                        // System.out.println(level + ": win A AND " + (player == b ? "B " : "C ") + (i + 1) + " " + (a1 + 1));
                        // System.out.println(level + ": A AND C " + (a1 + 1) + " " + (a2 + 1));
                        solve(level + 1, null, player == b ? c : b);
                        AwinCnt--;
                    }else{
                        if (player.order == 1){ // b랑 한거면
                            BwinCnt++;
                            solve(level + 1, b, c);
                            BwinCnt--;
                        }else{
                            CwinCnt++;
                            solve(level + 1, b, c);
                            CwinCnt--;
                        }
                    }
                    used[i] = false;
                    player.match--;
                }
            }
        
        }
    }


    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = 3 * (K - 1) + 1;
        info = new int[N][N];
        used = new boolean[N];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        b = new Person(br.readLine(), 1);
        c = new Person(br.readLine(), 2);
        solve(0, null, b);
        if (!isFinish){
            System.out.println(0);
        }
        
    }
    public static void main(String[] args)throws Exception {
        new Main().solution();
    }
}