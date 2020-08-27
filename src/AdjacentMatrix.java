
import utils.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class AdjacentMatrix {
    int nums_nodes;
    int[][] mat;
    int[] visited;
    int[] mst;
    public AdjacentMatrix() {
        nums_nodes = 0;
        mat = null;
    }
    public AdjacentMatrix(int nums_nodes,int[][] mat) {
        this.nums_nodes = nums_nodes;
        this.mat = mat;
        this.visited = new int[nums_nodes];
        this.mst = new int[nums_nodes];
    }
    public void dfs(int s) {
        visited[s] = 1;
        for (int i = 0; i < nums_nodes; i++) {
            if (mat[s][i]!=0 && visited[i]!=1) {
                mst[i]=s;
                dfs(i);
            }
        }
    }
    public void bfs(int s) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            visited[current]=1;
            for (int i = 0; i < nums_nodes; i++) {
                if (visited[i]!=1) {
                    mst[i]=current;
                    queue.add(i);
                }
            }
        }
    }
    public void mst_kruskal() {

    }
    public int minIndex(int[] nums) {
        int minVal = nums[0];
        int minIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < minVal) {
                minVal = nums[i];
                minIndex= i;
            }
        }
        return minIndex;
    }
    public void mst_prim() {
        int[] mindist = new int[nums_nodes];
        Arrays.fill(mindist,Integer.MAX_VALUE);
        mindist[0]=0;
        for (int i = 0; i < nums_nodes; i++) {
            if (mat[0][i]!=0) {
                mindist[i] = mat[0][i];
            }
        }
        
    }
    public void dijkstra(int s) {

    }
    public void show() {
        Graphviz graphviz = new Graphviz("graphviz", "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe");
        graphviz.start_graph();
        for (int i = 0; i < mat.length; i++) {
            for (int j = i+1; j < mat[0].length; j++) {
                if (mat[i][j]!=0) {
                    graphviz.addEdge(i,j,mat[i][j]);
                }
            }
        }
        graphviz.end_graph();
        graphviz.run();
    }

    public void showMst(int[] mst) {
        Graphviz graphviz = new Graphviz("graphviz", "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe");
        graphviz.start_graph();
        for (int i = 0; i < mat.length; i++) {
            for (int j = i+1; j < mat[0].length; j++) {
                if (mat[i][j]!=0) {
                    if (mst[i]==j||mst[j]==i) {
                        graphviz.addRedEdge(i,j,mat[i][j]);
                    } else {
                        graphviz.addEdge(i, j, mat[i][j]);
                    }
                }
            }
        }
        graphviz.end_graph();
        graphviz.run();
    }

    public static void main(String[] args) {
        int[][] mat = {{0,5,1,2},{5,0,3,4},{1,3,0,3},{2,4,3,0}};
        AdjacentMatrix adjacentMatrix = new AdjacentMatrix(4,mat);
        adjacentMatrix.show();
        adjacentMatrix.bfs(0);
        adjacentMatrix.showMst(adjacentMatrix.mst);
    }
}
