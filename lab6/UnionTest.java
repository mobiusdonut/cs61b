import static org.junit.Assert.*;
import org.junit.Test;

public class UnionTest {
  public static void main(String[] args) {
    UnionFind u = new UnionFind(5);
    int[] testParent = {0, 1, 2, 3, 4};
    int[] testSize = {1, 1, 1, 1, 1};
    for (int i = 0; i < testParent.length; i++) {
      assertEquals(u.getParent()[i], testParent[i]);
      assertEquals(u.getSize()[i], testSize[i]);
    }
    u.union(0, 4);
    u.union(2, 3);
    u.union(3, 4);
    int[] testParent2 = {2, 1, 2, 2, 0};
    int[] testSize2 = {2, 1, 4, 1, 1};
    for (int i = 0; i < testParent.length; i++) {
      assertEquals(u.getParent()[i], testParent2[i]);
      assertEquals(u.getSize()[i], testSize2[i]);
    }
    assertEquals(u.connected(0, 3), true);
    assertEquals(u.count(), 2);

    UnionFind u2 = new UnionFind(5);
    int[] testParent3 = {0, 1, 2, 3, 4};
    int[] testSize3 = {1, 1, 1, 1, 1};
    for (int i = 0; i < testParent.length; i++) {
      assertEquals(u2.getParent()[i], testParent3[i]);
      assertEquals(u2.getSize()[i], testSize3[i]);
    }
    u2.union(1, 3);
    u2.union(4, 2);
    u2.union(0, 1);
    int[] testParent4 = {1, 1, 4, 1, 4};
    int[] testSize4 = {1, 3, 1, 1, 2};
    for (int i = 0; i < testParent.length; i++) {
      assertEquals(u2.getParent()[i], testParent4[i]);
      assertEquals(u2.getSize()[i], testSize4[i]);
    }
    assertEquals(u2.connected(1, 4), false);
    assertEquals(u2.count(), 2);
  }
}
