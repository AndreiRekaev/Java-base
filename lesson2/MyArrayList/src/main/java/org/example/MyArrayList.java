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

public class MyArrayList<T> implements Iterable<T> {
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
        this.elements = (T[]) new Object[initSize];
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
    public <E> void addAll(Collection<E> c) {
        int addSize = c.size();
        ensureCapacity(size + addSize);

        int i = 0;
        for (E element : c) {
            elements[size + i++] = (T) element; // Приведение типа
        }

        size += addSize;
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
     * Сортирует элементы списка в порядке возрастания, используя интерфейс {@link Comparable}.
     */
    public void sort() {
        quickSort((T[]) elements, 0, size - 1);
    }

    /**
     * Сортирует элементы списка с использованием заданного компаратора.
     *
     * @param comparator Компаратор для сравнения элементов
     */
    public void sort(Comparator<? super T> comparator) {
        quickSort((T[]) elements, 0, size - 1, comparator);
    }

    /**
     * Выполняет быструю сортировку массива через хвостовую рекурсию  с использованием интерфейса {@link Comparable}.
     *
     * @param array Массив для сортировки
     * @param low Левый индекс подмассива
     * @param high Правый индекс подмассива
     */
    private void quickSort(T[] array, int low, int high) {
        while (low < high) {
            int pivotIndex = partition(array, low, high);
            // Сначала сортируем меньший подмассив
            if (pivotIndex - low < high - pivotIndex) {
                quickSort(array, low, pivotIndex - 1);
                low = pivotIndex + 1;
            } else {
                quickSort(array, pivotIndex + 1, high);
                high = pivotIndex - 1;
            }
        }
    }

    /**
     * Выполняет быструю сортировку массива через хвостовую рекурсию с использованием компаратора.
     *
     * @param array Массив для сортировки
     * @param low Левый индекс подмассива
     * @param high Правый индекс подмассива
     * @param comparator Компаратор для сравнения элементов
     */
    private void quickSort(T[] array, int low, int high, Comparator<? super T> comparator) {
        while (low < high) {
            int pivotIndex = partition(array, low, high, comparator);
            // Сначала сортируем меньший подмассив
            if (pivotIndex - low < high - pivotIndex) {
                quickSort(array, low, pivotIndex - 1, comparator);
                low = pivotIndex + 1;
            } else {
                quickSort(array, pivotIndex + 1, high, comparator);
                high = pivotIndex - 1;
            }
        }
    }

    /**
     * Разделяет массив вокруг опорного элемента, используя интерфейс {@link Comparable}.
     *
     * @param array Массив для разделения
     * @param low Левый индекс подмассива
     * @param high Правый индекс подмассива
     * @return Индекс опорного элемента
     */
    private int partition(T[] array, int low, int high) {
        T pivot = array[high];
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (((Comparable<T>) array[j]).compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    /**
     * Разделяет массив вокруг опорного элемента, используя компаратор.
     *
     * @param array Массив для разделения
     * @param low Левый индекс подмассива
     * @param high Правый индекс подмассива
     * @param comparator Компаратор для сравнения элементов
     * @return Индекс опорного элемента
     */
    private int partition(T[] array, int low, int high, Comparator<? super T> comparator) {
        T pivot = array[high];
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    /**
     * Меняет местами два элемента в массиве.
     *
     * @param array Массив, содержащий элементы
     * @param i Индекс первого элемента
     * @param j Индекс второго элемента
     */
    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Возвращает итератор для обхода элементов списка.
     *
     * @return Итератор для списка
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    /**
     * Внутренний класс, реализующий итератор для списка.
     */
    private class ArrayIterator implements Iterator<T> {
        private int cursor = 0;

        /**
         * Проверяет, есть ли ещё элементы для итерации.
         *
         * @return {@code true}, если есть ещё элементы, иначе {@code false}
         */
        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        /**
         * Возвращает следующий элемент в списке.
         *
         * @return Следующий элемент
         * @throws NoSuchElementException Если больше нет элементов для итерации
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements[cursor++];
        }
    }
}
