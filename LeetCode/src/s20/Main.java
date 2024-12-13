package s20;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String s =  "(]";

        System.out.println(isValid(s));

    }
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char i : s.toCharArray()){
            if (i =='(' || i == '{' || i =='['){
                stack.push(i);
            }
            else {
                if (stack.empty()){
                    return false;
                }
                char top = stack.peek();
                if (i == ')' && top == '('|| (i == ']' && top == '[') || (i == '}' && top == '{')){
                    stack.pop();
                }else {
                    return false;
                }
            }

        }
        return stack.empty();
    }
}
