package org.qingsong;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个长度为 n 的字符串数组 names 。你将会在文件系统中创建 n 个文件夹：在第 i 分钟，新建名为 names[i] 的文件夹。
 *
 * 由于两个文件 不能 共享相同的文件名，因此如果新建文件夹使用的文件名已经被占用，
 * 系统会以 (k) 的形式为新文件夹的文件名添加后缀，其中 k 是能保证文件名唯一的 最小正整数 。
 * 返回长度为 n 的字符串数组，其中 ans[i] 是创建第 i 个文件夹时系统分配给该文件夹的实际名称。
 *
 * 示例 1：
 *
 * 输入：names = ["pes","fifa","gta","pes(2019)"]
 * 输出：["pes","fifa","gta","pes(2019)"]
 * 解释：文件系统将会这样创建文件名：
 * "pes" --> 之前未分配，仍为 "pes"
 * "fifa" --> 之前未分配，仍为 "fifa"
 * "gta" --> 之前未分配，仍为 "gta"
 * "pes(2019)" --> 之前未分配，仍为 "pes(2019)"
 * 示例 2：
 *
 * 输入：names = ["gta","gta(1)","gta","avalon"]
 * 输出：["gta","gta(1)","gta(2)","avalon"]
 * 解释：文件系统将会这样创建文件名：
 * "gta" --> 之前未分配，仍为 "gta"
 * "gta(1)" --> 之前未分配，仍为 "gta(1)"
 * "gta" --> 文件名被占用，系统为该名称添加后缀 (k)，由于 "gta(1)" 也被占用，所以 k = 2 。实际创建的文件名为 "gta(2)" 。
 * "avalon" --> 之前未分配，仍为 "avalon"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/making-file-names-unique
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q1487 {
    public String[] getFolderNames(String[] names) {
        Map<String, Integer> uniqFolderNames = new HashMap<>();
        // 按数组顺序分配
        for (int i = 0; i < names.length; i++) {
            String key = names[i];
            int kValue = uniqFolderNames.getOrDefault(key, 1);
            while (uniqFolderNames.containsKey(key)) {
                key = names[i] + "(" + kValue + ")";
                kValue++;
            }
            // key已成唯一，map中添加它
            uniqFolderNames.put(key, 1);
            // 更新原名的重复次数
            uniqFolderNames.put(names[i], kValue);
            // 新名入数组，用于返回
            names[i] = key;
        }
        return names;
    }
}
