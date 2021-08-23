import java.io.*;
import java.util.*;

public class 기차가어둠을헤치고은하수를 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static boolean[] chk = new boolean[(1<<21)];
    static int[] trains;
    static int N,M;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trains = new int[N];

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a,b,c;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken())-1;

            switch (a){
                case 1:
                    c = Integer.parseInt(st.nextToken())-1;
                    trains[b] |= (1<<c);
                    break;
                case 2:
                    c = Integer.parseInt(st.nextToken())-1;
                    trains[b] &= ~(1<<c);
                    break;
                case 3:
                    trains[b] <<= 1;
                    trains[b] &= (1<<20)-1;
                    break;
                case 4:
                    trains[b] >>= 1;
                    trains[b] &= (1<<20)-1;
                    break;
            }
        }

        int ans = 0;
        for(int i=0;i<N;i++){
            if(!chk[trains[i]]) ans++;
            chk[trains[i]] = true;
        }
        System.out.println(ans);
    }
}
