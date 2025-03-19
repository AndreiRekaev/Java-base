package org.example;

import java.util.*;

class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
        this.next = null;
    }
}


public class MyLinkedList<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> head;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param data Элемент для добавления
     */
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * Возвращает элемент по индексу.
     *
     * @param index Индекс элемента
     * @return Элемент по указанному индексу
     * @throws IndexOutOfBoundsException Если индекс выходит за границы
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Удаляет элемент по индексу.
     *
     * @param index Индекс удаляемого элемента
     * @throws IndexOutOfBoundsException Если индекс выходит за границы
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }

    /**
     * Возвращает размер списка.
     *
     * @return Размер списка
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли список.
     *
     * @return {@code true}, если список пуст, иначе {@code false}
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Очищает список.
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Возвращает строковое представление списка.
     *
     * @return Строковое представление списка
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Реализация итератора для LinkedList.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    /**
     * Быстрая сортировка (Quick Sort) для списка.
     */
    public void quickSort() {
        if (head == null || head.next == null) {
            return;
        }
        head = quickSort(head);
    }

    /**
     * Рекурсивный метод для быстрой сортировки.
     *
     * @param start Начальный узел подсписка
     * @return Отсортированный подсписок
     */
    private Node<T> quickSort(Node<T> start) {
        if (start == null || start.next == null) {
            return start; // Базовый случай: список пуст или содержит один элемент
        }

        Node<T> pivot = start;
        Node<T> lessHead = null, lessTail = null;
        Node<T> greaterHead = null, greaterTail = null;

        Node<T> current = start.next;
        while (current != null) {
            if (current.data.compareTo(pivot.data) < 0) {
                if (lessHead == null) {
                    lessHead = lessTail = current;
                } else {
                    lessTail.next = current;
                    lessTail = current;
                }
            } else {
                if (greaterHead == null) {
                    greaterHead = greaterTail = current;
                } else {
                    greaterTail.next = current;
                    greaterTail = current;
                }
            }
            current = current.next;
        }

        if (lessTail != null) {
            lessTail.next = null;
        }
        if (greaterTail != null) {
            greaterTail.next = null;
        }

        Node<T> sortedLess = quickSort(lessHead);
        Node<T> sortedGreater = quickSort(greaterHead);

        if (sortedLess != null) {
            Node<T> temp = sortedLess;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = pivot;
        } else {
            sortedLess = pivot;
        }
        pivot.next = sortedGreater;

        return sortedLess;
    }
}
