package leetCode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 * */
public class LeetCode_22 {
    public List<String> generateParenthesis(int n){
        List<String> res = new ArrayList<String>();

        // 特殊判断
        if(n == 0){
            return res;
        }

        // 执行深度优先遍历，搜索可能结果
        dfs("", n, n, res);
        return res;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left 左括号还有几个可以使用
     * @param right 有括号还有几个可以使用
     * @param res 结果集
     * */
    void dfs(String curStr, int left, int right, List<String> res){
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        // 在递归结束的时候，直接把它放入到结果集中
        if(left == 0 && right == 0){
            res.add(curStr);
            return ;
        }

        // 剪枝：左括号可以使用的个数严格大于右括号可以使用的个数
        if(left > right){
            return ;
        }

        if(left > 0){
            dfs(curStr + "(", left - 1, right, res);
        }

        if(right > 0){
            dfs(curStr + ")", left, right - 1, res);
        }
    }

    public static void main(String[] args){
        int n = 3;
        LeetCode_22 solution = new LeetCode_22();
        List<String> ans = solution.generateParenthesis(n);
        System.out.println(ans);
    }
}
