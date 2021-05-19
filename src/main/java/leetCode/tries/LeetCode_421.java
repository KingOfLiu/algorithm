package leetCode.tries;

/**
 * 421. 数组中两个数的最大异或值
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * 进阶：你可以在 O(n) 的时间解决这个问题吗？
 *
 * 示例 1：
 * 输入：nums = [3,10,5,25,2,8]
 * 输出：28
 * 解释：最大运算结果是 5 XOR 25 = 28.
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：0
 *
 * 示例 3：
 * 输入：nums = [2,4]
 * 输出：6
 *
 * 示例 4：
 * 输入：nums = [8,10,2]
 * 输出：10
 *
 * 示例 5：
 * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * 输出：127
 *
 * 提示：
 *  1 <= nums.length <= 2 * 10^4
 *  0 <= nums[i] <= 2^31 - 1
 * */
public class LeetCode_421 {
    class TrieNode{
        /**
         * 0
         * */
        TrieNode left = null;

        /**
         * 1
         * */
        TrieNode right = null;
    }

    TrieNode root = new TrieNode();

    static int HIGH_BIT = 30;

    private void insert(int num){
        TrieNode node = root;
        for(int i = HIGH_BIT; i >= 0; i--){
            int bit = (num >> i) & 1;
            if(bit == 0){
                if(node.left == null){
                    node.left = new TrieNode();
                }
                node = node.left;
            } else {
                if(node.right == null){
                    node.right = new TrieNode();
                }
                node = node.right;
            }
        }
    }

    private int search(int num){
        TrieNode node = root;
        int x = 0;
        for(int i = HIGH_BIT; i >= 0; i--){
            int bit = (num >> i) & 1;
            if(bit == 0){
                if(node.right != null){
                    node = node.right;
                    x = x * 2 + 1;
                } else {
                    node = node.left;
                    x = x * 2;
                }
            } else {
                if(node.left != null){
                    node = node.left;
                    x = x * 2 + 1;
                } else {
                    node = node.right;
                    x = x * 2;
                }
            }
        }

        return x;
    }

    public int findMaximumXOR(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for(int i = 1; i < n; i++){
            insert(nums[i - 1]);
            ans = Math.max(ans, search(nums[i]));
        }
        return ans;
    }

    public static void main(String[] args){
        int[] nums = {14,70,53,83,49,91,36,80,92,51,66,70};
        LeetCode_421 solution = new LeetCode_421();
        int ans = solution.findMaximumXOR(nums);
        System.out.println(ans);
    }
}
