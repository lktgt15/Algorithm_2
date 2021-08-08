import java.util.*;
import java.io.*;

public class 안전영역 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int max_height;
    static int N;
    static int ans = 1;
    static int[][] map;
    static int[] dy={1,0,-1,0}, dx={0,1,0,-1};
    static boolean[][] chk;
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        map = new int[N+2][N+2];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                max_height = Math.max(max_height,map[i][j]);
            }
        }

        for(int i=1;i<=max_height;i++){
            ans = Math.max(ans,solveByDfs(i));
        }

        System.out.println(ans);
    }

    static int solveByDfs(int rain){
        chk = new boolean[N+2][N+2];
        int subans = 0;
        for(int i=1;i<=N;i++) for(int j=1;j<=N;j++){
            if(map[i][j] > rain && !chk[i][j]){
                dfs(i,j,rain);
                subans++;
            }
        }
        return subans;
    }

    static void dfs(int y,int x,int rain){
        chk[y][x] = true;
        for(int i=0;i<4;i++){
            int ny = y+dy[i];
            int nx = x+dx[i];
            if(map[ny][nx] > rain && !chk[ny][nx]){
                dfs(ny,nx,rain);
            }
        }
    }
}
