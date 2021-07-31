import java.util.*;
import java.io.*;

public class 드래곤커브 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static boolean[][] map = new boolean[303][303];
    static int[] dy = {0,-1,0,1}, dx = {1,0,-1,0};
    static int[] curveGenDir;
    static int curveGenDirIdx;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x,y,d,g;
            x = Integer.parseInt(st.nextToken())+150;
            y = Integer.parseInt(st.nextToken())+150;
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            curveGenDir = new int[2000];
            map[x][y] = true;
            curveGenDir[0] = d;
            curveGenDirIdx = 0;
            y += dy[d];
            x += dx[d];
            map[x][y] = true;
            doCurve(x,y,1,g+1);
        }
        int answer = 0;
        for(int i=1;i<=300;i++) for(int j=1;j<=300;j++){
            if(map[i][j] == true && map[i+1][j] == true && map[i][j+1] == true && map[i+1][j+1] == true){
                answer++;
            }
        }

        System.out.println(answer);
    }

    static void doCurve(int x,int y,int curg,int endg){
        if(endg == curg) return;
        int d = 0;
        for(int i= curveGenDirIdx;i>=0;i--,curveGenDirIdx++){
            d = (curveGenDir[i]+1)%4;
            curveGenDir[curveGenDirIdx+1] = d;
            y += dy[d];
            x += dx[d];
            map[x][y] = true;
        }
        doCurve(x,y,curg+1,endg);
    }
}
