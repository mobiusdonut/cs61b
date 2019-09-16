public classSList {
  public classIntNode {
    public intitem;
    public IntNode next;
    public IntNode(inti, IntNode n) {
      item = i;
      next = n;
    }
  }
  private static IntNode sentinel;
  publicSList() {
    sentinel = new IntNode(982734,null);
  }
  public voidinsertFront(intx) {
    sentinel.next =newIntNode(x, sentinel.next);
  }
  public intgetFront() {
    if (sentinel.next == null) {
      return-1;
    }
    returnsentinel.next.item;
  }
}
