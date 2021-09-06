import java.util.*;
import java.io.*;

public class 숨바꼭질 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static boolean[] chk;
    public static void main(String[] args) throws IOException {
        int N,K;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        chk = new boolean[200002];

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);
        q.offer(0);
        chk[N] = true;
        while(!q.isEmpty()){
            int cur = q.poll();
            int time = q.poll();
            if(cur == K){
                System.out.println(time);
                return;
            }

            if(cur+1 <= 200000 && !chk[cur+1]){
                q.offer(cur+1);
                q.offer(time+1);
                chk[cur+1] = true;
            }
            if(cur-1 >= 0 && !chk[cur-1]){
                q.offer(cur-1);
                q.offer(time+1);
                chk[cur-1] = true;
            }
            if(cur*2 <= 200000 && !chk[cur*2]){
                q.offer(cur*2);
                q.offer(time+1);
                chk[cur*2] = true;
            }
        }
    }
}
