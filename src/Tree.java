import sun.awt.image.ImageWatched;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
public class Tree {
    public void preOrderRecursion(TreeNode root) {
        if (root != null) {
            System.out.print(root.val+" ");
            preOrderRecursion(root.left);
            preOrderRecursion(root.right);
        }
    }
    public void preOrderIteration(TreeNode root) {
        if (root == null)
            return;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.push(root);
        while (!deque.isEmpty()) {
            TreeNode out = deque.pop();
            System.out.print(out.val+" ");
            if (out.right != null)
                deque.push(out.right);
            if (out.left != null)
                deque.push(out.left);
        }
    }
    public void preOrderMorris(TreeNode root) {
        // 一个循环，循环回来时，就以为这左子树遍历完毕
        // 在进入循环之前打印就是前序遍历，之后打印就是中序遍历
        TreeNode cur = root, pre = null;
        while (cur != null) {
            if (cur.left == null) {
                System.out.print(cur.val+" ");
                cur = cur.right;
            } else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = cur;
                    System.out.print(cur.val+" ");
                    cur = cur.left;
                } else {
                    pre.right = null;
                    cur = cur.right;
                }
            }
        }
    }

    public void inOrderRecursion(TreeNode root) {
        if (root != null) {
            inOrderRecursion(root.left);
            System.out.print(root.val+" ");
            inOrderRecursion(root.right);
        }
    }
    public void inOrderIteration(TreeNode root) {
        if (root == null)
            return;
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode in = root;
        while (in != null || !deque.isEmpty()) {
            while (in != null) {
                deque.push(in);
                in = in.left;
            }
            TreeNode out = deque.pop();
            System.out.print(out.val+" ");
            in = out.right;
        }
    }
    public void inOrderMorris(TreeNode root) {
        TreeNode cur = root, pre = null;
        while (cur != null) {
            if (cur.left == null) {
                System.out.print(cur.val+" ");
                cur = cur.right;
            } else {
                // 左孩子或者左孩子的最右节点是根节点的前驱节点
                pre = cur.left;
                while (pre.right != null && pre.right != cur)
                    pre = pre.right;
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    // pre.right == cur
                    // 说明循环一轮之后，再次循环回来了，说明左子树已经遍历完成
                    // 所以应该遍历中间节点和右子树
                    pre.right = null;
                    System.out.print(cur.val+" ");
                    cur = cur.right;
                }
            }
        }
    }

    public void postOrderRecursion(TreeNode root) {
        if (root != null) {
            postOrderRecursion(root.left);
            postOrderRecursion(root.right);
            System.out.print(root.val+" ");
        }
    }
    public void postOrderIteration(TreeNode root) {
        //把结构给改了！
        if (root == null)
            return;
        TreeNode in = root;
        Deque<TreeNode> deque = new ArrayDeque<>();
        while (in != null || !deque.isEmpty()) {
            while (in != null) {
                deque.push(in);
                in = in.left;
            }
            TreeNode out = deque.peek();
            if (out.right == null) {
                System.out.print(out.val+" ");
                deque.pop();
            } else {
                in = out.right;
                out.right = null;
            }
        }
    }
    public void postOrderMorris(TreeNode root) {
        TreeNode dump = new TreeNode(0);
        dump.left = root;
        TreeNode cur = dump, pre = null;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    Deque<Integer> tmp = new ArrayDeque<>();
                    TreeNode p = cur.left;
                    while (p != cur) {
                        tmp.push(p.val);
                        p = p.right;
                    }
                    while (!tmp.isEmpty()) {
                        System.out.print(tmp.pop()+" ");
                    }
                    pre.right = null;
                    cur = cur.right;
                }
            }
        }
    }
    public void postOrderDFS(TreeNode root) {
        // 相当于逆前序遍历
        // 和前序遍历唯一的区别在于先将左子树入栈，再将右子树入栈！
        LinkedList<Integer> list = new LinkedList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        if (root == null)
            return;
        deque.push(root);
        while (!deque.isEmpty()) {
            TreeNode tmp = deque.pop();
            list.addFirst(tmp.val);
            if (tmp.left != null) {
                deque.push(tmp.left);
            }
            if (tmp.right != null) {
                deque.push(tmp.right);
            }
        }
        for (Integer value : list) {
            System.out.print(value+" ");
        }
    }

    public void levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null)
            return;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            System.out.print(tmp.val+" ");
            if (tmp.left != null)
                queue.offer(tmp.left);
            if (tmp.right != null)
                queue.offer(tmp.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        Tree t = new Tree();
        System.out.println("层次遍历");
        t.levelOrder(root);
        System.out.println();
        System.out.println("前序遍历");
        t.preOrderRecursion(root);
        System.out.println();
        t.preOrderIteration(root);
        System.out.println();
        t.preOrderMorris(root);
        System.out.println();
        System.out.println("中序遍历");
        t.inOrderRecursion(root);
        System.out.println();
        t.inOrderIteration(root);
        System.out.println();
        t.inOrderMorris(root);
        System.out.println();
        System.out.println("后序遍历");
        t.postOrderRecursion(root);
        System.out.println();
        t.postOrderMorris(root);
        System.out.println();
        t.postOrderDFS(root);
        System.out.println();
        t.postOrderIteration(root);
        System.out.println();

    }
}
