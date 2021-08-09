# 9019번 DSLR
[문제 보러가기](https://www.acmicpc.net/problem/9019)

## 🅰 설계

### 1. 어떤 방법을 사용할 것인가?
String 연산이 반복되고 시간 제한이 6초인걸 보니 연산을 줄일 수 있는 부분은 모두 최소화해야겠다고 생각했다.  

최소한의 명령어는 BFS로 생각할 수 있고 그 명령어 나열은 String을 붙여서 구현할 수 있다.  

명령어의 나열에서 처음에 StringBuilder를 생각했는데 어차피 Queue에 들어가는 각 상태마다 새로운 객체가 필요해서 그냥 String으로 사용할 수 밖에 없었다.  

실제 값 비교는 String이 아닌 int로 연산하여 시간을 줄이고자 했다.

### 2. DSLR 코드

```java
// D
int dval = (cur.val*2)%10000;
if(!chk[dval]){
    chk[dval] = true;
    q.offer(new Point(cur.state+'D',dval));
}

// S
int sval = cur.val == 0 ? 9999 : cur.val-1;
if(!chk[sval]){
    chk[sval] = true;
    q.offer(new Point(cur.state+'S',sval));
}

// L
int lval = tol(cur.val);
if(!chk[lval]){
    chk[lval] = true;
    q.offer(new Point(cur.state+'L',lval));
}

// R
int rval = tor(cur.val);
if(!chk[rval]){
    chk[rval] = true;
    q.offer(new Point(cur.state+'R',rval));
}
```
각 명령어마다 int값을 계산하였다.

```java
static int tol(int val){
    return (val/1000)+(val%1000)*10;
}

static int tor(int val){
    return val/10+(val%10)*1000;
}
```
L과 R연산은 int값을 나누기,곱하기,나머지,더하기를 적절히 활용하면 연산을 줄일 수 있다.

### 3. 전체 코드
```java
import java.io.*;
import java.util.*;

public class DSLR {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int T;
    static StringBuilder ansbuilder = new StringBuilder();
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        while(T-- > 0) solve();
        System.out.println(ansbuilder);
    }

    static void solve() throws IOException{
        boolean[] chk = new boolean[10000];
        st = new StringTokenizer(br.readLine());

        Queue<Point> q = new ArrayDeque<>();
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        q.offer(new Point("",start));

        while(!q.isEmpty()){
            Point cur = q.poll();

            if(cur.val == end){
                System.out.println(cur.state);
                return;
            }

            // D
            int dval = (cur.val*2)%10000;
            if(!chk[dval]){
                chk[dval] = true;
                q.offer(new Point(cur.state+'D',dval));
            }

            // S
            int sval = cur.val == 0 ? 9999 : cur.val-1;
            if(!chk[sval]){
                chk[sval] = true;
                q.offer(new Point(cur.state+'S',sval));
            }

            // L
            int lval = tol(cur.val);
            if(!chk[lval]){
                chk[lval] = true;
                q.offer(new Point(cur.state+'L',lval));
            }

            // R
            int rval = tor(cur.val);
            if(!chk[rval]){
                chk[rval] = true;
                q.offer(new Point(cur.state+'R',rval));
            }
        }
    }

    static int tol(int val){
        return (val/1000)+(val%1000)*10;
    }

    static int tor(int val){
        return val/10+(val%10)*1000;
    }

    static class Point{
        String state;
        int val;
        public Point(String state,int val){
            this.state = state;
            this.val = val;
        }
    }
}

```

## ✅ 후기
처음에 Point의 val을 String으로 처리하려고 했다.  

String으로 처리하려고 보니 연산도 복잡해지고 더 오래걸릴 것 같아서 int로 계산하는 방법을 사용했다.