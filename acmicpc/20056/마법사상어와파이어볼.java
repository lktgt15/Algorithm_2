import java.util.*;
import java.io.*;

public class 마법사상어와파이어볼 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N,M,K;
    static int[] dy = {-1,-1,0,1,1,1,0,-1}, dx = {0,1,1,1,0,-1,-1,-1};
    static List<FireBall>[][] fireballList;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fireballList = new List[N][N];
        for(int i=0;i<N;i++) for(int j=0;j<N;j++) fireballList[i][j] = new ArrayList<>();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int r,c,m,s,d;
            r = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken())-1;
            m = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            fireballList[r][c].add(new FireBall(r,c,m,s,d));
        }

        while(K-- > 0){
            allMove();
            merge();
        }

        int ans = 0;
        for(int i=0;i<N;i++) for(int j=0;j<N;j++) {
            for (FireBall fb : fireballList[i][j]) {
                ans += fb.m;
            }
        }
        System.out.println(ans);
    }

    static void print(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++) {
                System.out.print(fireballList[i][j].size()+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void merge(){
        for(int i=0;i<N;i++) for(int j=0;j<N;j++){
            if(fireballList[i][j].size() < 2) continue;

            int newm = 0;
            int news = 0;

            boolean odd = false;
            boolean even = false;
            for(FireBall fb : fireballList[i][j]){
                newm += fb.m;
                news += fb.s;
                if(fb.d%2 == 0) even = true;
                else odd = true;
            }
            news /= fireballList[i][j].size();
            newm /= 5;
            fireballList[i][j] = new ArrayList<>();
            if(newm == 0) continue;
            if((odd && !even) || (!odd && even)){
                for(int k=0;k<4;k++){
                    fireballList[i][j].add(new FireBall(i,j,newm,news,k*2));
                }
            }else{
                for(int k=0;k<4;k++){
                    fireballList[i][j].add(new FireBall(i,j,newm,news,k*2+1));
                }
            }
        }
    }

    static void allMove(){
        List<FireBall>[][] newFireballList = new List[N][N];

        for(int i=0;i<N;i++) for(int j=0;j<N;j++) newFireballList[i][j] = new ArrayList<>();

        for(int i=0;i<N;i++) for(int j=0;j<N;j++){
            for(FireBall fb : fireballList[i][j]){
                fb.move();
                newFireballList[fb.r][fb.c].add(fb);
            }
        }

        for(int i=0;i<N;i++) for(int j=0;j<N;j++){
            fireballList[i][j] = newFireballList[i][j];
        }
    }

    static class FireBall{
        int r,c,m,s,d;
        public FireBall(){

        }
        public FireBall(int r,int c,int m,int s,int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        public void move(){
            this.r = this.r + dy[this.d]*this.s;
            this.c = this.c + dx[this.d]*this.s;

            this.r = this.r < 0 ? N-1-(-(this.r+1)%N) : this.r%N;
            this.c = this.c < 0 ? N-1-(-(this.c+1)%N) : this.c%N;
//            this.r = this.r < 0 ? this.r+N : this.r%N;
//            this.c = this.c < 0 ? this.r+N : this.c%N;
        }
    }
}
