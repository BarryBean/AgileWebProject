package com.company;

/**
 * @PackageName:com.company
 * @ClassName:Fibonacci
 * @Description
 * @Author: yushengbi
 * @Date:2020/3/4 17:41
 */
public class Fibonacci {

    public static double Fibonacci(int n) {
        if (n == 1) {
            return 1;
        }
        double pre = 0, sum = 1;
        for (int i = 2; i <= n; i++) {
            sum += pre;
            pre = sum - pre;
        }
        return sum;
    }

    public static int Fibonacci1(int n) {
        if (n == 1) {
            return 1;
        }
        int one = 1, two = 1, sum = 1;
        for (int i = 2; i <= n; i++) {
            sum = (one + two) % 1000000007;
            one = two;
            two = sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 200; i++) {
            System.out.print(Fibonacci(i) + " ");
        }
        System.out.println();
        System.out.println("===========");
        for (int i = 1; i <= 200; i++) {
            System.out.print(Fibonacci1(i) + " ");
        }
    }
}
