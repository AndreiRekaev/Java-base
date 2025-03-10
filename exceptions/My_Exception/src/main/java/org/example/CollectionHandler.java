package org.example;

import java.util.ArrayList;
import java.util.List;

class CollectionHandler {

    private List<Integer> collection = new ArrayList<>();

    public void addElement(Object element) throws InvalidTypeException {
        if (!(element instanceof Integer)) {
            throw new InvalidTypeException("Неправильный тип элемента: " + element.getClass().getSimpleName());
        }
        collection.add((Integer) element);
        System.out.println("Элемент добавлен: " + element);
    }

    public void checkCorrectElem(Object element) {
        try {
            addElement(element);
        } catch (InvalidTypeException e) {
            System.out.println("Перехвачено исключение: " + e.getMessage());
        }
    }
}
