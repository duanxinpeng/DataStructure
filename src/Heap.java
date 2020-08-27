import java.util.PriorityQueue;

public class Heap {
    /**
     * parent = (child-1)/2;
     * left = parent*2+1
     * right = parent*2+2
     */
    private int[] queue;
    private int capacity;
    private int size;
    public Heap(){
        this.capacity = 10;
        this.queue = new int[10];
        this.size = 0;
    }
    public Heap(int[] arr) {
        this.queue = arr;
        this.capacity = arr.length;
        this.size = arr.length;
    }
    public void heapify() {
        /**
         * 时间复杂度 O(n)
         * 1/2 个元素需要向下比较 1 次，
         * 1/4 个元素需要向下比较 2 次
         * 1/8 个元素需要向下比较3次，
         * sum(0,logN) 1/2^k*K < 2
         *
         * 堆排序复杂度：lgo2+log3+log4+log5 ..+ logN = log(N!) = nlogn
         */
        int n =this.size, i = (n-2) >>> 1; // 最后一个非叶子节点
        for (; i >= 0; i--) {   // 从最后一个非叶子节点到根节点！
            int k = i;
            int half = this.size >>> 1;
            int key = this.queue[i];  // 需要下沉的元素！
            while (k < half) {
                int child = (k << 1) + 1; // 默认是左孩子！
                int right = child + 1;
                if (right < n && this.queue[right] < queue[child]) //右孩子存在且比左孩子小，将孩子转换为右孩子
                    child = right;
                if (key <= this.queue[child])  // 已经找到下沉位置了！
                    break;  // 如果比左右孩子都小的话，就可以结束了！
                this.queue[k] = this.queue[child];
                k = child;
            }
            // 将下沉元素放到应在的下沉位置处！
            this.queue[k] = key;
        }
    }
    public void offer (int num) {
        this.queue[this.size] = num;
        int k = size;
        this.size++;
        while (k > 0) {
            int parent = (k-1) >>> 2;
            if (num >= this.queue[parent])  // 已经找到上浮位置！
                break;
            this.queue[k] = this.queue[parent];
            k = parent;
        }
        this.queue[k] = num;
    }
    public void poll() {
        int key = this.queue[--this.size]; //用最后一个元素代替头元素，并将size减一！得到需要下沉的元素
        int k = 0;
        int half = this.size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            int right = child + 1;
            if (right < this.size && this.queue[right] < this.queue[child]) {
                child = right;
            }
            if (key <= this.queue[child])
                break;
            this.queue[k] = this.queue[child];
            k = child;
        }
        this.queue[k] = key;
    }

}
