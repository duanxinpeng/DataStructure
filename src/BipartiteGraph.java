public class BipartiteGraph {

    /**
     *
     * @param i : 左边元素 
     * @param map : 表示图的矩阵
     * @param p : 记录结果（右边的元素对应的左边的元素）
     * @param vis ： 记录右边的元素在本轮寻找增广路径过程中是否被访问过
     * @return
     */
    public static boolean dfs_hungarian(int i, int[][] map, int[] p, boolean[] vis) {
        int m = map.length;
        int n = map[0].length;
        for (int j = 0; j < n; j++) {
            if (map[i][j]!=0 && !vis[j]) { // 要求和i相连，且不在交替路中
                vis[j] = true; // 放入交替路中
                if (p[j] == 0 || dfs_hungarian(p[j],map,p,vis)) {
                    /**
                     * 1. 未覆盖点和未覆盖点组成增广路
                     * 2. 未覆盖点和已覆盖点组成增广路
                     * 3. 交换路径，并返回 true
                     */
                    p[j] = i;
                    return true;
                }
            }
        }
        return false;
    }
    public static int hungarian(int[][] map) {
        /**
         * 匈牙利算法
         * https://zhuanlan.zhihu.com/p/96229700
         * 既可以用于最大匹配，也可以用于最小覆盖（寻找最少数量的顶点，覆盖所有边）
         * 有两种理解方式
         *      1. 寻找增广路径的理解方式
         *      2. 回溯的理解方式
         */
        // 左侧 元素个数
        int m = map.length;
        // 右侧元素个数
        int n = map[0].length;
        // 记录右侧元素对应的左侧元素
        int[] p = new int[n];
        // 记录右侧元素是否已经被遍历过
        boolean[] vis;
        int cnt = 0;
        for (int i = 1; i < m; i++) {
            vis = new boolean[n];
            // 每找到一条增光路径就会将匹配的数量加1
            if (dfs_hungarian(i,map,p,vis))
                cnt++;
        }
        return cnt;
    }


    // KM算法，计算加权二分图最大匹配问题
    public static boolean dfs_km(int girl,int[] slack, int[] match, int[] expire_girl, int[] expire_boy, boolean[] vis_girl,boolean[] vis_boy, int[][]map) {
        vis_girl[girl] = true;
        for (int i = 0; i < map[0].length; i++) {
            if (vis_boy[i])
                continue;
            int gap = expire_girl[girl] + expire_boy[i] - map[girl][i];
            if (gap == 0) { // 符合要求
                vis_boy[i] = true;
                if (match[i] == -1 || dfs_km(match[i],slack,match,expire_girl,expire_boy,vis_girl,vis_boy,map)) {
                    match[i] = girl;
                    return true;
                }
            } else {
                slack[i] = Math.min(slack[i],gap); // slack 可以理解为男生要得到女生的倾心，还需要多少期望值。
            }
        }
        return false;
    }

    public static int km(int[][] map) {
        // 女生数量
        int m = map.length;
        // 男生数量
        int n = map[0].length;
        int[] match = new int[n];
        for (int i = 0; i < n; i++) {
            match[i] = -1;
        }
        // 男生期望值初始化为0
        int[] expire_boy = new int[n];
        // 女生期望值初始化为和她相连的男生的最大好感度
        int[] expire_girl = new int[m];
        for (int i = 0; i < m; i++) {
            expire_girl[i] = map[i][0];
            for (int j = 0; j < n; j++) {
                expire_girl[i] = Math.max(expire_girl[i],map[i][j]);
            }
        }
        int[] slack = new int[m];
        boolean[] visit_girl;
        boolean[] visit_boy;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                // 因为要取最小值，所以初始化为无穷大
                slack[j] = Integer.MAX_VALUE;
            }
            while (true) {
                // 为每个女生解决归宿问题，如果找不到就降低期望值，直到找到为止
                visit_girl = new boolean[m];
                visit_boy = new boolean[n];
                if (dfs_km(i,slack,match,expire_girl,expire_boy,visit_girl,visit_boy,map))
                    break; // 找到归宿 退出
                // 如果不能找到，就降低期望
                // 最小可降低的期望值
                int d = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++) {
                    if (!visit_boy[j])
                        d = Math.min(d,slack[j]);
                }
                for (int j = 0; j < m; j++) {
                    if (visit_girl[j])
                        expire_girl[j] -= d;
                }
                for (int j = 0; j < n; j++) {
                    if (visit_boy[j])
                        expire_boy[j] += d;
                    else
                        slack[j] -= d;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            res += map[match[i]][i];
        }
        return res;
    }

    public static void main(String[] args) {
        boolean[] test ;
        for (int i = 0; i < 6; i++) {
            test = new boolean[5];
            for (int j = 0; j < 5; j++) {
                System.out.println(test[j]);
            }
            for (int j = 0; j < 5; j++) {
                test[j] = true;
            }
        }
    }
}
