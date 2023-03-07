import common.ListNode;

import java.util.Objects;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 * 2 -> 4 -> 3 和 5 -> 6 -> 4 计算得 7 -> 0 -> 8
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode headNode = null;
        ListNode tailNode = null;
        ListNode curNode;
        // 进位值
        int jinWei = 0;
        // 因链表头部为低位，则从头开始计算
        // l1, l2在循环尾部赋值为链表的next，均不为null时循环
        while (Objects.nonNull(l1) || Objects.nonNull(l2)) {
            // 均有值则相加（以及进位）
            if (Objects.nonNull(l1) && Objects.nonNull(l2)) {
                int sum = l1.val + l2.val + jinWei;
                // sum大于10更新进位
                jinWei = sum >= 10 ? 1 : 0;
                curNode = new ListNode(sum % 10);
            } else if (Objects.isNull(l1)) {
                int sum = l2.val + jinWei;
                jinWei = sum >= 10 ? 1 : 0;
                curNode = new ListNode(sum % 10);
            } else {
                int sum = l1.val + jinWei;
                jinWei = sum >= 10 ? 1 : 0;
                curNode = new ListNode(sum % 10);
            }
            if (headNode == null) {
                headNode = curNode;
                tailNode = curNode;
            } else {
                tailNode.next = curNode;
                tailNode = curNode;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (jinWei > 0) {
            tailNode.next = new ListNode(jinWei);
        }
        return headNode;
    }
}
