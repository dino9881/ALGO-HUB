import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class  Main {
	
	
	static int N, tr, tc, td, er, ec, ed, countB, countE;
	static char[][] board;
	static boolean[][][] visit;
	static List<Pair> bList, eList;
	final static int var = 0, hor = 1;
	
	
	class Pair{
		int row, col;
		Pair(int r, int c){
			row = r;
			col = c;
		}
	}
	
	class Data{
		int row, col, dir, count;
		Data(int r, int c, int d, int cnt){
			row = r;
			col = c;
			dir = d;
			count = cnt;
		}
	}
	
	public boolean rotate(int row, int col) {
		if (row <= 0 || col <= 0 || row >= N -1 || col >= N - 1)
			return false;
		//System.out.println(row + " " + col);
		int[] dirr = {-1, -1, -1, 0, 0, 1, 1, 1}, dirc = {-1, 0, 1, -1, 1, -1, 0, 1};
		for (int i = 0; i < 8; i++) {
			int nr = row + dirr[i];
			int nc = col + dirc[i];
			//System.out.println(nr + ", " + nc);
			
			if (board[nr][nc] == '1')
				return false;
		}
		return true;
	}
	
	
	public void solution()throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		bList = new ArrayList<>();
		eList = new ArrayList<>();
		N = Integer.parseInt(br.readLine());
		board = new char[N][N];
		visit = new boolean[N][N][2];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = s.charAt(j);
				if (board[i][j] == 'B') {
					bList.add(new Pair(i, j));
					board[i][j] = '0';
				}
				else if (board[i][j] == 'E') {
					eList.add(new Pair(i, j));
					board[i][j] = '0';
				}
			}
		}
		tr = bList.get(1).row;
		tc = bList.get(1).col;
		td = (bList.get(0).row == bList.get(1).row) ? hor : var;
		er = eList.get(1).row;
		ec = eList.get(1).col;
		ed = (eList.get(0).row == eList.get(1).row) ? hor : var;
//		System.out.println(tr + " " + tc + " " + td);
//		System.out.println(er + " " + ec + " " + ed);
		
		Queue<Data> q = new ArrayDeque();
		q.add(new Data(tr, tc, td, 0));
		while (!q.isEmpty()) {
			Data d = q.poll();
			int row = d.row;
			int col = d.col;
			int dir = d.dir;
			int count = d.count;
			if (row == er && col == ec && dir == ed) {
				System.out.println(count);
				return;
			}
			visit[row][col][dir] = true;
			
			if (rotate(row, col) && !visit[row][col][(dir + 1) % 2]) {
				q.add(new Data(row, col, (dir + 1) % 2, count + 1));
			}
			if (row != 0 && !visit[row - 1][col][dir]) { // up
				if (dir == hor) {					
					if (board[row - 1][col - 1] == '0' && board[row - 1][col] == '0' && board[row - 1][col + 1] == '0' ) {
						q.add(new Data(row - 1, col, dir, count + 1));
						visit[row - 1][col][dir] = true;
					}
				}
				else {
					if (row > 1 && board[row - 2][col] == '0') {
						q.add(new Data(row - 1, col, dir, count + 1));
						visit[row - 1][col][dir] = true;
					}
				}
			}
			
			if (row != N - 1 && !visit[row + 1][col][dir]) { // down
				if (dir == hor) {					
					if (board[row + 1][col - 1] == '0' && board[row + 1][col] == '0' && board[row + 1][col + 1] == '0' ) {
						q.add(new Data(row + 1, col, dir, count + 1));
						visit[row + 1][col][dir] = true;
					}
				}
				else {
					if (row < N - 2 && board[row + 2][col] == '0') {
						q.add(new Data(row + 1, col, dir, count + 1));
						visit[row + 1][col][dir] = true;
					}
				}
			}
			if (col != 0 && !visit[row][col - 1][dir]) {// left
				if (dir == var) {					
					if (board[row - 1][col - 1] == '0' && board[row][col - 1] == '0' && board[row + 1][col - 1] == '0' ) {
						q.add(new Data(row, col - 1, dir, count + 1));
						visit[row][col - 1][dir] = true;
					}
				}
				else {
					if (col > 1 && board[row][col - 2] == '0') {
						q.add(new Data(row, col - 1, dir, count + 1));
						visit[row][col - 1][dir] = true;
					}
				}
			}
			
			if (col != N - 1 && !visit[row][col + 1][dir]) { // right
				if (dir == var) {					
					if (board[row][col + 1] == '0' && board[row - 1][col + 1] == '0' && board[row + 1][col + 1] == '0' ) {
						q.add(new Data(row, col + 1, dir, count + 1));
						visit[row][col + 1][dir] = true;
					}
				}
				else {
					if (col < N - 2 && board[row][col + 2] == '0') {
						q.add(new Data(row, col + 1, dir, count + 1));
						visit[row][col + 1][dir] = true;
					}
				}
			}
			
		}
		System.out.println(0);
		
	}
	
	public static void main(String[] args) throws Exception {
		new Main().solution();
	}
}
