import java.util.*;
import java.io.*;

public class 트리의부모찾기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] parent;
    static boolean[] chk;
    static List<Integer>[] path;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        path = new List[N+1];
        parent = new int[N+1];
        chk = new boolean[N+1];
        for(int i=1;i<=N;i++) path[i] = new ArrayList<>();

        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int a,b;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            path[a].add(b);
            path[b].add(a);
        }

        find(1);

        StringBuilder sb = new StringBuilder();
        for(int i=2;i<=N;i++){
            sb.append(parent[i]).append('\n');
        }
        System.out.print(sb);
    }

    static void find(int root){
        chk[root] = true;
        for(int nxt : path[root]){
            if(!chk[nxt]){
                parent[nxt] = root;
                find(nxt);
            }
        }
    }
}
