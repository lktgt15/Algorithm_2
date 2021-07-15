# 1918번 후위표기식
[문제 보러가기](https://www.acmicpc.net/problem/1918)

## 🅰 설계
- Stack을 배울 때 나오는 단골 문제다.

- 우선순위
0. '('
1. '+', '-'
2. '*', '/'
3. ')'

- **현재 연산자의 우선순위가 Stack에 있는 연산자의 우선순위보다 높거나 같으면** Stack에서 모두 꺼낸다.
- **현재 연산자의 우선순위가 Stack에 있는 연산자의 우선순위보다 낮으면** Stack에 넣는다.
- 마지막으로 남은 연산자를 붙여주면 끝

- 여기서 Stack에 넣는다는 것은, 현재 연산자보다 우선순위가 낮기 때문에 나중에 계산하겠다는 의미이다.
- 반대로 Stack에서 꺼낸다는 것은, 현재 연산자보다 우선순위가 높기 때문에 지금 계산하겠다는 의미이다.


```java
import java.io.*;
import java.util.*;

public class 후위표기식{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();

        for(int i=0;i<input.length();i++){
            char c = input.charAt(i);
            switch (c){
                case '+':
                case '-':
                    while(!stack.isEmpty() && stack.peek() != '('){
                        sb.append(stack.pop());
                    }
                    stack.push(c);
                    break;
                case '*':
                case '/':
                    while(!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')){
                        sb.append(stack.pop());
                    }
                    stack.push(c);
                    break;
                case '(':
                    stack.push(c);
                    break;
                case ')':
                    while(stack.peek() != '('){
                        sb.append(stack.pop());
                    }
                    stack.pop();
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        while(!stack.isEmpty()) sb.append(stack.pop());
        System.out.print(sb.toString());
    }
}
```

## ✅ 후기
- 항상 다시 알고리즘을 시작할 때 느끼는건데, 기억에 의존해서 풀려고 한다. 논리적으로 접근하고 검증해서 한 번에 맞추는 연습을 해야한다.