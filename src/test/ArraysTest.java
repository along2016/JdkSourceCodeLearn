package test;

import java.util.Arrays;

public class ArraysTest {

    public static void main(String[] args) {
        int[] ints = {4, 8, 40, 52, 60};
        int[] ints1 = {4, 8, 40, 52, 60};
//        Arrays.parallelPrefix(ints, (a, b) -> a * b);
//        Arrays.sort(ints);
//        System.out.println(Arrays.toString(ints));
//        int index = Arrays.binarySearch(ints, 52);
        System.out.println(Arrays.hashCode(ints) == Arrays.hashCode(ints1));
    }
}
