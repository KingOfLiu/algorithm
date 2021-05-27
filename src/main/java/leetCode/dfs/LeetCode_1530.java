package leetCode.dfs;

import common.TreeNode;
import common.TreeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 1530. 好叶子节点对的数量
 * 给你二叉树的根节点 root 和一个整数 distance 。
 * 如果二叉树中两个 叶 节点之间的 最短路径长度 小于或者等于 distance ，那它们就可以构成一组 好叶子节点对 。
 * 返回树中 好叶子节点对的数量 。
 *
 * 示例 1：
 * 输入：root = [1,2,3,null,4], distance = 3
 * 输出：1
 * 解释：树的叶节点是 3 和 4 ，它们之间的最短路径的长度是 3 。这是唯一的好叶子节点对。
 *
 * 示例 2：
 * 输入：root = [1,2,3,4,5,6,7], distance = 3
 * 输出：2
 * 解释：好叶子节点对为 [4,5] 和 [6,7] ，最短路径长度都是 2 。但是叶子节点对 [4,6] 不满足要求，因为它们之间的最短路径长度为 4 。
 *
 * 示例 3：
 * 输入：root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
 * 输出：1
 * 解释：唯一的好叶子节点对是 [2,5] 。
 *
 * 示例 4：
 * 输入：root = [100], distance = 1
 * 输出：0
 *
 * 示例 5：
 * 输入：root = [1,1,1], distance = 2
 * 输出：1
 *
 *
 *
 * 提示：
 *  1> tree 的节点数在 [1, 2^10] 范围内。
 *  2> 每个节点的值都在 [1, 100] 之间。
 *  3> 1 <= distance <= 10
 * */
public class LeetCode_1530 {
    /*class Pair {
        int[] depths;
        int count;

        Pair(int[] depths, int count){
            this.depths = depths;
            this.count = count;
        }
    }

    Pair dfs(TreeNode root, int distance){
        int[] depths = new int[distance + 1];
        boolean isLeaf = root.left == null && root.right == null;
        if(isLeaf){
            depths[0] = 1;
            return new Pair(depths, 0);
        }

        int[] leftDepths = new int[distance + 1];
        int[] rightDepths = new int[distance + 1];
        int leftCount = 0, rightCount = 0;
        if(root.left != null){
            Pair leftPair = dfs(root.left, distance);
            leftDepths = leftPair.depths;
            leftCount = leftPair.count;
        }

        if(root.right != null){
            Pair rightPair = dfs(root.right, distance);
            rightDepths = rightPair.depths;
            rightCount = rightPair.count;
        }

        for(int i = 0; i < distance; i++){
            depths[i + 1] += leftDepths[i];
            depths[i + 1] += rightDepths[i];
        }

        int cnt = 0;
        for(int i = 0; i <= distance; i++){
            for(int j = 0; j + i + 2 <= distance; j++){
                cnt += leftDepths[i] * rightDepths[j];
            }
        }

        return new Pair(depths, cnt + leftCount + rightCount);
    }

    public int countPairs(TreeNode root, int distance) {
        Pair pair = dfs(root, distance);
        return pair.count;
    }*/

    private int ans = 0;

    public int countPairs(TreeNode root, int distance){
        dfs(root, distance);
        return ans;
    }

    private List<Integer> dfs(TreeNode node, int distance){
        List<Integer> res = new ArrayList<Integer>();
        if(node == null){
            return res;
        }

        if(node.left == null && node.right == null){
            res.add(1);
            return res;
        }

        List<Integer> leftRes = dfs(node.left, distance);
        for(int item : leftRes){
            if(++item > distance){
                continue;
            }

            res.add(item);
        }

        List<Integer> rightRes = dfs(node.right, distance);
        for(int item : rightRes){
            if(++item > distance){
                continue;
            }

            res.add(item);
        }

        for(int itemLeft : leftRes){
            for(int itemRight : rightRes){
                if(itemLeft + itemRight <= distance){
                    ans += 1;
                }
            }
        }

        return res;
    }

    public static void main(String[] args){
        TreeNode root = TreeUtil.covertToTreeNode("[1,2,3,4,5,6,7]");
        int distance = 3;

        LeetCode_1530 solution = new LeetCode_1530();
        int ans = solution.countPairs(root, distance);
        System.out.println(ans);
    }
}
