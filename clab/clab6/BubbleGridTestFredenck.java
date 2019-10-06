import org.junit.Test;
import static org.junit.Assert.*;

public class BubbleGridTestFredenck {

    @Test
    public void testBasic() {

        int[][] grid = {{1, 0, 0, 0},
                        {1, 1, 1, 0}};
        int[][] darts = {{1, 0}};
        int[] expected = {2};

        validate(grid, darts, expected);
    }
    @Test
    public void testGiven() {

        int[][] grid = {{1, 1, 0},
                        {1, 0, 0},
                        {1, 1, 0},
                        {1, 1, 1}};
        int[][] darts = {{2,2},{2,0}};
        int[] expected = {0,4};

        validate(grid, darts, expected);
    }
    @Test
    public void customTest(){
        int[][] grid = {{1,1,1,1,1},
                        {1,1,1,1,1},
                        {1,1,1,1,1},
                        {1,1,1,1,1},
                        {1,1,1,1,1},
                        {1,1,1,1,1},
                        {1,1,1,1,1}};
        int[][] darts = {{0,0},{0,1},{0,2}};
        int[] expected = {0,0,0};

        validate(grid,darts,expected);
    }
    @Test
    public void evilTest(){
        int[][] grid = {{1,1,1,0,1,1,1},
                        {1,0,0,0,0,0,1},
                        {1,0,1,1,1,0,1},
                        {1,0,0,0,0,0,1},
                        {1,0,1,1,1,1,1},
                        {1,0,0,0,0,0,1},
                        {1,1,1,1,1,1,1}};
        int[][] darts = {{0,3},{0,1},{0,2}};
        int[] expected = {3,0,0};

        validate(grid,darts,expected);
    }
    @Test
    public void evilTest2(){
        int[][] grid = {{0,0,0,0,0},
                        {1,1,1,1,1},
                        {1,1,1,1,1},
                        {1,1,1,1,1},
                        {1,1,1,1,1},
                        {1,1,1,1,1},
                        {1,1,1,1,1}};
        int[][] darts = {{0,3},{0,1},{0,2}};
        int[] expected = {25,0,0};

        validate(grid,darts,expected);
    }
    private void validate(int[][] grid, int[][] darts, int[] expected) {
        BubbleGrid sol = new BubbleGrid(grid);
        assertArrayEquals(expected, sol.popBubbles(darts));
    }
    public static void main(String[] args) {
      BubbleGridTestFredenck b = new BubbleGridTestFredenck();
      b.testBasic();
      b.customTest();
      //b.evilTest();
      b.evilTest2();
    }
}
