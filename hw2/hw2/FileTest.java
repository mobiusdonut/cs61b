import java.io.File;

public class FileTest {
  public static void main(String[] args) {
      String filename = args[0];
      In in = new In(new File(filename));
      int N = in.readInt();
  }
}
