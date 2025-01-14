import org.example.MyArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    @Test
    void testConstructorAndIsEmpty() {
        MyArrayList<String> list = new MyArrayList<>();
        assertTrue(list.isEmpty());
    }

    @Test
    void testAddAndGet() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    void testSize() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("a");
        list.add("b");

        assertEquals(2, list.size());
    }

    @Test
    void testAddAtEnd() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("a");
        list.add("b");

        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
    }

    @Test
    void testAddAtIndex() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(0, 5);
        list.add(1, 10);

        assertEquals(5, list.get(0));
        assertEquals(10, list.get(1));
    }

    @Test
    void testAddOutOfBoundsException() {
        MyArrayList<String> list = new MyArrayList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "test"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(list.size() + 1, "test"));
    }

    @Test
    void testRemove() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(2, (int)list.remove(1));
        assertEquals(2, list.size());
    }

    @Test
    void testRemoveIndexOutOfBoundsException() {
        MyArrayList<String> list = new MyArrayList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
    }

    @Test
    void testSort() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(4);
        list.add(2);
        list.add(1);
        list.sort();

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(4, list.get(2));
    }

    @Test
    void testClear() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("a");
        list.add("b");
        list.clear();

        assertTrue(list.isEmpty());
    }
}
