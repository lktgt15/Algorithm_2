import java.util.*;
import java.io.*;

public class 마법사상어와비바라기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] dy={0,-1,-1,-1,0,1,1,1}, dx={-1,-1,0,1,1,1,0,-1};
    static int[][] bucket;
    static int N,M;
    static List<Cloud> clouds;
    static boolean[][] chk;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bucket = new int[N][N];
        resetCloud();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                bucket[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        clouds.add(new Cloud(N-1,0));
        clouds.add(new Cloud(N-1,1));
        clouds.add(new Cloud(N-2,0));
        clouds.add(new Cloud(N-2,1));

        for(int i=0;i<M;i++){
            chk = new boolean[N][N]; // 비내린곳

            st = new StringTokenizer(br.readLine());
            int d,s;
            d = Integer.parseInt(st.nextToken())-1;
            s = Integer.parseInt(st.nextToken());
            moveCloud(d,s); // 1
            rainCloud();    // 2
            copyBug();      // 4
            resetCloud();   // 3
            createCloud();  // 5
        }

        int ans = 0;
        for(int i=0;i<N;i++) for(int j=0;j<N;j++){
            ans += bucket[i][j];
        }
        System.out.println(ans);
    }

    static void createCloud(){
        for(int i=0;i<N;i++) for(int j=0;j<N;j++){
            if(bucket[i][j] >= 2 && !chk[i][j]){
                bucket[i][j] -= 2;
                clouds.add(new Cloud(i,j));
            }
        }
    }

    static void copyBug(){
        for(Cloud c : clouds){
            int cnt = 0;
            for(int i=1;i<=7;i+=2){
                int ny = c.r+dy[i];
                int nx = c.c+dx[i];
                if(isValid(ny,nx) && bucket[ny][nx] > 0){
                    cnt++;
                }
            }
            bucket[c.r][c.c] += cnt;
        }
    }

    static boolean isValid(int y,int x){
        return y>=0 && y<N && x>=0 && x<N;
    }

    static void resetCloud(){
        clouds = new ArrayList<>();
    }

    static void rainCloud(){
        for(Cloud c : clouds){
            bucket[c.r][c.c]++;
            chk[c.r][c.c] = true;
        }
    }

    static void moveCloud(int d,int s){
        for(Cloud c : clouds){
            c.move(d,s);
        }
    }

    static class Cloud{
        int r,c;
        public Cloud(){}
        public Cloud(int r,int c){
            this.r = r;
            this.c = c;
        }

        void move(int d,int s){
            this.r = (this.r + dy[d]*s+50*N)%N;
            this.c = (this.c + dx[d]*s+50*N)%N;
        }
    }
}
