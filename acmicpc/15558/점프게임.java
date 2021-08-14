import java.util.*;
import java.io.*;

public class 점프게임 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n,k;
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[][] map = new int[2][n+k+1];
        for(int i=0;i<2;i++){
            String in = br.readLine();
            for(int j=0;j<n;j++){
                map[i][j] = in.charAt(j)-'0';
            }
            for(int j=n;j<=k;j++){
                map[i][j] = 1;
            }
        }

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0,0));
        boolean[][] chk = new boolean[2][n+k+1];

        int answer = 0;
        chk[0][0] = true;
        int time = 0;
        while(!q.isEmpty()){

            int size = q.size();
            for(int i=0;i<size;i++){

                Point cur = q.poll();

                if(cur.pos < time) continue;

                if(cur.pos >= n){
                    answer = 1;
                    break;
                }

                // 1
                if((!chk[cur.line^1][cur.pos+k] && map[cur.line^1][cur.pos+k] == 1) || cur.pos+k >= n){
                    chk[cur.line^1][cur.pos+k] = true;
                    q.offer(new Point(cur.line^1,cur.pos+k));
                }

                // 2
                if(cur.pos-1 >= 0 && !chk[cur.line][cur.pos-1] && map[cur.line][cur.pos-1] == 1){
                    chk[cur.line][cur.pos-1] = true;
                    q.offer(new Point(cur.line,cur.pos-1));
                }

                // 3
                if(!chk[cur.line][cur.pos+1] && map[cur.line][cur.pos+1] == 1){
                    chk[cur.line][cur.pos+1] = true;
                    q.offer(new Point(cur.line, cur.pos+1));
                }
            }
            time++;
        }
        System.out.println(answer);
    }

    static class Point{
        int line;
        int pos;

        public Point(){
        }

        public Point(int line,int pos){
            this.line = line;
            this.pos = pos;
        }
    }
}
