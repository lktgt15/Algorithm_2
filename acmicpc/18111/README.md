# 18111번 마인크래프트
[문제 보러가기](https://www.acmicpc.net/problem/18111)

## 🅰 설계

### 1. 어떤 방법을 사용할 것인가?
땅의 높이가 최대 256이므로 0부터 256까지 높이를 한 번씩 설정해보고 그 높이로 평탄화하는 방법을 생각할 수 있다.  

맵의 크기는 최대 500이므로 250,000이다. **250,000\*257 = 64,250,000**이므로 1초 제한인 이 문제에서 모든 블럭을 한 번씩 돌면서 높이를 체크하는 방법을 쓰기는 불안하다.  

모든 맵을 돌지 않아도 땅의 최대 높이가 256이므로 각 높이에 해당하는 블록의 개수를 미리 저장해둘 수 있다.  

그리고 0~256까지 높이를 한 번씩 시도해보면서 현재 가지고있는 블럭들의 높이에 대해서 현재 설정한 높이를 만드는데 드는 비용을 계산할 수 있다.

### 2. 전체 코드
```java
import java.util.*;
import java.io.*;

public class 마인크래프트 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        int N,M,B;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        int height[] = new int[257];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                int cur = Integer.parseInt(st.nextToken());
                height[cur]++;
            }
        }

        int anstime = Integer.MAX_VALUE;
        int ansheight = 0;

        for(int i=256;i>=0;i--){
            int curB = B;
            int subtime = 0;
            for(int j=256;j>=0;j--){
                if(height[j] > 0){
                    if(j < i){
                        curB -= height[j]*(i-j);
                        subtime += height[j]*(i-j);
                    }else if(j > i){
                        curB += height[j]*(j-i);
                        subtime += height[j]*(j-i)*2;
                    }
                }
            }

            if(curB < 0) continue;

            if(anstime > subtime){
                anstime = subtime;
                ansheight = i;
            }
        }
        System.out.println(anstime + " " + ansheight);
    }
}

```

높이를 256부터 0까지 시도하므로 시간이 최소인 부분만 체크하면 같은 시간이 걸리는 높이에 대해서 항상 가장 큰 높이로 평탄화하는 행동이 답이 된다.  



## ✅ 후기
처음에 BruteForce 문제인줄 알고 각 맵을 모두 도는 방법을 생각했었는데 시간 복잡도를 계산해보고 다른 방법을 생각했다. 아이디어가 좋았던 문제였다.