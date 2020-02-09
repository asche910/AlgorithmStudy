package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式算法
 */
public class ReversePolishNotation {
    public static void main(String[] args) {

        System.out.println(strToExpression("2*3*4"));
//        System.out.println(strToExpression("(6+2*(1+2))*4"));

//        List<String> list = strToExpression("(6+2*(1+2))*4");
//        String[] strs = new String[list.size()];
//        list.toArray(strs);

//        System.out.println(Arrays.toString(strs));
    }

    /**
     * 中缀表达式转化为后缀表达式
     */
    public static List<String> strToExpression(String str) {
        List<String> list = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '+' || ch == '-') {
                if (stack.isEmpty() || stack.peek().equals("(")) {
                    stack.push(String.valueOf(ch));
                } else {
                    while (!stack.isEmpty()) {
                        String peek = stack.peek();
                        if (peek.equals("+") || peek.equals("-") || peek.equals("*") || peek.equals("/")) {
                            list.add(stack.pop());
                            if(stack.isEmpty()){
                                stack.push(String.valueOf(ch));
                                break;
                            }
                        } else {
                            stack.push(String.valueOf(ch));
                            break;
                        }
                    }
                }
            } else if (ch == '*' || ch == '/') {
                if (stack.isEmpty()) {
                    stack.push(String.valueOf(ch));
                } else {
                    while (!stack.isEmpty()) {
                        String peek = stack.peek();
                        if (peek.equals("*") || peek.equals("/")) {
                            list.add(stack.pop());
                            if(stack.isEmpty()){
                                stack.push(String.valueOf(ch));
                                break;
                            }
                        } else {
                            stack.push(String.valueOf(ch));
                            break;
                        }
                    }
                }
            } else if (ch == '(') {
                stack.push(String.valueOf(ch));
            } else if (ch == ')') {
                while (!stack.peek().equals("(")) {
                    list.add(stack.pop());
                }
                stack.pop();
            } else if(ch == ' '){
                continue;
            }else {
                int len = 1;
                while (i + len < str.length() && str.charAt(i + len) >= '0' && str.charAt(i + len) <= '9') {
                    len++;
                }
                list.add(str.substring(i, i + len));
                i += len - 1;
            }
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }
}
