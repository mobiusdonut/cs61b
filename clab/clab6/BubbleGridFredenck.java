

public class BubbleGridFredenck {
    private int[][] grid;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGridFredenck(int[][] grid) {
        this.grid = grid;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        // TODO
        int R = grid.length, C = grid[0].length;
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};

        int[][] A = new int[R][C];
        for (int r = 0; r < R; ++r) {
            A[r] = grid[r].clone();
        }
        for (int[] hit: darts) {
            A[hit[0]][hit[1]] = 0;
        }

        CustomWQU dsu = new CustomWQU(R*C + 1);
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (A[r][c] == 1) {
                    int i = r * C + c;
                    if (r == 0) //top row is always stuck, union with bottom (since it is stuck)
                        dsu.union(i, R * C);
                    if (r > 0 && A[r-1][c] == 1) {//left
                        dsu.union(i, (r - 1) * C + c);
                    }
                    if (c > 0 && A[r][c-1] == 1) {//right
                        dsu.union(i, r * C + c - 1);
                    }
                }
            }
        }

        int t = darts.length;
        int[] ans = new int[t];
        t--;

        while (t>=0){
            int r = darts[t][0];
            int c = darts[t][1];
            int prevTop = dsu.top();

            if (grid[r][c]==0){//dart at nothing
                t--;
            }else{//dart hits a bubble
                int cur = r*C+c;
                for (int k = 0; k < 4; ++k) {//4 dir
                    int nr = r + dr[k];
                    int nc = c + dc[k];
                    if (0 <= nr && nr < R && 0 <= nc && nc < C && A[nr][nc] == 1)
                        dsu.union(cur, nr * C + nc);
                }
                if (r==0){
                    dsu.union(cur, R * C);
                }

                A[r][c] = 1;
                ans[t] = Math.max(0, dsu.top() - prevTop - 1);
                t--;
            }
        }

        return ans;
    }
}
