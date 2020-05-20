package test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArraysTest {

    public static void main(String[] args) {
        int[][] ints = {{4}, {1, 2}, {40, 20}, {52, 60}};
        int[][] ints1 = {{4}, {1, 2}, {40, 20}, {52, 60}};
        int[] ints2 = {4, 1, 2, 40, 20, 52, 60};
//        Arrays.parallelPrefix(ints, (a, b) -> a * b);
//        Arrays.sort(ints);
//        System.out.println(Arrays.toString(ints));
//        int index = Arrays.binarySearch(ints, 52);
//        System.out.println(Arrays.hashCode(ints) == Arrays.hashCode(ints1));

//        System.out.println(Arrays.equals(ints, ints1));
//        System.out.println(Arrays.deepEquals(ints, ints1));
        IntStream stream = Arrays.stream(ints2);
        int a = stream.filter(x -> x > 5).sum();
        System.out.println(a);
//        stream.map(x -> x = 5).forEach(x -> System.out.println(x));
    }
}
