package ru.mirea.senkovHW22;

import java.util.Stack;

public class MyRNP {
    public static String ExpressionToRNP(String Expr){
        String current = "";
        Stack<Character> stack = new Stack<>();

        int priority;
        for(int i = 0; i< Expr.length();i++){
            priority = getP(Expr.charAt(i));

            if (priority == 0) current+=Expr.charAt(i);
            if (priority == 1) stack.push(Expr.charAt(i));

            if (priority > 1){
                current+=' ';
                while (!stack.empty()){
                    if(getP(stack.peek()) >= priority) current+=stack.pop();
                    else break;
                }
                stack.push(Expr.charAt(i));
            }
            if (priority == -1){
                current+=' ';
                while(getP(stack.peek())!=1) current+=stack.pop();

                stack.pop();
            }
        }
        while (!stack.empty()) current+=stack.pop();
        return current;
    }

    public static double RNPtoAnswer(String prn){
        String operand= new String();
        Stack<Double> stack = new Stack<>();

        for (int i=0; i < prn.length();i++) {
            if (getP(prn.charAt(i)) == ' ') continue;
            if (getP(prn.charAt(i)) == 0) {
                while (prn.charAt(i) != ' ' && getP(prn.charAt(i)) == 0){
                    operand += prn.charAt(i++);
                    if (i == prn.length()) break;}
                stack.push(Double.parseDouble(operand));
                operand = "";
            }
            if (getP(prn.charAt(i))>1){
                double a =stack.pop(), b= stack.pop();
                if (prn.charAt(i) == '+')stack.push(b+a);
                if (prn.charAt(i) == '-')stack.push(b-a);
                if (prn.charAt(i) == '*')stack.push(b*a);
                if (prn.charAt(i) == '/')stack.push(b/a);
            }
        }
        return stack.pop();
    }

    private static int getP(char token){ //расставим приоритет в знаках
        if (token == '*'|| token == '/'){
            return 3;
        } else if (token == '+' || token == '-') {
            return 2;
        } else if (token == '(') {
            return 1;
        } else if (token == ')') {
            return -1;
        }else return 0;
    }
}
