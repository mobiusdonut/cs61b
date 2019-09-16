public classDMSList {
  private IntNode sentinel;
  publicDMSList() {
    sentinel = newIntNode(-1000, );
  }
  public class IntNode {
    public intitem;
    public IntNode next;
    public IntNode(int i, IntNode h) {
      item = i;
      next = h;
    }
    public int max() {
      returnMath.max(item, next.max());
    }
  }
  public void insert {

  }
  public int max() {
    returnsentinel.next.max();
  }
}
