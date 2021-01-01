package leetCode;

import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树寻路
 * 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。
 *
 * 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
 * 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
 *
 * 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，
 * 该路径是由途经的节点标号所组成的。
 * */
public class LeetCode_1104 {
    public List<Integer> pathInZigZagTree(int label){
        LinkedList<Integer> ans = new LinkedList<>();
        if(label == 1){
            ans.offer(1);
            return ans;
        }

        int level = 0;
        int tLabel = label;
        while(tLabel != 0){
            tLabel >>= 1;
            level++;
        }

        while(label > 1){
            ans.offerFirst(label);
            label = (int) (Math.pow(2, level - 1)) - 1 + (int) (Math.pow(2, level - 2)) - (label / 2);
            level--;
        }
        ans.offerFirst(1);
        return ans;
    }

    public static void main(String[] args){
        int label = 1;
        LeetCode_1104 solution = new LeetCode_1104();
        List<Integer> ans = solution.pathInZigZagTree(label);
        System.out.println(ans);
    }
}
