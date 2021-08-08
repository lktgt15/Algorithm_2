import java.io.*;
import java.util.*;

public class 서강그라운드 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n,m,r;
    static int[] items;
    static int[] dist;
    static List<path>[] paths;
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        paths = new List[n+1];
        items = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            paths[i] = new ArrayList<>();
            items[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<r;i++){
            st = new StringTokenizer(br.readLine());
            int a,b,c;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            paths[a].add(new path(b,c));
            paths[b].add(new path(a,c));
        }

        PriorityQueue<path> pq = new PriorityQueue<>((p1,p2) -> p1.cost - p2.cost);
        int ans = 0;
        for(int i=1;i<=n;i++){
            pq.clear();
            dist = new int[n+1];
            Arrays.fill(dist,INF);
            dist[i] = 0;

            pq.add(new path(i,0));
            int subans = 0;
            while(!pq.isEmpty()){
                path cur = pq.poll();
                if(dist[cur.point] < cur.cost) continue;
                if(cur.cost > m) break;

                subans += items[cur.point];
                for(path nxt : paths[cur.point]){
                    int nxtcost = nxt.cost+cur.cost;
                    if(dist[nxt.point] > nxtcost){
                        dist[nxt.point] = nxtcost;
                        pq.add(new path(nxt.point,nxtcost));
                    }
                }
            }
            ans = Math.max(ans,subans);
        }
        System.out.println(ans);
    }

    static class path{
        int point,cost;
        public path(int point,int cost){
            this.point = point;
            this.cost = cost;
        }
    }
}
