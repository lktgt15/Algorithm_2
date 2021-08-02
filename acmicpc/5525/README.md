# 5525번 IOIOI
[문제 보러가기](https://www.acmicpc.net/problem/5525)

## 🅰 설계

### 1. 어떤 방법을 쓸 것인가?
- 가장 간단한 방법으로 모든 index 위치에서 Pn이 포함되어 있는지 확인하는 방법을 생각할 수 있다. 약 O((2N+1)\*M) = O(NM) 정도의 시간이 걸린다.
- 이 방법으로 서브태스크1은 통과할 수 있다. 그러나 서브태스크2는 O(NM) = O(10^6 * 10^6) = O(10^12) 정도가 걸려 위의 방법을 사용하여 통과할 수 없다.
- 다음으로 생각할 수 있는 것은 '확인한 지점을 다시 확인해야하나?'라는 의문을 가질 수 있다.
- 예를 들어 `IOIOIOI`라는 문자열이 있을 때 0번째 index`I`부터 `IOIOI`까지 확인했다면 그 후로는 5번째 이전의 index는 확인할 필요가 없다. 이후에 `OI`가 붙는지 아닌지만 확인하면 된다.
1. `OI`가 붙는다 : 그대로 다음에도 `OI`가 뒤에 붙어지는지 확인하면 된다.
2. `OI`가 붙지 않는다 : 이 경우 이전의 `IOIOI...`들은 어디서 시작하던지 원하는 `IOIOI...`문자열을 만들 수 없게 된다. 그러므로 다시 새롭게 현재 index부터 `IOI...`를 만들어 가면 된다.  

### 2. 전체 코드
```java
import java.util.*;
import java.io.*;

public class IOIOI {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N,M;
    static String in;
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        in = br.readLine();

        int streak = 0;
        int ans = 0;
        for(int i=0;i<M;i++){
            if(streak == 0){
                if(in.charAt(i) == 'I' && isValid(i+1) && in.charAt(i+1) == 'O' && isValid(i+2) && in.charAt(i+2) == 'I'){
                    streak = 1;
                    i+=2;
                    if(N == 1) ans++;
                }
            }else{
                if(in.charAt(i) == 'O' && isValid(i+1) && in.charAt(i+1) == 'I'){
                    streak++;
                    i++;
                    if(streak >= N) ans++;
                }else{
                    streak = 0;
                    i--;
                }
            }
        }
        System.out.println(ans);
    }

    static boolean isValid(int i){
        return i<M;
    }
}

```

## ✅ 후기
풀고나면 간단한 문자열 문제지만 딱히 알고리즘을 쓰지 않고도 여러가지 생각을 해봐야 하는 좋은 문제였다.