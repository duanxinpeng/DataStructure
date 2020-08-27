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
        int f1 = find(num1);
        int f2 = find(num2);
        if (f1 != f2) {
            s[f1] = f2;
        }
    }

    public int find(int num) {
        // O(1)
        if (s[num]==-1) {
            return num;
        }
        return s[num] = find(s[num]);
    }
}
