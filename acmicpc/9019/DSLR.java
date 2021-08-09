import java.io.*;
import java.util.*;

public class DSLR {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int T;
    static StringBuilder ansbuilder = new StringBuilder();
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        while(T-- > 0) solve();
        System.out.println(ansbuilder);
    }

    static void solve() throws IOException{
        boolean[] chk = new boolean[10000];
        st = new StringTokenizer(br.readLine());

        Queue<Point> q = new ArrayDeque<>();
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        q.offer(new Point("",start));

        while(!q.isEmpty()){
            Point cur = q.poll();

            if(cur.val == end){
                System.out.println(cur.state);
                return;
            }

            // D
            int dval = (cur.val*2)%10000;
            if(!chk[dval]){
                chk[dval] = true;
                q.offer(new Point(cur.state+'D',dval));
            }

            // S
            int sval = cur.val == 0 ? 9999 : cur.val-1;
            if(!chk[sval]){
                chk[sval] = true;
                q.offer(new Point(cur.state+'S',sval));
            }

            // L
            int lval = tol(cur.val);
            if(!chk[lval]){
                chk[lval] = true;
                q.offer(new Point(cur.state+'L',lval));
            }

            // R
            int rval = tor(cur.val);
            if(!chk[rval]){
                chk[rval] = true;
                q.offer(new Point(cur.state+'R',rval));
            }
        }
    }

    static int tol(int val){
        return (val/1000)+(val%1000)*10;
    }

    static int tor(int val){
        return val/10+(val%10)*1000;
    }

    static class Point{
        String state;
        int val;
        public Point(String state,int val){
            this.state = state;
            this.val = val;
        }
    }
}
