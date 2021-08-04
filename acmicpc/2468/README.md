# 2468번 안전 영역
[문제 보러가기](https://www.acmicpc.net/problem/2468)

## 🅰 설계

### 1. 어떤 방법을 사용할 것인가?
- 맵이 주어지고 4방탐색을 하는 간단한 방법으로 DFS,BFS가 있다. 이 문제의 경우에는 최단거리 측정이 필요 없고 반복되는 탐색을 재귀로 쉽게 구현이 가능하므로 DFS를 사용하기로 한다.

### 2. DFS 코드

```java
// main ...

for(int i=1;i<=max_height;i++){
    ans = Math.max(ans,solveByDfs(i));
}

// main ...

static int solveByDfs(int rain){
    chk = new boolean[N+2][N+2];
    int subans = 0;
    for(int i=1;i<=N;i++) for(int j=1;j<=N;j++){
        if(map[i][j] > rain && !chk[i][j]){
            dfs(i,j,rain);
            subans++;
        }
    }
    return subans;
}

static void dfs(int y,int x,int rain){
    chk[y][x] = true;
    for(int i=0;i<4;i++){
        int ny = y+dy[i];
        int nx = x+dx[i];
        if(map[ny][nx] > rain && !chk[ny][nx]){
            dfs(ny,nx,rain);
        }
    }
}
```
- `solveByDfs()`에서 높이를 정해주고, 모든 map을 돌면서 체크되지 않았으면서 정해진 높이(rain)보다 높은 부분부터 `dfs()` 를 돌며 체크한다.
- `dfs()`에서 방문 표시를 해 주고, 4방 탐색을 시도하며 연결된 모든 부분을 탐색한다.

### 3. 전체 코드

```java
import java.util.*;
import java.io.*;

public class 안전영역 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int max_height;
    static int N;
    static int ans = 1;
    static int[][] map;
    static int[] dy={1,0,-1,0}, dx={0,1,0,-1};
    static boolean[][] chk;
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        map = new int[N+2][N+2];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                max_height = Math.max(max_height,map[i][j]);
            }
        }

        for(int i=1;i<=max_height;i++){
            ans = Math.max(ans,solveByDfs(i));
        }

        System.out.println(ans);
    }

    static int solveByDfs(int rain){
        chk = new boolean[N+2][N+2];
        int subans = 0;
        for(int i=1;i<=N;i++) for(int j=1;j<=N;j++){
            if(map[i][j] > rain && !chk[i][j]){
                dfs(i,j,rain);
                subans++;
            }
        }
        return subans;
    }

    static void dfs(int y,int x,int rain){
        chk[y][x] = true;
        for(int i=0;i<4;i++){
            int ny = y+dy[i];
            int nx = x+dx[i];
            if(map[ny][nx] > rain && !chk[ny][nx]){
                dfs(ny,nx,rain);
            }
        }
    }
}

```

## ✅ 후기
4방 탐색을 여러번 하면서 답을 갱신하는 연습하기 좋은 탐색 문제였다.