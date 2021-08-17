import java.util.*;
import java.io.*;

public class 집합의표현 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n,m;
    static int[] parent;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];

        Arrays.fill(parent,-1);

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a,b,c;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if(a == 0){
                merge(b,c);
            }else{
                b = find(b);
                c = find(c);
                if(b != c) sb.append("NO\n");
                else sb.append("YES\n");
            }
        }
        System.out.print(sb);
    }

    static int find(int a){
        if(parent[a] < 0) return a;
        return parent[a] = find(parent[a]);
    }

    static void merge(int a,int b){
        a = find(a);
        b = find(b);
        if(a == b) return;
        parent[b] = a;
    }
}
