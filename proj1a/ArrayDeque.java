public class ArrayDeque<T> {
  private T[] adeq;
  private int offset, size;

  public ArrayDeque() {
    offset = 0;
    size = 0;
    resize();
  }
  public void addFirst(T item) {
    if (size + 1 > adeq.length) {
      resize();
    }
    for (int i = size; i >= 0; i--) {
      adeq[i] = adeq[i - 1];
    }
    adeq[0] = item;
    size += 1;
  }
  public void addLast(T item) {
    if (size + 1 > adeq.length) {
      resize();
    }
    adeq[size] = item;
    size += 1;
  }
  public boolean isEmpty() {
    return (size() == 0);
  }
  public int size() {
    return size;
  }
  public void printDeque() {
    for (int i = 0; i <= size; i++) {
      System.out.print(adeq[i] + " ");
    }
    System.out.print("\n");
  }
  public void removeFirst() {
    for (int i = 0; i < size; i++) {
      adeq[i] = adeq[i + 1];
    }
    size -= 1;
    if (3 * size < adeq.length) {
      resize();
    }
  }
  public void removeLast() {
    size -= 1;
    if (3 * size < adeq.length) {
      resize();
    }
  }
  public T get(int index) {
    return adeq[(offset + index) % adeq.length];
  }
  private void resize() {
    T[] temp = (T[]) new Object[Math.max(2 * size, 1)];
    for (int i = 0; i < size; i++) {
      temp[i] = adeq[(offset + i) % adeq.length];
    }
    adeq = temp;
    offset = 0;
  }
}
