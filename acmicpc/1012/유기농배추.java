import java.io.*;
import java.util.*;

public class 유기농배추 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] dy={1,-1,0,0}, dx={0,0,1,-1};
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++) solve();
    }


    static void solve() throws IOException{
        int m,n,k;
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[n+2][m+2];

        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int x,y;
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            map[y+1][x+1] = true;
        }

        int ans = 0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(map[i][j]){
                    ans++;
                    check(i,j,map);
                }
            }
        }
        System.out.println(ans);
    }

    static void check(int y,int x,boolean[][] map){
        map[y][x] = false;
        for(int i=0;i<4;i++){
            int ny = y+dy[i];
            int nx = x+dx[i];
            if(map[ny][nx]) check(ny,nx,map);
        }
    }
}
