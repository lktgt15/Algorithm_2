# 14938번 서강 그라운드
[문제 보러가기](https://www.acmicpc.net/problem/14938)

## 🅰 설계

### 1. 어떤 방법을 사용할 것인가?
정점, 길의 개수가 적기 때문에 플로이드,다익스트라 모두 쓸 수 있다.  

### 2. 전체 코드
```java
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

```
양방향 간선이기 때문에 a와 b의 인접 리스트 모두에 길을 추가한다. 

1~n까지 모든 정점에서 시작하여 제한 거리 m을 추가한 일반적인 다익스트라 코드를 돌리면 답이 된다.

## ✅ 후기
최단거리 알고리즘인 다익스트라,플로이드 와샬을 생각하고 다익스트라만을 사용했는데 플로이드도 한번 써 보면 좋을것 같다.