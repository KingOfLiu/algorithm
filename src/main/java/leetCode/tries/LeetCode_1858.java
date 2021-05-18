package leetCode.tries;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1858. Longest Word With All Prefixes
 *
 * Given an array of strings words, find the longest string in
 * words such that every prefix of it is also in words.
 *  For example, let words = ["a", "app", "ap"].
 *  The string "app" has prefixes "ap" and "a",
 *  all of which are in words.
 *
 * Return the string described above. If there is more than one
 * string with the same length, return the lexicographically
 * smallest one, and if no string exists, return "".
 *
 *
 * Example 1:
 * Input: words = ["k","ki","kir","kira", "kiran"]
 * Output: "kiran"
 * Explanation: "kiran" has prefixes "kira", "kir", "ki", and "k",
 * and all of them appear in words.
 *
 * Example 2:
 * Input: words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * Output: "apple"
 * Explanation: Both "apple" and "apply" have all their prefixes in words.
 * However, "apple" is lexicographically smaller, so we return that.
 *
 * Example 3:
 * Input: words = ["abc", "bc", "ab", "qwe"]
 * Output: ""
 *
 *
 *
 * Constraints:
 * 1 <= words.length <= 10^5
 * 1 <= words[i].length <= 10^5
 * 1 <= sum(words[i].length) <= 10^5
 * */
public class LeetCode_1858 {
    class TrieNode {
        TrieNode[] child = new TrieNode[128];
        boolean hasPrefix = false;

        TrieNode(){ }

        TrieNode(boolean hasPrefix){
            this.hasPrefix = hasPrefix;
        }
    }

    class Trie{
        TrieNode root = new TrieNode(true);

        public TrieNode insert(String word){
            TrieNode node = root;
            for(int i = 0; i < word.length(); i++){
                char ch = word.charAt(i);
                int index = ch - ' ';
                if(!node.hasPrefix){
                    return null;
                }

                if(node.child[index] == null){
                    node.child[index] = new TrieNode();
                }
                node = node.child[index];
            }

            return node;
        }
    }

    public String longestWord(String[] words) {
        Arrays.sort(words, new Comparator<String>(){
            public int compare(String s1, String s2){
                return s1.length() - s2.length();
            }
        });

        String ans = "";
        Trie trie = new Trie();
        for(String word : words){
            TrieNode node = trie.insert(word);
            if(node == null){
                continue;
            }

            node.hasPrefix = true;
            if(ans.length() < word.length() || (ans.length() == word.length() && ans.compareTo(word) > 0)){
                ans = word;
            }
        }
        return ans;
    }

    public static void main(String[] args){
        String[] words = {"k","ki","kir","kira", "kiran"};
        LeetCode_1858 solution = new LeetCode_1858();
        String ans = solution.longestWord(words);
        System.out.println(ans);
    }
}
