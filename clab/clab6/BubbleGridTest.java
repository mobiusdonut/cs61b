import org.junit.Test;
import static org.junit.Assert.*;

public class BubbleGridTest {

    @Test
    public void testBasic() {

        int[][] grid = {{1, 0, 0, 0},
                        {1, 1, 1, 0}};
        int[][] darts = {{1, 0}};
        int[] expected = {2};

        validate(grid, darts, expected);
    }

    @Test
    public void testBasic2() {

        int[][] grid = {{1, 1, 0},
                        {1, 0, 0},
                        {1, 1, 0},
                        {1, 1, 1},};
        int[][] darts = {{2, 2}, {2, 0}};
        int[] expected = {0, 4};

        validate(grid, darts, expected);
    }

    public void testBasic3() {

        int[][] grid = {{1, 1, 1},
                        {1, 1, 1},
                        {1, 1, 1},
                        {1, 1, 1},};
        int[][] darts = {{0, 0}};
        int[] expected = {0};

        validate(grid, darts, expected);
    }

    private void validate(int[][] grid, int[][] darts, int[] expected) {
        BubbleGrid sol = new BubbleGrid(grid);
        assertArrayEquals(expected, sol.popBubbles(darts));
    }

    public static void main(String[] args) {
      BubbleGridTest b = new BubbleGridTest();
      b.testBasic();
      b.testBasic2();
      b.testBasic3();
    }
}
