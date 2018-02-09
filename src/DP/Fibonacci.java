package DP;

/**
 * 任何数学递推公式都可以直接转换成递归算法 但是基本现实是编译器常常不能正确对待递归算法 导致低效的程序
 * 我们必须给编译器提供一些帮助 将递归算法写成非递归算法 让后者把那些子问题的答案系统地记录在一个表里
 * 利用这种方法的技巧叫做动态规划(Dynamic Programming)
 * @author swj
 * @create 2018/2/1
 */
public class Fibonacci {
    /**
     * 递归 指数级运行时间
     * @param n
     * @return
     */
    public static int fib(int n){
        if (n <= 1){
            return 1;
        }else{
            return fib(n - 1) + fib(n - 2);
        }
    }

    /**
     * 动态规划 O(N)
     * @param n
     * @return
     */
    public static int fibonacci(int n){
        if (n <= 1){
            return 1;
        }
        int first = 1;
        int next = 1;
        int answer = 0;
        for (int i = 1; i < n; i++){
            answer = first + next;
            first = next;
            next = answer;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(fib(10));
        System.out.println(fibonacci(10));
    }
}
