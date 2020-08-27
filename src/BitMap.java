import java.util.ArrayList;
import java.util.List;

public class BitMap {
    // 需要存放的数据个数
    private static final int N = 200000000;
    // 用一个 int 类型的数组作为位图，一个int最多可以存放32个整型数（4字节32位！）
    // 相当于压缩了 32 倍的空间！
    private int[] a = new int[N >> 32 + 1];

    /**
     * 设置 n 所在的bit位为 1
     *
     * @param n
     */
    public void addValue(int n) {
        // 计算 n 在数组 a 中的下标
        int row = n >> 5;
        // 计算 n 在a[row] 元素中的下标
        // 相当于 a[row] = a[row] | (2^(n%32))
        a[row] |= 1 << (n & 0x1F);
    }

    public boolean isExist(int n) {
        /**
         * 判断 n 所在比特位是否为1
         */
        int row = n >> 5;
        return (a[row] & (1 << (n & 0x1F))) != 0;
    }

    public void display(int begin, int end) {
        /**
         * 展示部分位图
         */
        System.out.println("BitMap位图展示");
        for (int i = begin; i < end; i++) {
            List<Integer> list = new ArrayList<Integer>();
            int temp = a[i];
            for (int j = 0; j < 32; j++) {
                list.add(temp & 1);
                temp >>= 1;
            }
            System.out.println("a[" + i + "]" + list);
        }
    }

    public static void main(String[] args) {
        BitMap bm = new BitMap();
        bm.addValue(12);
        bm.addValue(100000000);
        System.out.println(bm.isExist(100000000));
        bm.display(0, 1);
    }

}
