package y2021.m8;

import java.util.Collections;
import java.util.LinkedList;

/**
 * @ClassName : DecodeString
 * @Description: 394. 字符串解码
 * @Author zhaooo
 * @Date 2021/8/29/18:46
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像3a或2[4]的输入。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 *
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 *
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 *
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DecodeString {

    // 全局下标
    int i;

    int ptr;

    /**
     * 深度优先遍历
     * @param s
     * @return
     */
    public String decodeString(String s) {
        char[] chars = s.toCharArray();
        return dfs(chars, 0).toString();
    }

    public StringBuilder dfs(char[] chars, int left) {
        StringBuilder bd = new StringBuilder();
        // 出现']' 退出本次dfs
        for (i = left; i < chars.length && chars[i] != ']'; i++) {
            if (Character.isLetter(chars[i])) {
                bd.append(chars[i]);
                // 是数字（先出现数字）
            } else {
                StringBuilder numBd = new StringBuilder();
                while (Character.isDigit(chars[i])) {
                    numBd.append(chars[i++]);
                }
                // chars[i]=']'
                StringBuilder tmp = dfs(chars, i + 1);
                for (int k = 0; k < Integer.parseInt(numBd.toString()); k++) {
                    // 比bd.append(tmp)快;
                    bd.append(tmp.toString());
                }
            }
        }
        return bd;
    }

    /**
     * 栈
     * @param s
     * @return
     */
    public String decodeString2(String s) {
        LinkedList<String> stk = new LinkedList<String>();
        ptr = 0;

        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                // 获取一个字母并进栈
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stk.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuilder t = new StringBuilder();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stk.addLast(t.toString());
            }
        }

        return getString(stk);
    }

    public String getDigits(String s) {
        StringBuilder ret = new StringBuilder();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    public String getString(LinkedList<String> v) {
        StringBuilder ret = new StringBuilder();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }
}
