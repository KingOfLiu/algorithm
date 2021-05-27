package leetCode.tries;

import java.util.ArrayList;
import java.util.List;

/**
 * 472. 连接词
 * 给定一个 不含重复 单词的字符串数组 words ，编写一个程序，返回 words 中的所有 连接词 。
 * 连接词 的定义为：一个字符串完全是由至少两个给定数组中的单词组成的。
 *
 * 示例 1：
 * 输入：words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * 输出：["catsdogcats","dogcatsdog","ratcatdogcat"]
 * 解释："catsdogcats"由"cats", "dog" 和 "cats"组成;
 *      "dogcatsdog"由"dog", "cats"和"dog"组成;
 *      "ratcatdogcat"由"rat", "cat", "dog"和"cat"组成。
 *
 * 示例 2：
 * 输入：words = ["cat","dog","catdog"]
 * 输出：["catdog"]
 *
 *
 * 提示：
 * 1>. 1 <= words.length <= 10^4
 * 2> .0 <= words[i].length <= 1000
 * 3>. words[i] 仅由小写字母组成
 * 4> 0 <= sum(words[i].length) <= 6 * 10^5
 * */
public class LeetCode_472 {
    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isEnd = false;
        int cnt = 0 ;
    }

    final TrieNode root = new TrieNode();

    void insert(String word){
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++){
            int index = word.charAt(i) - 'a';
            if(node.child[index] == null){
                node.child[index] = new TrieNode();
            }
            node = node.child[index];
        }
        node.isEnd = true;
        node.cnt++;
    }


    List<String> ans = new ArrayList<>();
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        if(words.length == 0){
            return ans;
        }

        for(String word : words){
            if(word.length() != 0){
                insert(word);
            }
        }

        for(String word : words){
            if(dfs(word, root, 0, 0)){
                ans.add(word);
            }
        }
        return ans;
    }

    boolean dfs(String word, TrieNode node, int count, int index){
        for(int i = index; i < word.length(); i++){
            int pos = word.charAt(i) - 'a';
            if(node.child[pos] == null){
                return false;
            }
            node = node.child[pos];

            if(node.isEnd && dfs(word, root, count + 1, i + 1)){
                return true;
            }
        }

        return count > 0 && node.isEnd;
    }

    public static void main(String[] args){
        String[] words = {"catsdogcats","cat","cats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};

        LeetCode_472 solution = new LeetCode_472();
        List<String> ans = solution.findAllConcatenatedWordsInADict(words);
        System.out.println(ans);
    }
}
