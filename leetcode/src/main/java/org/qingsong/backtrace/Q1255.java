package org.qingsong.backtrace;

/**
 * 你将会得到一份单词表 words，一个字母表 letters （可能会有重复字母），以及每个字母对应的得分情况表 score。
 * 请你帮忙计算玩家在单词拼写游戏中所能获得的「最高得分」：能够由 letters 里的字母拼写出的 任意 属于 words 单词子集中，分数最高的单词集合的得分。
 * 单词拼写游戏的规则概述如下：
 * 玩家需要用字母表 letters 里的字母来拼写单词表 words 中的单词。
 * 可以只使用字母表 letters 中的部分字母，但是每个字母最多被使用一次。
 * 单词表 words 中每个单词只能计分（使用）一次。
 * 根据字母得分情况表score，字母 'a', 'b', 'c', ... , 'z' 对应的得分分别为 score[0], score[1], ..., score[25]。
 * 本场游戏的「得分」是指：玩家所拼写出的单词集合里包含的所有字母的得分之和。
 *  
 * 示例 1：
 *
 * 输入：words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"], score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
 * 输出：23
 * 解释：
 * 字母得分为  a=1, c=9, d=5, g=3, o=2
 * 使用给定的字母表 letters，我们可以拼写单词 "dad" (5+1+5)和 "good" (3+2+2+5)，得分为 23 。
 * 而单词 "dad" 和 "dog" 只能得到 21 分。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-score-words-formed-by-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q1255 {
    // 主要是枚举，有二进制枚举和递归枚举（每个单词仅有2种状态：用和不用）
    // 每种情况在满足letters的条件下计算得分，最后选出分数最高的情况/单词组合
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] leftLetters = new int[26];
        int lettersNum = letters.length;
        for (int i = 0;i < lettersNum;i++) {
            leftLetters[letters[i] - 'a']++;
        }
        return findGroup(words, score, words.length - 1, leftLetters);
    }

    /**
     * 计算当前索引位置的词在选中和不选情况下得分并递归
     * @param words 词集
     * @param score 分数表
     * @param wordIndex 当前词索引
     * @param leftLetters 剩余可用字母表
     * @return 最大分数
     */
    private int findGroup(String[] words, int[] score, int wordIndex, int[] leftLetters) {
        // 退出条件，递归到索引边界
        if (wordIndex < 0) {
            return 0;
        }
        // 不选，递归一层，加0分，字母数量不变
        int maxScore = findGroup(words, score, wordIndex - 1, leftLetters);
        // 选择，判断是否满足条件及计算分数
        // 深拷贝一份leftLetters，便于在计算一部分之后不满足条件后的状态回溯
        int[] tmpLetters = leftLetters.clone();
        int tmpScore = 0;
        for (char ch: words[wordIndex].toCharArray()) {
            int indexOfLetter = ch - 'a';
            // 字母不够覆盖该单词，于是结果等于不选
            if (tmpLetters[indexOfLetter] == 0) {
                // 不选的情况在上面已经递归过，这里就直接返回结果
                return maxScore;
            }
            // 如果字母还够，则加分且字母数量-1
            tmpScore += score[indexOfLetter];
            tmpLetters[indexOfLetter] -= 1;
        }
        // 返回不选该词和选择该词较大的得分
        return Math.max(maxScore, tmpScore + findGroup(words, score, wordIndex -1, tmpLetters));
    }
}
// 位压缩法，利用二进制表示各种枚举组合，穷举计算组合中的最大分数
// 比递归方法慢
// class Solution {
//     public int maxScoreWords(String[] words, char[] letters, int[] score) {
//         int n = words.length, res = 0;
//         int[] count = new int[26];
//         for (char c : letters) {
//             count[c - 'a']++;
//         }
//         for (int s = 1; s < (1 << n); s++) {
//             int[] wordCount = new int[26]; // 统计子集 s 所有单词的字母数目
//             for (int k = 0; k < n; k++) {
//                 if ((s & (1 << k)) == 0) { // words[k] 不在子集 s 中
//                     continue;
//                 }
//                 for (int i = 0; i < words[k].length(); i++) {
//                     char c = words[k].charAt(i);
//                     wordCount[c - 'a']++;
//                 }
//             }
//             boolean ok = true; // 判断子集 s 是否合法
//             int sum = 0; // 保存子集 s 的得分
//             for (int i = 0; i < 26; i++) {
//                 sum += score[i] * wordCount[i];
//                 ok = ok && (wordCount[i] <= count[i]);
//             }
//             if (ok) {
//                 res = Math.max(res, sum);
//             }
//         }
//         return res;
//     }
// }
// 未注释的方法通过参考以下题解理解后写的
// class Solution {
//     private String[] words;
//     private int[] score;

//     public int maxScoreWords(String[] words, char[] letters, int[] score) {
//         this.words = words;
//         this.score = score;
//         int[] left = new int[26];
//         for (char c : letters) ++left[c - 'a'];
//         return dfs(words.length - 1, left);
//     }

//     private int dfs(int i, int[] left) {
//         if (i < 0) return 0; // base case

//         // 不选 words[i]
//         int res = dfs(i - 1, left);

//         // 选 words[i]
//         int[] tmp = left.clone();
//         int s = 0;
//         for (char c : words[i].toCharArray()) {
//             if (tmp[c - 'a']-- == 0)
//                 return res; // 剩余字母不足
//             s += score[c - 'a']; // 累加得分
//         }
//         return Math.max(res, s + dfs(i - 1, tmp));
//     }
// }
