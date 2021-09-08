import java.util.*;
import java.io.*;

public class 마법사상어와블리자드 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] dy={-1,1,0,0}, dx={0,0,-1,1};
    static int[] firstPos={6,2,0,4}; // 거리 +1당 dist+8, 위,아래,왼쪽,오른쪽
    static int[] firstDist={15,11,9,13};
    static int N,M,d,s;
    static int[][] firstMap;
    static int[] myMap,newMap;
    static int ans;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        firstMap = new int[N][N];
        myMap = new int[N*N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                firstMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        initMyMap();

        while(M-->0){
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken())-1;
            s = Integer.parseInt(st.nextToken());

            blizzard();// 1. blizzard
            move();// 2. move
            while(bomb()){// 3. bomb
                move(); // 4. move
            }

            group();// 5. group
            myMap = newMap;
            print();
            System.out.println("-----------------");
        }
        System.out.println(ans);
    }
    static void group(){
        int newMapPos = 0;
        int cnt = 0;
        newMap = new int[N*N];
        int curpos = 0;
        for(int i=0;i<N;i++) for(int j=0;j<N;j++){
            curpos = i*N+j;
            if(myMap[curpos] == 0 || newMapPos+1 >= N*N) return;
            if(myMap[curpos+1] == myMap[curpos]){
                cnt++;
            }else{
                newMap[newMapPos] = cnt+1;
                newMap[newMapPos+1] = myMap[curpos];
                newMapPos += 2;
                cnt = 0;
            }
        }
        if(cnt > 0){
            newMap[newMapPos] = cnt+1;
            newMap[newMapPos+1] = myMap[curpos];
        }

    }

    static boolean bomb(){
        int cnt = 0;
        boolean ret = false;
        for(int i=0;i<N;i++) for(int j=0;j<N;j++){
            int curpos = i*N+j;
            if(myMap[curpos] == 0){
                cnt = 0;
                continue;
            }
            if(myMap[curpos+1] == myMap[curpos]){
                cnt++;
            }else{
                if(cnt >= 3){
                    ans += (cnt+1)*myMap[curpos];
                    myMap[curpos] = 0;
                    while(cnt-->0){
                        myMap[--curpos] = 0;
                    }
                    ret = true;
                }
                cnt = 0;
            }
        }
        return ret;
    }

    static void blizzard(){
        int pos = firstPos[d];
        myMap[pos] = 0;
        pos += firstDist[d];
        for(int i=0;i<s-1;i++){
            pos += i*(8+i*2);
            if(pos >= N*N) return;
            myMap[pos] = 0;
        }
    }

    static void move(){
        for(int i=0;i<N;i++) for(int j=0;j<N;j++){
            int curpos = i*N+j;
            while(curpos-1 >= 0 && myMap[curpos-1] == 0){
                swap(curpos-1,curpos);
                curpos--;
            }
        }
    }

    static void swap(int i,int j){
        int tmp = myMap[i];
        myMap[i] = myMap[j];
        myMap[j] = tmp;
    }

    static void print(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.println(myMap[i*N+j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void initMyMap(){
        // dir = 2,1,3,0;
        int[] dirs = {2,1,3,0};
        int y = N/2;
        int x = N/2-1;
        int dir = 0;
        int curs = 1;
        int curscnt = 0;
        int totalcnt = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++){
                if(j==N-1 && i == N-1) break;
                myMap[i*N+j] = firstMap[y][x];

                totalcnt++;
                curscnt++;
                if(totalcnt == curs*2){
                    curs++;
                    curscnt = totalcnt = 0;
                    dir = (dir+1)%4;
                }
                if(curscnt == curs){
                    curscnt = 0;
                    dir = (dir+1)%4;
                }
                y += dy[dirs[dir]];
                x += dx[dirs[dir]];

            }
        }
    }
}
