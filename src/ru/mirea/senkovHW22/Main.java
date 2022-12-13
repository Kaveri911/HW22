package ru.mirea.senkovHW22;

public class Main {
    public static void main(String[] args) {
        System.out.println(MyRNP.ExpressionToRNP("2+2*2"));
        System.out.println(MyRNP.RNPtoAnswer(MyRNP.ExpressionToRNP("2+2*2")));
    }
}
