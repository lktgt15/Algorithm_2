import java.util.*;
import java.io.*;

public class 상어초등학교 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static Kan[][] map;
    static int N;
    static int num,a,b,c,d;
    static int[] dy = {1,-1,0,0}, dx = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new Kan[N][N];
        for(int i=0;i<N;i++) for(int j=0;j<N;j++) map[i][j] = new Kan(true,-1);
        for(int i=0;i<N*N;i++){
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            select();
        }

        int ans = 0;
        for(int i=0;i<N;i++) for(int j=0;j<N;j++){
            int cnt = 1;
            for(int k=0;k<4;k++){
                int ny = i+dy[k];
                int nx = j+dx[k];
                if(isValid(ny,nx) && (map[ny][nx].val == map[i][j].a || map[ny][nx].val == map[i][j].b || map[ny][nx].val == map[i][j].c || map[ny][nx].val == map[i][j].d)){
                    cnt *= 10;
                }
            }
            cnt /= 10;
            ans += cnt;
        }
        System.out.println(ans);
    }

    static void select(){
        int likeCount = -1;
        int adjBlank = -1;
        int row = 100;
        int col = 100;
        for(int i=0;i<N;i++) for(int j=0;j<N;j++){
            if(!map[i][j].blank) continue;

            int subLikeCount = 0;
            int subAdjBlank = 0;
            for(int k=0;k<4;k++){
                int ny = i+dy[k];
                int nx = j+dx[k];
                if(isValid(ny,nx)){
                    if((map[ny][nx].val == a || map[ny][nx].val == b || map[ny][nx].val == c || map[ny][nx].val == d))
                        subLikeCount++;
                    if(map[ny][nx].blank){
                        subAdjBlank++;
                    }
                }
            }

            if(likeCount < subLikeCount){
                likeCount = subLikeCount;
                adjBlank = subAdjBlank;
                row = i;
                col = j;
            }else if(likeCount == subLikeCount){
                if(adjBlank < subAdjBlank){
                    likeCount = subLikeCount;
                    adjBlank = subAdjBlank;
                    row = i;
                    col = j;
                }else if(adjBlank == subAdjBlank){
                    if(i < row){
                        likeCount = subLikeCount;
                        adjBlank = subAdjBlank;
                        row = i;
                        col = j;
                    }else if(row == i){
                        if(j < col){
                            likeCount = subLikeCount;
                            adjBlank = subAdjBlank;
                            row = i;
                            col = j;
                        }
                    }
                }
            }
        }
        map[row][col].val = num;
        map[row][col].blank = false;
        map[row][col].a = a;
        map[row][col].b = b;
        map[row][col].c = c;
        map[row][col].d = d;
    }

    static boolean isValid(int y, int x){
        return y>=0 && y<N && x>=0 && x<N;
    }

    static class Kan implements Comparator<Kan>{
        int val,a,b,c,d;
        boolean blank;

        public Kan(){}
        public Kan(boolean blank,int val){
            this.blank = blank;
            this.val = val;
        }

        @Override
        public int compare(Kan o1, Kan o2) {
            return Boolean.compare(o1.blank,o2.blank);
        }
    }
}
