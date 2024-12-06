package s150;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String[] tokens ={"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        evalRPN(tokens);
        System.out.println(evalRPN(tokens));
    }
    public static int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();
        for(String i : tokens){
            if(i.equals("+")){
                st.push(st.pop() + st.pop());
            } else if (i.equals("-")) {
                int second = st.pop();
                int first = st.pop();
            }else if (i.equals("*")) {
                st.push(st.pop() * st.pop());
            }else if (i.equals("/")) {
                int second = st.pop();
                int first = st.pop();
                st.push(first / second);
            }else {
                st.push(Integer.parseInt(i));
            }
        }
        return st.peek();
    }
}
