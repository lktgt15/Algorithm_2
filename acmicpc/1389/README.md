# 1389번 케빈 베이컨의 6단계 법칙
[문제 보러가기](https://www.acmicpc.net/problem/1389)

## 🅰 설계
### 1. 어떤 방법을 사용할 것인가?
- 각 번호에서 다른 번호로 이동하는 데 얼마나 걸리는지 확인하는 법으로 크게 두 가지로 생각했다.
1. BFS
   - 1부터 N까지의 점에서 각각 시작해서 다른 모든 점까지 도착하는 시간을 계산하면 된다.
    ```java
        static int solveByBfs(){
        int ansval = Integer.MAX_VALUE;
        int ansnum = 0;
        for(int i=1;i<=N;i++){
            int tmp = bfs(i);
            if(ansval > tmp){
                ansval = tmp;
                ansnum = i;
            }
        }
        return ansnum;
    }

    static int bfs(int start){
        Queue<Integer> q = new ArrayDeque<>();
        int ret = 0;
        int level = 0;
        boolean[] chk = new boolean[N+1];
        chk[start] = true;
        q.offer(start);
        while(!q.isEmpty()){
            int size = q.size();
            while(size > 0){
                size--;
                int cur = q.poll();
                ret += level;
                for(int i=1;i<=N;i++){
                    if(!chk[i] && adjarr[cur][i]){
                        chk[i] = true;
                        q.offer(i);
                    }
                }
            }
            level++;
        }

        return ret;
    }
    ```
   - bfs로 각 정점이 꺼내질 때마다 그 정점을 방문한 단계수를 합하여 return한다.
2. Floyd-warshall
   - 1부터 N까지의 모든 점에 대한 최단 경로를 만드는 알고리즘으로 Floyd-warshall을 생각할 수 있다.
   ```java
        static int solveByFloyd(){
        for(int k=1;k<=N;k++){
            for(int i=1;i<=N;i++){
                for(int j=1;j<=N;j++){
                    if(i==j) continue;
                    if(distarr[i][j] > distarr[i][k] + distarr[k][j]){
                        distarr[i][j] = distarr[i][k] + distarr[k][j];
                    }
                }
            }
        }

        int ansval = Integer.MAX_VALUE;
        int ansnum = 0;
        for(int i=1;i<=N;i++){
            int tmpval = 0;
            for(int j=1;j<=N;j++){
                if(i==j) continue;
                tmpval += distarr[i][j];
            }
            if(ansval > tmpval){
                ansval = tmpval;
                ansnum = i;
            }
        }
        return ansnum;
    }
   ```
   - floyd로 1부터 N까지 모든 정점에서 시작하여 모든 정점에 대해 최단거리를 계산하고, 다시 1부터 N까지 해당 정점에서 다른 모든 정점으로 가는 비용을 합하면 된다.
## ✅ 후기
간단한 그래프 문제였지만 두 가지 방법으로 풀 수 있었던 것이 좋았던 문제였다.