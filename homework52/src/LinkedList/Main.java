package LinkedList;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        CustomLinkedList<Integer> linkedList1 = new CustomLinkedList<>();

        for (int i = 0; i < 10; i++) {
            linkedList1.add(random.nextInt(101));
        }

        System.out.print("The linked list: ");
        linkedList1.print();
        System.out.println();
        System.out.println("Size: " + linkedList1.size());
        System.out.println("Get: " + linkedList1.get(5));
    }
}




