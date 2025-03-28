package org.example;

import java.util.*;

/**
 * Реализация динамического массива, поддерживающего операции добавления,
 * удаления, очистки, получения и сортировки элементов.
 *
 * @author Андрей Рекаев
 * @version 1.0
 * @since 17
 * @param <T> Тип элементов, хранимых в списке
 */

public class MyArrayList<T extends Comparable<T>> implements Iterable<T> {
    /**
     * Статическая константа по умолчанию для начальной емкости массива.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Массив для хранения элементов.
     */
    private T[] elements;

    /**
     * Текущий размер списка.
     */
    private int size;

    /**
     * Конструктор по умолчанию, создающий пустой список с начальной ёмкостью 10.
     */
    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Конструктор, создающий пустой список с указанной начальной ёмкостью.
     *
     * @param initSize Начальная ёмкость списка
     * @throws IllegalArgumentException Если начальная ёмкость отрицательна
     */
    public MyArrayList(int initSize) {
        if (initSize < 0) {
            throw new IllegalArgumentException("illegal size:" + initSize);
        }
        this.elements = (T[]) new Comparable[initSize];
    }

    /**
     * Проверяет, является ли список пустым.
     *
     * @return {@code true}, если список пуст, иначе {@code false}
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Возвращает текущий размер списка.
     *
     * @return Размер списка
     */
    public int size() {
        return this.size;
    }


    /**
     * Добавляет элемент в конец списка.
     *
     * @param element Элемент для добавления
     */
    public void add(T element) {
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            ensureCapacity(size + 1);
            elements[size++] = element;
        }
    }

    /**
     * Добавляет элемент в указанную позицию списка.
     *
     * @param index Индекс позиции для вставки
     * @param element Элемент для добавления
     * @throws IndexOutOfBoundsException Если индекс выходит за границы допустимого диапазона
     */
    public void add(int index, T element) {
        checkIndex(index);
        ensureCapacity(size + 1);

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Добавляет все элементы из указанной коллекции в конец этого списка.
     *
     * @param c коллекция, содержащая элементы для добавления
     * @throws NullPointerException если указанная коллекция равна null
     */
    public void addAll(Collection<T> c) {
        int addSize = c.size();
        ensureCapacity(size + addSize);

        for (T element : c) {
            elements[size++] = element;
        }
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index Индекс элемента
     * @return Элемент по указанному индексу
     * @throws IndexOutOfBoundsException Если индекс выходит за границы допустимого диапазона
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index Индекс удаляемого элемента
     * @return Удалённый элемент
     * @throws IndexOutOfBoundsException Если индекс выходит за границы допустимого диапазона
     */
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) elements[index];

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;

        return removedElement;
    }

    /**
     * Очищает список, удаляя все элементы.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Возвращает строковое представление списка в формате [element1, element2, ...].
     *
     * @return Строковое представление списка
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Проверяет, находится ли указанный индекс в пределах допустимого диапазона.
     *
     * @param index Индекс для проверки
     * @throws IndexOutOfBoundsException Если индекс выходит за границы допустимого диапазона
     */
    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    /**
     * Увеличивает ёмкость массива, если текущая ёмкость недостаточна для хранения нового элемента.
     *
     * @param minCapacity Минимально необходимая ёмкость
     */
    public void ensureCapacity(int minCapacity) {
        int current = elements.length;
        if (minCapacity > current) {
            T[] newData = (T[]) new Object[Math.max(current * 2, minCapacity)];
            System.arraycopy(elements, 0, newData, 0, size);
            elements = newData;
        }
    }

    /**
     * Реализация сортировки слиянием.
     */
    public void mergeSort() {
        if (size <= 1) {
            return; // Базовый случай: список уже отсортирован
        }
        T[] temp = (T[]) new Comparable[size];
        mergeSort(0, size - 1, temp);
    }

    /**
     * Рекурсивный метод для сортировки слиянием.
     *
     * @param left  Левый индекс подмассива
     * @param right Правый индекс подмассива
     * @param temp  Временный массив для слияния
     */
    private void mergeSort(int left, int right, T[] temp) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(left, mid, temp);
            mergeSort(mid + 1, right, temp);
            merge(left, mid, right, temp);
        }
    }

    /**
     * Метод для слияния двух отсортированных подмассивов.
     *
     * @param left  Левый индекс подмассива
     * @param mid   Середина подмассива
     * @param right Правый индекс подмассива
     * @param temp  Временный массив для слияния
     */
    private void merge(int left, int mid, int right, T[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = elements[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp[i].compareTo(temp[j]) <= 0) {
                elements[k++] = temp[i++];
            } else {
                elements[k++] = temp[j++];
            }
        }

        while (i <= mid) {
            elements[k++] = temp[i++];
        }

        while (j <= right) {
            elements[k++] = temp[j++];
        }
    }

    /**
     * Реализация итератора для MyArrayList.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return elements[currentIndex++];
            }
        };
    }
}
