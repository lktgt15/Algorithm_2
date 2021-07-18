import java.util.*;
import java.io.*;

public class 톱니바퀴 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] state = new int[4][8]; // N = 0, S = 1
    static int k;
    public static void main(String[] args) throws IOException {
        for(int i=0;i<4;i++){
            String in = br.readLine();
            for(int j=0;j<8;j++) state[i][j] = in.charAt(j)-'0';
        }
        k = Integer.parseInt(br.readLine());
        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken());

            rotate(num,dir);
        }

        int ans = 0;
        for(int i=0;i<4;i++){
            ans += state[i][0]*pow(2,i);
        }
        System.out.println(ans);
    }

    static int pow(int n,int k){
        int ret = 1;
        for(int i=0;i<k;i++) ret *= n;
        return ret;
    }

    static private void rotate(int num,int dir){
        int[][] nextState = new int[4][8];
        boolean chk[] = new boolean[4];
        chk[num] = true;

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(num);
        q.offer(dir);
        while(!q.isEmpty()){
            int curnum = q.poll();
            int curdir = q.poll();
            for(int i=0;i<8;i++){
                nextState[curnum][i] = state[curnum][(i+(-1*curdir))&7];
            }
            // state[i][2] = Right, state[i][6] = Left
            if(curnum+1 < 4 && (state[curnum][2]^state[curnum+1][6]) == 1 && !chk[curnum+1]){
                q.offer(curnum+1);
                q.offer(curdir*-1);
                chk[curnum+1] = true;
            }
            if(curnum-1 >= 0 && ((state[curnum][6]^state[curnum-1][2]) == 1 && !chk[curnum-1])){
                q.offer(curnum-1);
                q.offer(curdir*-1);
                chk[curnum-1] = true;
            }
        }

        for(int i=0;i<4;i++){
            if(chk[i]){
                for(int j=0;j<8;j++) state[i][j] = nextState[i][j];
            }
        }
    }
}
