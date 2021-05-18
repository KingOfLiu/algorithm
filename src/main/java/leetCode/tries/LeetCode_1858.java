package leetCode.tries;

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
    class TrieNode{
        TrieNode[] child = new TrieNode[256];
        String word = null;
        boolean isEnd = false;
    }

    class Trie{
        TrieNode root = new TrieNode();

        public void insert(String word){
            TrieNode node = root;
            for(int i = 0; i < word.length(); i++){
                char ch = word.charAt(i);
                int index = ch - ' ';
                if(node.child[index] == null){
                    node.child[index] = new TrieNode();
                }
                node = node.child[index];
            }
            node.isEnd = true;
            node.word = word;
        }

        public TrieNode search(String prefix){
            TrieNode node = root;
            for(int i = 0; i < prefix.length(); i++){
                char ch = prefix.charAt(i);
                int index = ch - ' ';
                if(node.child[index] == null){
                    return null;
                }
                node = node.child[index];
            }

            return node;
        }
    }

    public String longestWord(String[] words) {
        Trie trie = new Trie();
        for(String word : words){
            trie.insert(word);
        }

        TrieNode node = trie.search("ap");
        System.out.println(node);
        return null;
    }

    public static void main(String[] args){
        String[] words = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        LeetCode_1858 solution = new LeetCode_1858();
        String ans = solution.longestWord(words);
        System.out.println(ans);
    }
}
