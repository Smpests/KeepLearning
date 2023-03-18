package leetcode.recursion;

import leetcode.common.ListNode;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

 * 示例 1：
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 如果头结点为空直接返回
        if (head == null) {
            return null;
        }
        int length = numberOffFromTail(head, n);
        // 如果n大于长度，去掉头结点
        return n >= length ? head.next : head;
    }

    /**
     * 通过回溯从链表尾部从1开始计数
     */
    private int numberOffFromTail(ListNode node, int n) {
        // 到达链表末尾，返回倒数第1
        if (node.next == null) {
            return 1;
        }
        // 当前结点的倒数编号是后一个结点的计算返回
        int myNumber = numberOffFromTail(node.next, n) + 1;
        // 如果当前是倒数第n+1，也即要删除的结点的前结点
        if (myNumber == n + 1) {
            // 特殊：如果删除的是倒数第1个，前结点的next置为null
            node.next = n == 1 ? null : node.next.next;
        }
        return myNumber;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1, new ListNode(2, new ListNode(3)));
        System.out.println(new Q19().removeNthFromEnd(n1, 1).next.val);
    }
}
