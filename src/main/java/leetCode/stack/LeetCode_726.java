package leetCode.stack;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.TreeMap;

/**
 * 726. 原子的数量
 * 给定一个化学式formula（作为字符串），返回每种原子的数量。
 * 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
 * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
 * 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。
 * 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
 * 给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
 *
 * 示例 1:
 * 输入:
 * formula = "H2O"
 * 输出: "H2O"
 * 解释:
 * 原子的数量是 {'H': 2, 'O': 1}。
 *
 * 示例 2:
 * 输入:
 * formula = "Mg(OH)2"
 * 输出: "H2MgO2"
 * 解释:
 * 原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
 *
 * 示例 3:
 * 输入:
 * formula = "K4(ON(SO3)2)2"
 * 输出: "K4N2O14S4"
 * 解释:
 * 原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
 *
 *
 * 注意:
 * 所有原子的第一个字母为大写，剩余字母都是小写。
 * formula的长度在[1, 1000]之间。
 * formula只包含字母、数字和圆括号，并且题目中给定的是合法的化学式。
 * */
public class LeetCode_726 {
    public String countOfAtoms(String formula) {
        ArrayDeque<Map<String, Integer>> stack = new ArrayDeque<Map<String, Integer>>();
        stack.push(new TreeMap<String, Integer>());

        int n = formula.length();
        int i = 0;
        while(i < n){
            char ch = formula.charAt(i);
            if(ch == '('){
                stack.push(new TreeMap<String, Integer>());
                i++;
            } else if(ch == ')'){
                int numIdx = i + 1;
                int num = 0;
                while(numIdx < n && Character.isDigit(formula.charAt(numIdx))){
                    num = num * 10 + (formula.charAt(numIdx) - '0');
                    numIdx++;
                }

                Map<String, Integer> prevLevelMap = stack.poll();
                Map<String, Integer> curLevelMap = stack.peek();

                for(Map.Entry<String, Integer> entry : prevLevelMap.entrySet()){
                    String prevKey = entry.getKey();
                    int prevCnt = entry.getValue();
                    int curCnt = curLevelMap.getOrDefault(prevKey, 0);
                    curLevelMap.put(prevKey, curCnt + ((num == 0 ? 1 : num) * prevCnt));
                }

                i = numIdx;
            } else {
                StringBuffer curAtomName = new StringBuffer();
                curAtomName.append(ch);

                int next = i + 1;
                if(next == n){
                    int cnt = stack.peek().getOrDefault(curAtomName.toString(), 0);
                    stack.peek().put(curAtomName.toString(), cnt + 1);
                    break;
                } else if(Character.isUpperCase(formula.charAt(next)) || formula.charAt(next) == '(' || formula.charAt(next) == ')'){
                    int cnt = stack.peek().getOrDefault(curAtomName.toString(), 0);
                    stack.peek().put(curAtomName.toString(), cnt + 1);
                    i = next;
                    continue;
                } else if(Character.isLowerCase(formula.charAt(next))){
                    while(next < n && Character.isLowerCase(formula.charAt(next))){
                        curAtomName.append(formula.charAt(next));
                        next++;
                    }

                    int cntNum = 0;
                    int numIndex = next;
                    while(numIndex < n && Character.isDigit(formula.charAt(numIndex))){
                        cntNum = cntNum * 10 + (formula.charAt(numIndex) - '0');
                        numIndex++;
                    }

                    int cnt = stack.peek().getOrDefault(curAtomName.toString(), 0);
                    stack.peek().put(curAtomName.toString(), cnt + (cntNum == 0 ? 1 : cntNum));

                    i = numIndex;
                } else if(Character.isDigit(formula.charAt(next))){
                    int cntNum = 0;
                    int numIndex = next;
                    while(numIndex < n && Character.isDigit(formula.charAt(numIndex))){
                        cntNum = cntNum * 10 + (formula.charAt(numIndex) - '0');
                        numIndex++;
                    }

                    int cnt = stack.peek().getOrDefault(curAtomName.toString(), 0);
                    stack.peek().put(curAtomName.toString(), cnt + (cntNum == 0 ? 1 : cntNum));

                    i = numIndex;
                }
            }
        }

        StringBuffer ans = new StringBuffer();
        Map<String, Integer> map = stack.peek();
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            String key = entry.getKey();
            int value = entry.getValue();

            String str = value == 1 ? key : key + "" + value;
            ans.append(str);
        }
        return ans.toString();
    }

    public static void main(String[] args){
        String formula = "(H)";
        //String formula = "Kas4(Oab3Nsd5)6678P4";
        LeetCode_726 solution = new LeetCode_726();
        String ans = solution.countOfAtoms(formula);
        System.out.println(ans);
    }
}