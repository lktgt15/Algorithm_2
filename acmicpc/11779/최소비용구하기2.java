import java.util.*;
import java.io.*;

public class 최소비용구하기2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] dist,prev;
    static int n,m,start,end;
    static final int INF = Integer.MAX_VALUE;
    static List<Point>[] adjList;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dist = new int[n+1];
        Arrays.fill(dist,INF);

        prev = new int[n+1];
        adjList = new List[n+1];

        for(int i=1;i<=n;i++) adjList[i] = new ArrayList<>();

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a,b,c;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            adjList[a].add(new Point(b,c));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.dist));

        pq.add(new Point(start,0));
        dist[start] = 0;
        while(!pq.isEmpty()){
            Point cur = pq.poll();
            if(dist[cur.p] < cur.dist) continue;
            if(cur.p == end) break;

            for(Point nxtPoint : adjList[cur.p]){
                int nxtdist = cur.dist+nxtPoint.dist;
                if(dist[nxtPoint.p] > nxtdist){
                    dist[nxtPoint.p] = nxtdist;
                    prev[nxtPoint.p] = cur.p;
                    pq.add(new Point(nxtPoint.p,nxtdist));
                }
            }
        }

        List<Integer> ansList = new ArrayList<>();
        int tracking = end;
        ansList.add(end);
        while(prev[tracking] > 0){
            tracking = prev[tracking];
            ansList.add(tracking);
        }
        System.out.println(dist[end]);
        System.out.println(ansList.size());
        StringBuilder sb = new StringBuilder();
        for(int i=ansList.size()-1;i>=0;i--){
            sb.append(ansList.get(i)).append(" ");
        }
        System.out.println(sb);
    }

    static class Point{
        int p,dist;
        public Point(){}
        public Point(int p,int dist){
            this.p = p;
            this.dist = dist;
        }
    }
}
