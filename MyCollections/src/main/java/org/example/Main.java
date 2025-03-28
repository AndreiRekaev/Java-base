package org.example;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> alist = new MyArrayList<>();
        alist.add(5);
        alist.add(3);
        alist.add(8);
        alist.add(1);
        alist.add(2);

        System.out.println("Before sorting: " + alist);
        alist.mergeSort();
        System.out.println("After sorting: " + alist);

        MyLinkedList<Integer> llist = new MyLinkedList<>();
        llist.add(5);
        llist.add(3);
        llist.add(8);
        llist.add(1);
        llist.add(2);

        System.out.println("Before sorting: " + llist);
        llist.quickSort();
        System.out.println("After sorting: " + llist);
    }
}