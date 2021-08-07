# 10250번 ACM 호텔
[문제 보러가기](https://www.acmicpc.net/problem/10250)

## 🅰 설계

### 1. 어떤 방법을 사용할 것인가?
1. Bruteforce  
   이중 포문으로 모든 층을 한번씩 방문하여 돌면서 `cnt` 수를 갱신해나가다가 `N`과 `cnt`가 같아지는 순간의 층의 방 번호를 출력하면 된다.  
   ```java
    static void solveByBruteforce() throws IOException{
        int cnt = 0;
        for(int j=1;j<=W;j++){
            for(int i=1;i<=H;i++){
                cnt++;

                if(cnt == N){
                    if(j >= 10){
                        System.out.println(i+""+j);
                    }else{
                        System.out.println(i+"0"+j);
                    }
                    return;
                }
            }
        }
    }
   ```
2. 수학
   `N`번째 호실은 `H`의 몫과 나머지로 구할 수 있다.
   ```java
    static void solveByMath(){
        int hm = N/H+1;
        int hr = N%H;
        hm = hr == 0 ? hm-1 : hm;
        hr = hr == 0 ? H : hr;
        if(hm >= 10) System.out.println(hr+""+hm);
        else System.out.println(hr+"0"+hm);
    }
   ```
   호실의 번호가 0부터가 아니라 1부터 시작하므로 나머지가 0일때만 신경을 써 주면 된다.

### 2. 전체 코드

```java
import java.io.*;
import java.util.*;

public class ACM호텔 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int T,H,W,N;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        while(T-- > 0) solve();
    }

    static void solve() throws IOException{
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
//        solveByBruteforce();
        solveByMath();
    }

    static void solveByBruteforce() throws IOException{
        int cnt = 0;
        for(int j=1;j<=W;j++){
            for(int i=1;i<=H;i++){
                cnt++;

                if(cnt == N){
                    if(j >= 10){
                        System.out.println(i+""+j);
                    }else{
                        System.out.println(i+"0"+j);
                    }
                    return;
                }
            }
        }
    }

    static void solveByMath(){
        int hm = N/H+1;
        int hr = N%H;
        hm = hr == 0 ? hm-1 : hm;
        hr = hr == 0 ? H : hr;
        if(hm >= 10) System.out.println(hr+""+hm);
        else System.out.println(hr+"0"+hm);
    }
}

```

## ✅ 후기
예전에 이 문제에 조금 애를 먹었던 기억이 있었다.  
그 때는 수학적으로 계산하는 방법에 메달려서 여러번 틀리고 고쳤었다.  
이번에 다시 풀어보니 Bruteforce로 시간 내에 쉽게 풀리는 것을 깨닫고 먼저 Bruteforce를 시도하여 답을 맞췄다.  
항상 화려한 풀이보다 시간내에 정석적이고 빠르게 풀 수 있는 방법을 먼저 생각하는 것이 좋은것 같다.