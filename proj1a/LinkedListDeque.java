public class LinkedListDeque<T> {
  private Node<T> first;
  private Node<T> last;
  private int size;

  public LinkedListDeque() {
    first = null;
    last = null;
    size = 0;
  }
  public LinkedListDeque(LinkedListDeque<T> other) {
    Node<T> flag = other.getFirst();
    while (flag != null) {
      addLast(flag.getValue());
      flag = flag.getNext();
    }
  }
  public void addFirst(T item) {
    Node<T> node = new Node<T>(item);
    if (isEmpty()) {
      last = node;
    }
    else {
      first.setPrev(node);
      node.setNext(first);
    }
    first = node;
    size += 1;
  }
  public void addLast(T item) {
    Node<T> node = new Node<T>(item);
    if (isEmpty()) {
      first = node;
    }
    else {
      last.setNext(node);
      node.setPrev(last);
    }
    last = node;
    size += 1;
  }
  public boolean isEmpty() {
    return (size() == 0);
  }
  public int size() {
    return size;
  }
  public void printDeque() {
    Node<T> flag = first;
    while (flag != null) {
      System.out.print(flag.getValue() + " ");
      flag = flag.getNext();
    }
    System.out.print("\n");
  }
  public void removeFirst() {
    if (first.getNext() != null) {
			first = first.getNext();
      first.setPrev(null);
    }
		else {
      first = null;
    }
    size -= 1;
  }
  public void removeLast() {
    if (last.getPrev() != null) {
			last = last.getPrev();
      last.setNext(null);
    }
		else {
      last = null;
    }
    size -= 1;
  }
  public T get(int index) {
    int ind = 0;
    Node<T> val = first;
    while (ind < index) {
      val = val.getNext();
    }
    return val.getValue();
  }
  public Node<T> getFirst() {
    return first;
  }
  public Node<T> getLast() {
    return last;
  }
}

class Node<T> {
  private T value;
  private Node<T> next;
  private Node<T> prev;

  public Node(T value) {
    this.value = value;
  }
  public void setNext(Node<T> next) {
    this.next = next;
    next.prev = this;
  }
  public Node<T> getNext() {
    return next;
  }
  public void setPrev(Node<T> prev) {
    this.prev = prev;
    prev.next = this;
  }
  public Node<T> getPrev() {
    return prev;
  }
  public T getValue() {
    return value;
  }
}
