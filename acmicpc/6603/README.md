# 6603번 로또
[문제 보러가기](https://www.acmicpc.net/problem/6603)

## 🅰 설계

### 1. 어떤 방법을 사용할 것인가?
- 주어지는 k개의 숫자 중에 6개를 선택하여 만들 수 있는 모든 방법을 구하여야 한다.
- 이는 조합으로 해결할 수 있고, 조합을 만드는 코드는 다음과 같다.

### 2. 조합 코드
```java
static void recursive(int start,int idx){
    if(idx == 6){
        for(int i=0;i<6;i++){
            sb.append(ansArr[i]).append(" ");
        }
        sb.append("\n");
        return;
    }
    for(int i=start;i<k;i++){
        ansArr[idx] = baseArr[i];
        recursive(i+1,idx+1);
    }
}
```
- idx가 6이면 0~5까지의 idx에 값이 저장되어 있다는 뜻이므로 종료 조건이 된다. 0~5까지의 값들을 StringBuilder에 append하고 return하면 된다.
- idx가 6보다 작으면 6개 숫자를 뽑지 못했다는 뜻이다. start부터 시작하여 k까지 모든 수를 한번씩 그 자리에 넣어보고 재귀로 다음 숫자부터 시작하도록 recursive(i+1,idx+1)을 호출한다.

### 3. 전체 코드
```java
import java.util.*;
import java.io.*;

public class 로또 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] baseArr = new int[13];
    static int[] ansArr = new int[6];
    static int k;
    public static void main(String[] args) throws IOException{
        while(true){
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if(k == 0) break;

            for(int i=0;i<k;i++){
                baseArr[i] = Integer.parseInt(st.nextToken());
            }

            recursive(0,0);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void recursive(int start,int idx){
        if(idx == 6){
            for(int i=0;i<6;i++){
                sb.append(ansArr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i=start;i<k;i++){
            ansArr[idx] = baseArr[i];
            recursive(i+1,idx+1);
        }
    }
}

```

## ✅ 후기
간단한 조합문제지만 예전에 recursive 재귀를 recursive(i,idx+1)과 같은 식으로 돌리는 실수를 하여 무한 루프를 돈 적이 있다. 실수하기 쉬운 부분이므로 조심해야 한다.