import java.util.*;
import java.io.*;

public class 최소비용구하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] dist;
    static List<point>[] points;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int start,end,cost;

        dist = new int[N+1];
        points = new List[N+1];
        Arrays.fill(dist,INF);

        for(int i=1;i<=N;i++) points[i] = new ArrayList<>();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            points[start].add(new point(end,cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        PriorityQueue<point> pq = new PriorityQueue<>((point1, point2) -> Integer.compare(point1.cost, point2.cost));
        dist[start] = 0;
        pq.add(new point(start,0));
        while(!pq.isEmpty()){
            point cur = pq.poll();
            if(cur.cost > dist[cur.dest]) continue;
            if(cur.dest == end){
                System.out.println(dist[cur.dest]);
                return;
            }
            for(point nxt : points[cur.dest]){
                int nxtcost = nxt.cost + cur.cost;
                if(dist[nxt.dest] > nxtcost){
                    dist[nxt.dest] = nxtcost;
                    pq.add(new point(nxt.dest,dist[nxt.dest]));
                }
            }
        }
    }

    static class point {
        int dest,cost;
        public point(int dest, int cost){
            this.dest = dest;
            this.cost = cost;
        }
    }
}
