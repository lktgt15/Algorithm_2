# 14569번 시간표 짜기
[문제 보러가기](https://www.acmicpc.net/problem/14569)

## 🅰 설계

### 1. 어떤 방법을 사용할 것인가?

1. BruteForce  
모든 과목의 수업 시간을 하나씩 체크하면서 그 시간이 비는지 체크한다. 이 경우 O(N\*k\*M) = O(500,000,000)으로 시간 제한을 넘긴다. 다른 방법이 필요하다.  

2. BitMask  
결국 어떤 과목과 학생의 시간을 탐색하는 방법의 최적화가 필요하다.  
시간이 1~50 사이로 주어지므로, 64Bit 자료형을 사용하여 1~50 사이의 주어지는 시간의 Bit를 켜두고 &연산자를 활용하면 50번의 비교를 1번의 비교로 줄일 수 있다.

### 2. BitMask 코드
```java
N = Integer.parseInt(br.readLine());
long[] lectures = new long[N];
for(int i=0;i<N;i++){
    st = new StringTokenizer(br.readLine());
    k = Integer.parseInt(st.nextToken());
    for(int j=0;j<k;j++){
        lectures[i] |= 1L << Long.parseLong(st.nextToken());
    }
}
```
수업의 개수만큼 1~50의 시간을 `lecutres[j]`에 Bit를 켠다.

```java
for(int i=0;i<M;i++){
    st = new StringTokenizer(br.readLine());
    k = Integer.parseInt(st.nextToken());
    long student = 0;
    int subans = 0;
    for(int j=0;j<k;j++){
        student |= 1L << Long.parseLong(st.nextToken());
    }
    for(int j=0;j<N;j++){
        if((student & lectures[j]) == lectures[j]){
            subans++;
        }
    }
    sb.append(subans).append("\n");
}
```
학생의 개수만큼 다시 똑같이 1~50의 시간을 `student`에 Bit를 켠다.  

그리고 앞서 Bit를 켜 뒀던 수업들을 `&`연산으로 비교하여 `lecture[j]`가 그대로 남는다면 그 수업시간은 학생이 모두 가능하다는 뜻이 된다.

### 3. 전체 코드

```java
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N,M,k;
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        long[] lectures = new long[N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            for(int j=0;j<k;j++){
                lectures[i] |= 1L << Long.parseLong(st.nextToken());
            }
        }
        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            long student = 0;
            int subans = 0;
            for(int j=0;j<k;j++){
                student |= 1L << Long.parseLong(st.nextToken());
            }
            for(int j=0;j<N;j++){
                if((student & lectures[j]) == lectures[j]){
                    subans++;
                }
            }
            sb.append(subans).append("\n");
        }
        System.out.print(sb.toString());
    }
}

```

## ✅ 후기
// 새롭게 알게되거나 공유해서 알게된 점
// 고생한 점