public class DisjointSet {
    private int count;
    private int[] s;

    public DisjointSet(int count) {
        this.count = count;
        s = new int[count];
        for (int i = 0; i < count; i++) {
            s[i] = -1;
        }
    }

    public void union(int num1, int num2) {
        // O(n)
        // 必须对其祖先节点进行操作！
        int f1 = find(num1);
        int f2 = find(num2);
        if (f1 != f2) {
            s[f1] = f2;
        }
    }

    public int find(int num) {
        /**
         * 1. 如果并查集初始化微元素自己nums[i] = i, 此时find 不需要将-1单独拿出来的
         * 2. 如果并查集初始为一个特殊值，比如0，或-1， 此时需要将下面这种特殊情况单独拿出来，
         */
        // O(1)
        if (s[num]==-1) {
            return num;
        }
        return s[num] = find(s[num]);
    }
}
