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