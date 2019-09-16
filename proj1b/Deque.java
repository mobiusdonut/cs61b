public interface Deque<T> {
  public void addFirst(T item);
  public void addLast(T item);
  default boolean isEmpty() {
    return (this.size() == 0);
  }
  public int size();
  public void printDeque();
  public void removeFirst();
  public void removeLast();
  public T get(int index);
  public Node<T> getFirst();
  public Node<T> getLast();
}
