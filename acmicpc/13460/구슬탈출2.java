import java.util.*;
import java.io.*;

public class 구슬탈출2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N,M,ans = 11;
    static int[] dy={1,-1,0,0}, dx={0,0,1,-1};
    static char[][] map;
    static int Ry,Rx,By,Bx,Oy,Ox;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i=0;i<N;i++){
            String in = br.readLine();
            for(int j=0;j<M;j++) {
                map[i][j] = in.charAt(j);
                if(map[i][j] == 'O') {map[i][j] = '.'; Oy = i; Ox = j;}
                else if(map[i][j] == 'R') {map[i][j] = '.'; Ry = i; Rx = j;}
                else if(map[i][j] == 'B')  {map[i][j] = '.'; By = i; Bx = j;}
            }
        }

        solve(1);
        System.out.println(ans == 11 ? -1 : ans);
    }

    static void solve(int trial){
        if(trial >= ans) return;
        int prevRy = Ry, prevRx = Rx, prevBy = By, prevBx = Bx;

        for(int dir=0;dir<4;dir++){
            for(int type=0;type<2;type++){ // 0 == R , 1 == B
                move(dir,type);
            }
            if(By == Oy && Bx == Ox) {
                Ry = prevRy; Rx = prevRx;
                By = prevBy; Bx = prevBx;
                continue;
            }else if(Ry == Oy && Rx == Ox){
                ans = Math.min(trial,ans);
                Ry = prevRy; Rx = prevRx;
                By = prevBy; Bx = prevBx;
                return;
            }
            if(Ry == By && Rx == Bx){
                int Rdist = Math.abs(Ry-prevRy)+Math.abs(Rx-prevRx);
                int Bdist = Math.abs(By-prevBy)+Math.abs(Bx-prevBx);
                if(Rdist <= Bdist){
                    By -= dy[dir];
                    Bx -= dx[dir];
                }else{
                    Ry -= dy[dir];
                    Rx -= dx[dir];
                }
            }
            solve(trial+1);
            Ry = prevRy; Rx = prevRx;
            By = prevBy; Bx = prevBx;
        }
    }

    static void move(int dir,int type){
        if(type == 0) moveR(dir);
        else moveB(dir);
    }

    static void moveR(int dir){
        int nxtRy = Ry+dy[dir], nxtRx = Rx+dx[dir];
        while(isValid(nxtRy,nxtRx)){
            Ry = nxtRy;
            Rx = nxtRx;
            if(Ry == Oy && Rx == Ox) break;
            nxtRy += dy[dir];
            nxtRx += dx[dir];
        }
    }

    static void moveB(int dir){
        int nxtBy = By+dy[dir], nxtBx = Bx+dx[dir];
        while(isValid(nxtBy,nxtBx)){
            By = nxtBy;
            Bx = nxtBx;
            if(By == Oy && Bx == Ox) break;
            nxtBy += dy[dir];
            nxtBx += dx[dir];
        }
    }

    static boolean isValid(int y,int x){
        return map[y][x] == '.';
    }
}
