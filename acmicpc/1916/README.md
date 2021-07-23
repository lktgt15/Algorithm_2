# 1916번 최소 비용 구하기
[문제 보러가기](https://www.acmicpc.net/problem/1916)

## 🅰 설계

### 1. 최단거리 알고리즘
- A에서 B로 가는 가장 간단한 형식의 최단거리 문제인 것을 알 수 있다. 간단하게 어떤 알고리즘을 써야 하는지 생각해 봤다.  
- 일단 조건을 확인한다.  
```
도시의 개수 N(1 <= N <= 1,000)
버스의 개수 M(1 <= M <= 100,000)
버스의 비용 Cost(0 <= Cost <= 100,000)
```

1. BFS
	- 최단거리 알고리즘 중 Edge의 Cost가 1인 경우에만 사용할 수 있다. 그러므로 사용할 수 없다.
2. Floyd
	- Floyd의 시간복잡도는 O(V^3)이다. N이 최대 1,000이므로 input이 최대치로 들어올 경우 1,000,000,000번의 연산을 진행해야 한다. 시간초과로 사용할 수 없다.
3. Bellman-Ford
	- Bellman-Ford의 시간복잡도는 O(VE)이다. 이 문제의 시간제한은 0.5초 이고 O(VE) = O(100,000,000)이 된다. 보통 100,000,000의 연산을 1초로 두고 계산하며 이보다 빠른 Dijkstra 알고리즘이 있으므로 사용하지 않는다.
4. Dijkstra
	- Dijkstra의 시간복잡도는 O(ElogV)이다. log1,000 을 10으로 놓고 계산하면 O(ElogV) = O(1,000,000)이 되며 시간안에 충분히 계산이 가능하다. 음수 간선도 없으므로 Dijkstra 알고리즘을 사용한다.

### 2. 전체 코드

```java
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

```

## ✅ 후기
가장 간단한 형태의 최단거리 문제지만 이런 기본 문제들도 주기적으로 풀어주는 것이 숙련도 향상에 도움이 되는듯 하다.