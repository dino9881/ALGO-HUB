import java.io.*;
import java.util.*;

class Solution {
    
    static Set<Integer>[][] memo = new HashSet[100][100];
    static Set<Integer>[][] crush = new HashSet[100][100];
    
    static boolean[] check = new boolean[100 + 1];
    
    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        for (int i = 0; i < 100; i++){
            for (int j = 0; j < 100; j++){
                memo[i][j] = new HashSet<>();
                crush[i][j] = new HashSet<>();
            }
        }
        
        for (int i = 0; i < routes.length; i++){
            int cr = -1;
            int cc = -1;
            int time = -1;
            for (int j = 0; j < routes[i].length; j++){
                int point = routes[i][j] - 1;
                if (j == 0){
                    cr = points[point][0] - 1;
                    cc = points[point][1] - 1;
                    if (memo[cr][cc].contains(time)){
                        crush[cr][cc].add(time);
                    }
                    memo[cr][cc].add(time);
                    continue;
                }
                int pr = points[point][0] - 1;
                int pc = points[point][1] - 1;
                while (pr != cr){
                    ++time;
                    if (pr < cr){
                        cr--;
                    }else{
                        cr++;
                    }
                    if (memo[cr][cc].contains(time)){
                        crush[cr][cc].add(time);
                    }
                    memo[cr][cc].add(time);
                }
                while (pc != cc){
                    ++time;
                    if (pc < cc){
                        cc--;
                    }else{
                        cc++;
                    }
                    if (memo[cr][cc].contains(time)){
                        crush[cr][cc].add(time);
                    }
                    memo[cr][cc].add(time);
                }
            }
        }
        for (int i = 0; i < 100; i++){
            for (int j = 0; j < 100; j++){
                answer += crush[i][j].size();
            }
        }
        
        
        return answer;
    }
}