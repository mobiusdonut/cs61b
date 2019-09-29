public class UnionFind {

    private int[] parent; // id[i] = parent of i
    private int[] size; // sz[i] = number of objects in subtree rooted at i
    private int count; // number of components

    /**
     * Create an empty union find data structure with N isolated sets.
     *
     * @throws java.lang.IllegalArgumentException
     *             if N < 0
     */
    public UnionFind(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Return the id of component corresponding to object p.
     *
     * @throws java.lang.IndexOutOfBoundsException
     *             unless 0 <= p < N
     */

    public void validate(int v1) {
      if (v1 < 0 || v1 >= parent.length) {
          throw new IndexOutOfBoundsException();
      }
    }

    public int sizeOf(int v1) {
      return size[parent[v1]];
    }

    public int find(int v1) {
        if (v1 < 0 || v1 >= parent.length) {
            throw new IndexOutOfBoundsException();
        }
        while (v1 != parent[v1]) {
            parent[v1] = parent[parent[v1]]; // implements path compression
            v1 = parent[v1];
        }
        return v1;
    }

    /**
     * Return the number of disjoint sets.
     */
    public int count() {
        return count;
    }

    /**
     * Are objects p and q in the same set?
     *
     * @throws java.lang.IndexOutOfBoundsException
     *             unless both 0 <= p < N and 0 <= q < N
     */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /**
     * Replace sets containing p and q with their union.
     *
     * @throws java.lang.IndexOutOfBoundsException
     *             unless both 0 <= p < N and 0 <= q < N
     */
    public void union(int v1, int v2) {
        int i = find(v1);
        int j = find(v2);
        if (i == j) {
            return;
        }

        // make smaller root point to larger one
        if (size[i] < size[j]) {
            parent[i] = j;
            size[j] += size[i];
        } else {
            parent[j] = i;
            size[i] += size[j];
        }
        count--;
    }

    public int[] getParent() {
      return parent;
    }

    public int[] getSize() {
      return size;
    }
}
