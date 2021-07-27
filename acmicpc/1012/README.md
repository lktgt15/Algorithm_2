# 1012번 유기농배추
[문제 보러가기](https://www.acmicpc.net/problem/1012)

## 🅰 설계
### 1. 어떤 방법을 사용할 것인가?

- map을 만들고 4방향을 탐색하며 연결된 부분을 다 체크하고 답의 value를 올리는 방식을 생각할 수 있다.  
  - BFS : Queue에 위치를 넣으면서 방문한 map을 0으로 바꿔주면 된다.  
  - DFS : 재귀로 방문한 map을 0으로 바꿔주면 된다.
- DFS를 사용하기로 한다. Queue에 넣고 빼는 작업 없이 재귀로 map의 방문 체크만 하면 되기 때문에 더 간단하게 풀 수 있다.

### 2. 방문 체크
```java
for(int i=1;i<=n;i++){
    for(int j=1;j<=m;j++){
        if(map[i][j]){
            ans++;
            check(i,j,map);
        }
    }
}

static void check(int y,int x,boolean[][] map){
    map[y][x] = false;
    for(int i=0;i<4;i++){
        int ny = y+dy[i];
        int nx = x+dx[i];
        if(map[ny][nx]) check(ny,nx,map);
    }
}
```
- 모든 map을 돌면서 (i,j)에 배추가 있다면 답의 value를 올리고 `check(i,j,map)`을 호출하여 연결된 모든 map을 탐색해 배추를 없앤다.  

### 3. 전체 코드
```java
import java.io.*;
import java.util.*;

public class 유기농배추 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] dy={1,-1,0,0}, dx={0,0,1,-1};
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++) solve();
    }


    static void solve() throws IOException{
        int m,n,k;
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[n+2][m+2];

        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int x,y;
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            map[y+1][x+1] = true;
        }

        int ans = 0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(map[i][j]){
                    ans++;
                    check(i,j,map);
                }
            }
        }
        System.out.println(ans);
    }

    static void check(int y,int x,boolean[][] map){
        map[y][x] = false;
        for(int i=0;i<4;i++){
            int ny = y+dy[i];
            int nx = x+dx[i];
            if(map[ny][nx]) check(ny,nx,map);
        }
    }
}

```


## ✅ 후기
간단한 DFS문제로 처음 배웠을 때 연습하기 좋은 문제였다. BFS와 DFS중 본인이 익숙한 방식으로 해결하면 될 듯 하다.