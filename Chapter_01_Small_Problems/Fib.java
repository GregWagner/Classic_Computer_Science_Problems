
// package chapter1;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Fib {
    // private static int fib1(int n) {
    //     return fib1(n - 1) + fib1(n - 2);
    // }

    private static int fib2(int n) {
        if (n < 2) {
            return n;
        }
        return fib2(n - 1) + fib2(n - 2);
    }

    // Map.of() was introducted in Java 9 but return an immutable Map
    // This creates a map wiith 0->0 and 1->1 which represent the base case
    static Map<Integer, Integer> memo = new HashMap<>(Map.of(0, 0, 1, 1));

    private static int fib3(int n) {
        if (!memo.containsKey(n)) {
            memo.put(n, fib3(n - 1) + fib3(n - 2));
        }
        return memo.get(n);
    }

    private static int fib4(int n) {
        int last = 0;
        int next = 1;
        for (int i = 0; i < n; ++i) {
            int oldLast = last;
            last = next;
            next += oldLast;
        }
        return last;
    }

    private int last = 0;
    private int next = 1;

    private IntStream stream() {
        return IntStream.generate(() -> {
            int oldLast = last;
            last = next;
            next += oldLast;
            return oldLast;
        });
    }

    public static void main(String[] args) {
        System.out.println(fib2(5));
        System.out.println(fib2(10));
        System.out.println(fib2(40));

        System.out.println(fib3(5));
        System.out.println(fib3(10));
        System.out.println(fib3(40));

        System.out.println(fib4(5));
        System.out.println(fib4(10));
        System.out.println(fib4(40));

        Fib fib = new Fib();
        fib.stream().limit(41).forEachOrdered(System.out::println);
    }
}