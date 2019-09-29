public class BubbleGrid {
    private int[][] grid;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        //counts number of bubbles
        int bubbleCount = 0;
        for (int[] r : grid) {
            for (int c : r) {
                if (c == 1) {
                    bubbleCount++;
                }
            }
        }
        //stores dimensions of grid + result
        int row = grid.length;
        int column = grid[0].length;
        int[] result = new int[darts.length];
        //stores whether connection exists for loop checking
        boolean exist = false;
        boolean exist_column = false;
        boolean exist_loop = false;
        //stores initial bubble count
        int temp;
        //sets up UnionFinder
        UnionFind Bubbles = new UnionFind(row * column);

        for (int d = 0; d < darts.length; d++) {
            //subtracts 1 if popped at top
            if (darts[d][0] == 0) {
                  temp = bubbleCount;
              }
            else {
                  temp = bubbleCount - 1;
            }
            //stores 0 if no bubbles popped
            if (grid[darts[d][0]][darts[d][1]] == 0) {
                  result[d] = 0;
                  continue;
            }
            for (int i = 0; i < 2; i++) {
                //first run-through, going upwards
                if (i == 0) {
                    //sets popped bubble to 0
                    grid[darts[i][0]][darts[i][1]] = 0;
                    //checks for connected bubbles,
                    for (int x = 1; x < row; x++) {
                        for (int y = 0; y < column; y++) {
                            if (grid[x][y] == 1) {
                                //check for connected bubbles upwards then iterates from top to find connection
                                if (grid[x - 1][y] == 1) {
                                    for (int a = 0; a < column; a++) {
                                        if (Bubbles.connected(a, (x - 1) * column + y)) {
                                            Bubbles.union(x * column + y, (x - 1) * column + y);
                                            exist = true;
                                            exist_loop = true;
                                            break;
                                        }
                                    }
                                }
                                //check for connected bubbles left if not at left edge then iterates from top to find connection and connection not already found
                                if (y != 0 && grid[x][y - 1] == 1 && !exist) {
                                    for (int a = 0; a < column; a++) {
                                        if (Bubbles.connected(a, x * column + y - 1)) {
                                            Bubbles.union(x * column + y, x * column + y - 1);
                                            exist = true;
                                            exist_loop = true;
                                            break;
                                        }
                                    }
                                }
                                //check for connected bubbles right if not at right edge then iterates from top to find connection and connection not already found
                                if (y != column - 1 && grid[x][y + 1] == 1 && !exist) {
                                    for (int a = 0; a < column; a++) {
                                        if (Bubbles.connected(a, x * column + y + 1)) {
                                            Bubbles.union(x * column + y, x * column + y + 1);
                                            exist = true;
                                            exist_loop = true;
                                            break;
                                        }
                                    }
                                }

                            }
                            if (exist) {
                                exist_column = true;
                            }
                            exist = false;
                        }
                        if (!exist_column) {
                            break;
                        }
                        exist_column = true;
                    }
                }
                //second run-through, going downwards
                else {
                    do {
                        exist_loop = false;
                        boolean exist_temp = false;

                        for (int x = row - 1; x > 0; x--) {
                            for (int y = column - 1; y >= 0; y--) {
                                for (int a = 0; a < column; a++) {
                                    if (Bubbles.connected(a, x * column + y)) {
                                        exist_temp = true;
                                        break;
                                    }
                                }
                                if (exist_temp) {
                                    exist_temp = false;
                                    continue;
                                }
                                if (grid[x][y] == 1) {
                                    //check for connected bubbles left then iterates from top to find connection
                                    if (grid[x - 1][y] == 1) {
                                        for (int a = 0; a < column; a++) {
                                            if (Bubbles.connected(a, (x - 1) * column + y)) {
                                                Bubbles.union(x * column + y, (x - 1) * column + y);
                                                exist = true;
                                                exist_loop = true;
                                                break;
                                            }
                                        }
                                    }
                                    //check for connected bubbles down if not at bottom edge then iterates from top to find connection
                                    if (x != row - 1 && grid[x + 1][y] == 1) {
                                        for (int a = 0; a < column; a++) {
                                            if (Bubbles.connected(a, (x + 1) * column + y)) {
                                                Bubbles.union(x * column + y, (x + 1) * column + y);
                                                exist = true;
                                                exist_loop = true;
                                                break;
                                            }
                                        }
                                    }
                                    //check for connected bubbles left if not at left edge then iterates from top to find connection and connection not already found
                                    if (y != 0 && grid[x][y - 1] == 1 && !exist) {
                                        for (int a = 0; a < column; a++) {
                                            if (Bubbles.connected(a, x * column + y - 1)) {
                                                Bubbles.union(x * column + y, x * column + y - 1);
                                                exist = true;
                                                exist_loop = true;
                                                break;
                                            }
                                        }
                                    }
                                    //check for connected bubbles right if not at right edge then iterates from top to find connection and connection not already found
                                    if (y != column - 1 && grid[x][y + 1] == 1 && !exist) {
                                        for (int a = 0; a < column; a++) {
                                            if (Bubbles.connected(a, x * column + y + 1)) {
                                                Bubbles.union(x * column + y, x * column + y + 1);
                                                exist = true;
                                                exist_loop = true;
                                                break;
                                            }
                                        }
                                    }

                                    if (exist) {
                                        exist_column = true;
                                    }
                                    exist = false;
                                }
                                if (!exist_column) {
                                    break;
                                }
                                exist_column = true;
                            }
                        }
                    } while (exist_loop);
                }
            }
            grid[darts[d][0]][darts[d][1]] = 1;
            for (int k = 0; k < column; k++) {
                if (grid[0][k] == 1) {
                    temp = temp - Bubbles.sizeOf(k);
                }
            }
            result[d] = temp;
            Bubbles = new UnionFind(row * column);
            }
    return result;
    }

}
