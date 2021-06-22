package offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * */
public class Offer_38 {
    List<String> rec;
    boolean[] visited;

    public String[] permutation(String s) {
        int n = s.length();
        rec = new ArrayList<String>();
        visited = new boolean[n];
        char[] chs = s.toCharArray();
        Arrays.sort(chs);

        StringBuffer perm = new StringBuffer();
        dfs(chs, 0, n, perm);

        int size = rec.size();
        String[] ans = new String[size];
        for(int i = 0; i < size; i++){
            ans[i] = rec.get(i);
        }

        return ans;
    }

    void dfs(char[] chs, int i, int n, StringBuffer perm){
        if(i == n){
            rec.add(perm.toString());
            return;
        }

        for(int j = 0; j < n; j++){
            // 排序之后去重处理
            if(visited[j] || (j > 0 && !visited[j - 1] && chs[j - 1] == chs[j])){
                continue;
            }

            visited[j] = true;
            perm.append(chs[j]);
            dfs(chs, i + 1, n, perm);
            perm.deleteCharAt(perm.length() - 1);
            visited[j] = false;
        }
    }

    public static void main(String[] args){
        String s = "abc";
        Offer_38 solution = new Offer_38();
        String[] ans = solution.permutation(s);
        for(String str : ans){
            System.out.print(str + " ");
        }
        System.out.println();
    }
}
