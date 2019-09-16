public class Palindrome {
  public Deque<Character> wordToDeque(String word) {
    Deque d = new LinkedListDeque<Character>();
    for (int i = 0; i < word.length(); i++) {
      //System.out.println(word.charAt(i));
      d.addLast(word.charAt(i));
      //d.printDeque();
    }
    /*Node<Character> first = d.getFirst();
    while (first != null) {
      System.out.println(first.getValue());
      first = first.getNext();
    }
    for(int i = 0; i < d.size(); i++) {
      System.out.print(i + " ");
      System.out.println(d.get(i));
    }*/
    return d;
  }
  public boolean isPalindrome(String word) {
    Deque d = wordToDeque(word);
    if (d.size() == 0 || d.size() == 1) {
      return true;
    }
    else {
      for (int i = 0; i < d.size() / 2; i++) {
        if (d.get(i) != d.get(d.size() - i - 1)) {
          return false;
        }
      }
      return true;
    }
  }
}
