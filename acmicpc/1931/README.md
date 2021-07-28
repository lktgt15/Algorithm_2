# 1931번 회의실 배정
[문제 보러가기](https://www.acmicpc.net/problem/1931)

## 🅰 설계
### 1. 어떤 방법을 사용할 것인가?

- **시작 시간**과 **종료 시간**에 대해서 생각해 보자. 최대한 많은 회의 수를 정하는 데는 회의가 얼마나 긴지 짧은지는 상관이 없다.  
  - (0,100),(7,9),(4,11)의 회의들이 있을 때를 생각해 보자.  
  - **시작 시간**을 기준으로 : 시작 시간이 가장 빠른 첫 번째 회의는 빨리 시작하지만 100에 끝나게 되어 이 회의를 선택할 경우 (0,100) 사이의 회의는 모두 쓸 수 없게 된다. 회의의 시작 시간이 아무리 빨라도 종료 시간이 늦으면 다른 회의를 선택하는 것이 좋다.  
  - **종료 시간**을 기준으로 : 종료 시간이 가장 빠른 두 번째 회의를 선택하면 (7,9) 사이의 회의를 선택할 수 없다. 종료 시간이 빠른 대로 회의를 배정하면 그 종료 시간 이후에 시작하는 회의를 선택할 수 있다.

- **종료 시간이 같고, 시작 시간이 다를 때** : 사실 시작 시간이 어떤 때인지는 상관 없다고 생각했다. 그러나 이런 예가 있다.
  - (6,6),(3,6),(1,2)
  - 위에서 종료 시간이 빠른대로만 정렬하면 (1,2),(6,6),(3,6)이 된다. 문제의 조건에서 시작 시간과 종료 시간이 같을 수 있고, 종료 시간에 회의를 시작 할 수 있으므로 (1,2),(3,6)을 순서대로 선택하면 (6,6) 까지 3가지 방법이 답이 되지만, (1,2),(6,6)을 선택하면 다음의 (3,6)은 선택할 수가 없게 된다.  
  - 그러므로 종료 시간이 같다면 시작 시간이 빠른 회의를 선택해야 한다.  

### 2. 전체 코드
```java
import java.util.*;
import java.io.*;

public class 회의실배정 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        Meeting[] meetings = new Meeting[N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int s,e;
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            meetings[i] = new Meeting(s,e);
        }
        Arrays.sort(meetings);
        int ans = 0;
        int lastEnd = -1;

        for(int i=0;i<N;i++){
            if(lastEnd <= meetings[i].s){
                lastEnd = meetings[i].e;
                ans++;
            }
        }
        System.out.println(ans);
    }

    static class Meeting implements Comparable<Meeting>{
        int s,e;
        public Meeting(int s,int e){
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Meeting o) {
            if(this.e == o.e)
                return Integer.compare(this.s,o.s);
            return Integer.compare(this.e,o.e);
        }
    }
}

```


## ✅ 후기
가장 기본적인 그리디 문제라 종료 시간을 기준으로 정렬하는 것은 알고 있었다. 그러나 문제의 조건때문에 추가적으로 시작 시간을 기준으로도 정렬해주어야 한다는 것을 놓쳤었다. 다시 한번 문제의 조건을 꼼꼼히 체크해보는 연습을 해야 할 것 같다.