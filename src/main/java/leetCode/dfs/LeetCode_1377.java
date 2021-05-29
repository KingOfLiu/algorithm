package leetCode.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1377. T 秒后青蛙的位置
 *
 * 给你一棵由 n 个顶点组成的无向树，顶点编号从 1 到 n。青蛙从 顶点 1 开始起跳。规则如下：
 *  在一秒内，青蛙从它所在的当前顶点跳到另一个 未访问 过的顶点（如果它们直接相连）。
 *  青蛙无法跳回已经访问过的顶点。
 *  如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。
 *  如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。
 * 无向树的边用数组 edges 描述，其中 edges[i] = [fromi, toi] 意味着存在一条直接连通 fromi 和 toi 两个顶点的边。
 * 返回青蛙在 t 秒后位于目标顶点 target 上的概率。
 *
 *
 * 示例 1：
 * 输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
 * 输出：0.16666666666666666
 * 解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，第 1 秒 有 1/3 的概率跳到顶点 2 ，然后第 2 秒 有 1/2 的概率跳到顶点 4，因此青蛙在 2 秒后位于顶点 4 的概率是 1/3 * 1/2 = 1/6 = 0.16666666666666666 。
 *
 * 示例 2：
 * 输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
 * 输出：0.3333333333333333
 * 解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，有 1/3 = 0.3333333333333333 的概率能够 1 秒 后跳到顶点 7 。
 *
 * 示例 3：
 * 输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 20, target = 6
 * 输出：0.16666666666666666
 *
 *
 * 提示：
 *  1>. 1 <= n <= 100
 *  2>. edges.length == n-1
 *  3>. edges[i].length == 2
 *  4>. 1 <= edges[i][0], edges[i][1] <= n
 *  5>. 1 <= t <= 50
 *  6>. 1 <= target <= n
 *  7>. 与准确值误差在 10^-5 之内的结果将被判定为正确。
 * */
public class LeetCode_1377 {
    Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
    public double frogPosition(int n, int[][] edges, int t, int target) {
        for(int[] edge : edges){
            int e0 = edge[0], e1 = edge[1];
            graph.putIfAbsent(e0, new ArrayList<>());
            graph.putIfAbsent(e1, new ArrayList<>());

            graph.get(e0).add(e1);
            graph.get(e1).add(e0);
        }

        return dfs(1, 0, t, target);
    }

    /**
     * dfs含义：青蛙如果跳到这个节点，它因为跳到这个节点最终停在target的概率
     *
     * @param u 当前节点
     * @param fa 父节点
     * @param t 时间
     * @param target 目标
     * */
    double dfs(int u, int fa, int t, int target){
        int size = graph.get(u).size();

        // t == 0   表示跳完了。
        // fa != 0 且 size == 1 表示是叶子节点了。
        if(t == 0 || (fa != 0 && size == 1)){
            // 判断当前节点是不是 目标节点：如果是，返回1，否则0
            if(u == target){
                return 1;
            } else {
                return 0;
            }
        }

        // p表示当前节点的概率
        // fa == 0 表示是根节点，所以下面有size个节点
        // fa != 0 表示是其他非叶子节点，因为整个节点对应的集合中是包含父节点的，所以size要减一，减去父节点的数量
        double p = 1.0 / (fa != 0? size - 1 : size), max = 0;

        // 依次遍历当前节点的子节点
        for(int i = 0; i < size; i++){
            int v = graph.get(u).get(i);

            // 表示如果遍历的当前指针指向父节点，直接跳过
            if(v == fa){
                continue;
            }

            // dfs的方式继续访问下一个层级的节点，并且跳跃的次数要减一
            double next = dfs(v, u, t - 1, target);

            // 下一个层级的概率如果不是0，就获取下一个层级的概率，并跳出循环
            if(next > 0){
                max = next;
                break;
            }
        }

        // 统计当前节点u的概率：max表示当前节点子节点的概率
        return p * max;
    }

    public static void main(String[] args){
        int n = 7;
        int[][] edges = {{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}};
        int t = 2;
        int target = 4;
        LeetCode_1377 solution = new LeetCode_1377();
        double ans = solution.frogPosition(n, edges, t, target);
        System.out.println(ans);
    }
}
