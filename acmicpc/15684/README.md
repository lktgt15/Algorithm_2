# 15684번 
[문제 보러가기](https://www.acmicpc.net/problem/15684)

## 🅰 설계

### 1. 입력
```java
static int N, M, H, ans;
static boolean[][] line;

//--------------

st = new StringTokenizer(br.readLine());
N = Integer.parseInt(st.nextToken());
M = Integer.parseInt(st.nextToken());
H = Integer.parseInt(st.nextToken());

line = new boolean[H+1][N+1];

for(int i=0;i<M;i++){
    st = new StringTokenizer(br.readLine());
    int a, b;
    a = Integer.parseInt(st.nextToken());
    b = Integer.parseInt(st.nextToken());
    line[a-1][b-1] = true;
}
```
- `line[i][j]`는 (i,j)지점에서 오른쪽으로 가로선이 있다는 뜻이다.  

### 2. 사다리 놓기

```java
static void solve(int h, int n, int k) {
    if(ans > k && checkComplete()) {
        ans = k;
        return;
    }

    if(k == 3) return;
    for(int j=n;j<N-1;j++) {
        if(isValidLine(h, j)) {
            line[h][j] = true;
            solve(h,j+2,k+1);
            line[h][j] = false;
        }
    }
    for(int i=h+1;i<H;i++) for(int j=0;j<N-1;j++) {
        if(isValidLine(i, j)) {
            line[i][j] = true;
            solve(i, j+2, k+1);
            line[i][j] = false;
        }
    }
}
```
- `solve(h,n,k)`는 (h,n)에서 시작하여 k개의 가로선을 이미 그었을 때  
  1. 현재 상태에서 `ans`보다 `k`가 작으면서 모든 세로선이 마지막에 자신의 위치로 끝나면 `ans`를 갱신하고 return한다.
  2. 위 조건에 맞지 않으면 `k==3` 이면 더 가로선을 그을 수 없으므로 return시키고, 다음으로 가능한 위치들을 탐색하면서 가로선을 그을 수 있다면 놓아본다.  

### 2-1. 가로선을 그을 수 있는지 확인

```java
static boolean isValidLine(int i, int j) {
    if(j>0 && line[i][j-1]) return false;
    if(line[i][j+1]) return false;
    return !line[i][j];
}
```
- `isValidLine(i,j)`는 (i,j)위치에 가로선을 놓을 수 있는지 검사하는 함수이다.  
  1. (i,j-1)위치에 가로선이 있으면 (i,j)에 놓을 수 없다. 이 때 j가 **OutOfBoundsException**이 나지 않게 0 이상인 경우만 체크한다.  
  2. (i,j+1)위치에 가로선이 있으면 (i,j)에 놓을 수 없다. 이 때는 배열을 열의 개수+1만큼 더 선언해 주었고, j가 맨 오른쪽인 경우는 이 함수를 호출하지 않으므로 j의 범위를 체크할 필요는 없다.  
  3. 마지막으로 현재 위치(i,j)에 이미 가로선이 있는지 체크한다.

### 3. 답이 될 수 있는지 확인

```java
static boolean checkComplete() {
    for(int i=0;i<N;i++){
        int curHorizontalLine = i;
        for(int curVerticalLine=0;curVerticalLine<H;curVerticalLine++){
            if(line[curVerticalLine][curHorizontalLine]) {
                curHorizontalLine++;
            }else if(curHorizontalLine>0 && line[curVerticalLine][curHorizontalLine-1]){
                curHorizontalLine--;
            }
        }
        if(curHorizontalLine != i) return false;
    }
    return true;
}
```
- `checkComplete()`는 현재 사다리의 상태에서 모든 세로선의 시작점과 도착점이 같은지 체크한다.

### 4. 전체 코드

```java
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, H, ans;
    static boolean[][] line;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        line = new boolean[H+1][N+1];

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a, b;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            line[a-1][b-1] = true;
        }

        ans = 4;
        solve(0,0,0);
        System.out.print(ans == 4 ? -1 : ans);
    }

    static void solve(int h, int n, int k) {
        if(ans > k && checkComplete()) {
            ans = k;
            return;
        }

        if(k == 3) return;
        for(int j=n;j<N-1;j++) {
            if(isValidLine(h, j)) {
                line[h][j] = true;
                solve(h,j+2,k+1);
                line[h][j] = false;
            }
        }
        for(int i=h+1;i<H;i++) for(int j=0;j<N-1;j++) {
            if(isValidLine(i, j)) {
                line[i][j] = true;
                solve(i, j+2, k+1);
                line[i][j] = false;
            }
        }
    }

    static boolean isValidLine(int i, int j) {
        if(j>0 && line[i][j-1]) return false;
        if(line[i][j+1]) return false;
        return !line[i][j];
    }

    static boolean checkComplete() {
        for(int i=0;i<N;i++){
            int curHorizontalLine = i;
            for(int curVerticalLine=0;curVerticalLine<H;curVerticalLine++){
                if(line[curVerticalLine][curHorizontalLine]) {
                    curHorizontalLine++;
                }else if(curHorizontalLine>0 && line[curVerticalLine][curHorizontalLine-1]){
                    curHorizontalLine--;
                }
            }
            if(curHorizontalLine != i) return false;
        }
        return true;
    }
}

```

## ✅ 후기
예전에 풀었던 문제지만 지독했던 기억이 있었다. 시간초과가 나거나 로직의 오류를 찾기 힘든 점에서 꼼꼼하게 체크하는 연습하기 좋은 문제였다.