package leetCode.tries;

/**
 * 1707. 与数组中元素的最大异或值
 * 给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
 * 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，
 * 答案是 max(nums[j] XOR xi) ，其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，
 * 最终答案就是 -1 。
 * 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个查询的答案。
 *
 * 示例 1：
 * 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
 * 输出：[3,3,7]
 * 解释：
 * 1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
 * 2) 1 XOR 2 = 3.
 * 3) 5 XOR 2 = 7.
 *
 * 示例 2：
 * 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
 * 输出：[15,-1,5]
 *
 *
 * 提示：
 *
 * 1 <= nums.length, queries.length <= 10^5
 * queries[i].length == 2
 * 0 <= nums[j], xi, mi <= 10^9
 * */
public class LeetCode_1707 {
    static final int HIGH_BIT = 30;

    class TrieNode {
        TrieNode[] child = new TrieNode[2];
        int min = Integer.MAX_VALUE;
    }

    final TrieNode root = new TrieNode();

    void insert(int num){
        TrieNode node = root;
        for(int i = HIGH_BIT; i >= 0; i--){
            int index = num >>> i & 1;
            if(node.child[index] == null){
                node.child[index] = new TrieNode();
            }
            node = node.child[index];
            node.min = Math.min(node.min, num);
        }
    }

    int query(int x, int m){
        int xor = 0;
        TrieNode node = root;
        for(int i = HIGH_BIT; i >= 0; i--){
            int index = x >>> i & 1;
            if(node.child[index ^ 1] != null && node.child[index ^ 1].min <= m){
                xor |= 1 << i;
                node = node.child[index ^ 1];
            } else if(node.child[index] != null && node.child[index].min <= m){
                node = node.child[index];
            } else {
                return -1;
            }
        }

        return xor;
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {
        for(int num : nums){
            insert(num);
        }
        int n = queries.length;
        int[] ans = new int[n];
        for(int i = 0; i < n; i++){
            ans[i] = query(queries[i][0], queries[i][1]);
        }

        return ans;
    }

    public static void main(String[] args){
        /*int[] nums = {5,2,4,6,6,3};
        int[][] queries = {{12,4}, {8,1}, {6,3}};*/

        int[] nums = {0,1,2,3,4};
        int[][] queries = {{3,1},{1,3},{5,6}};

        LeetCode_1707 solution = new LeetCode_1707();
        int[] ans = solution.maximizeXor(nums, queries);
        for(int s : ans){
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
