import java.util.*;
import java.io.*;

public class 케빈베이컨의6단계법칙 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N,M;
    static boolean[][] adjarr;
    static int[][] distarr;
    static int INF = 987654321;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjarr = new boolean[N+1][N+1];
        distarr = new int[N+1][N+1];

        for(int i=1;i<=N;i++) Arrays.fill(distarr[i],INF);
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a,b;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            adjarr[a][b] = adjarr[b][a] = true;
            distarr[a][b] = distarr[b][a] = 1;
        }

//        System.out.println(solveByBfs());
        System.out.println(solveByFloyd());
    }

    static int solveByFloyd(){
        for(int k=1;k<=N;k++){
            for(int i=1;i<=N;i++){
                for(int j=1;j<=N;j++){
                    if(i==j) continue;
                    if(distarr[i][j] > distarr[i][k] + distarr[k][j]){
                        distarr[i][j] = distarr[i][k] + distarr[k][j];
                    }
                }
            }
        }

        int ansval = Integer.MAX_VALUE;
        int ansnum = 0;
        for(int i=1;i<=N;i++){
            int tmpval = 0;
            for(int j=1;j<=N;j++){
                if(i==j) continue;
                tmpval += distarr[i][j];
            }
            if(ansval > tmpval){
                ansval = tmpval;
                ansnum = i;
            }
        }
        return ansnum;
    }

    static int solveByBfs(){
        int ansval = Integer.MAX_VALUE;
        int ansnum = 0;
        for(int i=1;i<=N;i++){
            int tmp = bfs(i);
            if(ansval > tmp){
                ansval = tmp;
                ansnum = i;
            }
        }
        return ansnum;
    }

    static int bfs(int start){
        Queue<Integer> q = new ArrayDeque<>();
        int ret = 0;
        int level = 0;
        boolean[] chk = new boolean[N+1];
        chk[start] = true;
        q.offer(start);
        while(!q.isEmpty()){
            int size = q.size();
            while(size > 0){
                size--;
                int cur = q.poll();
                ret += level;
                for(int i=1;i<=N;i++){
                    if(!chk[i] && adjarr[cur][i]){
                        chk[i] = true;
                        q.offer(i);
                    }
                }
            }
            level++;
        }

        return ret;
    }
}
