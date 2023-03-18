package leetcode.recursion;

import leetcode.common.ListNode;

/**
 * 24. 两两交换链表中的节点
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 * 示例 1：
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 *
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1]
 * 输出：[1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q24 {
    public ListNode swapPairs(ListNode head) {
        return backtrace(head);
    }

    /**
     * 因为两两交换，我们只需要关注当前结点和它的下一结点交换即可，剩下的递归到下一层解决，
     * 递归串起来就得到解
     */
    private ListNode backtrace(ListNode node) {
        // 当前结点或下一结点为null，没有交换的必要，直接返回
        if (node == null || node.next == null) {
            return node;
        }
        // 因为要交换，所以设定一个临时变量，存储下一个结点
        ListNode tmp = node.next;
        // 交换，当前结点的next交换后等于原next的next（即node.next.next）
        // 因为交换后的下一节点也要发生交换，所以递归传递
        node.next = backtrace(tmp.next);
        // 交换后原next的next就变成了当前结点
        tmp.next = node;
        // 返回被交换到前面来的结点
        return tmp;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode result = new Q24().swapPairs(node);
        System.out.println("");
    }
}
