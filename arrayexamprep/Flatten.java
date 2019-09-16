import java.util.ArrayList;
public class Flatten {
  public static int[] flatten(int[][] x) {
    int totalLength = 0;
    for (int i = 0; i < x.length; i++) {
      totalLength += x[i].length;
    }
    int[] a = new int[totalLength];
    int aIndex = 0;
    for (int i = 0; i < x.length; i++) {
      for (int j = 0; j < x[i].length; j++) {
        a[aIndex] = x[i][j];
        aIndex += 1;
      }
    }
    return a;
  }
  public static int[] skippify(int[] x) {
    ArrayList<Integer> newx = new ArrayList<Integer>();
    int n = 2;
    int i = 0;
    while (i < x.length) {
      newx.add(x[i]);
      i += n;
      n += 1;
    }
    int[] newxarr = new int[newx.size()];
    for (int j = 0; j < newx.size(); j++) {
      newxarr[j] = newx.get(j);
    }
    return newxarr;
  }
  public static int[] sans(int[] x, int y) {
    ArrayList<Integer> newx = new ArrayList<Integer>();
    for(int i = 0; i < x.length; i++) {
      if (x[i] != y) {
        newx.add(x[i]);
      }
    }
    int[] newxarr = new int[newx.size()];
    for (int j = 0; j < newx.size(); j++) {
      newxarr[j] = newx.get(j);
    }
    return newxarr;
  }
  public static void main(String[] args) {
    int[][] t = {{1}, {1, 2}, {1, 2, 3}};
    int[] tflat = flatten(t);
    for (int i : tflat) {
      System.out.print(i + " ");
    }
    System.out.println("");
    int[] tskip = skippify(tflat);
    for (int i : tskip) {
      System.out.print(i + " ");
    }
    System.out.println("");
    int[] tsans = sans(tskip, 1);
    for (int i : tsans) {
      System.out.print(i + " ");
    }
  }
}
