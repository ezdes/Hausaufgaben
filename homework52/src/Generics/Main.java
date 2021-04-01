package Generics;

import java.util.*;

public class Main {
    private static final Random random = new Random();

    public static void main(String[] args) {
        Integer[] array1 = new Integer[10];
        List<Integer> arrayList1 = new ArrayList<>();
        List<Integer> linkedList1 = new LinkedList<>();

        Double[] array2 = new Double[10];
        List<Double> arrayList2 = new ArrayList<>();
        List<Double> linkedList2 = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            arrayList1.add(random.nextInt(50));
            linkedList1.add(random.nextInt(50));

            arrayList2.add(random.nextDouble() * 50);
            linkedList2.add(random.nextDouble() * 50);

            array1[i] = random.nextInt(50);
            array2[i] = random.nextDouble() * 50;
        }

        System.out.print("First array: ");
        for (Integer i : array1) {
            System.out.print(i + " ");
        }
        System.out.println();
        avg(array1);
        System.out.println();

        System.out.println("First array list: " + arrayList1);
        sum(arrayList1);
        System.out.println();

        System.out.println("First linked list: " + linkedList1);
        sum(linkedList1);
        System.out.println();

        System.out.print("Second array: ");
        for (Double i : array2) {
            System.out.print(i + " ");
        }
        System.out.println();
        avg(array2);
        System.out.println();

        System.out.println("Second array list: " + arrayList2);
        sum(arrayList2);
        System.out.println();

        System.out.println("Second linked list: " + linkedList2);
        sum(linkedList2);
    }

    private static void sum(Collection<? extends Number> collection) {
        double number = 0;

        for (Number n : collection) {
            number += n.doubleValue();
        }

        System.out.println("Sum: " + number);
    }

    private static <T extends Number> void avg(T[] array) {
        double sum = 0.0;

        for (T t : array) {
            sum += t.doubleValue();
        }

        System.out.println("Average: " + sum / array.length);
    }
}
