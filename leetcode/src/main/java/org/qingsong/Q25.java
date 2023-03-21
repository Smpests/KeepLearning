package org.qingsong;

import org.qingsong.common.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 示例 1：
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 示例 2：
 *
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q25 {

    /**
     * 见反转想递归和栈，栈是先进后出。
     * 递归思前想后，不好拼接两组反转后的链表，于是用栈。
     * 方式一：使用栈一开始遇到了循环链表问题，解决之后时间复杂度O(n²)，耗时长空间大；
     */
    public ListNode reverseKGroupSlow(ListNode head, int k) {
        Stack<ListNode> nodeStack = new Stack<>();
        ListNode newHead = new ListNode(0);
        ListNode iterNode = newHead;
        while (head != null) {
            nodeStack.add(head);
            head = head.next;
            if (nodeStack.size() == k) {
                while (!nodeStack.isEmpty()) {
                    ListNode tmp = iterNode.next;
                    iterNode.next = nodeStack.pop();
                    iterNode = iterNode.next;
                    iterNode.next = tmp;
                }
            }
        }
        List<ListNode> nodeLists = new ArrayList<>();
        while (!nodeStack.isEmpty()) {
            nodeLists.add(nodeStack.pop());
        }
        for (int i = nodeLists.size() - 1; i >= 0; --i) {
            iterNode.next = nodeLists.get(i);
            iterNode = iterNode.next;
        }
        return newHead.next;
    }

    /**
     * 方式二：参考题解，详见注释
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 在head前插入一个结点作为头结点的前置结点不参与反转
        ListNode headHolder = new ListNode(0, head);
        // 前结点在区间反转后要接新的头结点
        ListNode prev = headHolder;
        // 区间反转后的节点要接原链表的剩余部分（剩余部分也会反转，所以初始化为和prev相同）
        ListNode end = headHolder;
        // 还未反转到原链表尾结点
        while (end.next != null) {
            // 循环K次，更新end为当前区间的最后一个结点
            for (int i = 0; i < k && end != null; ++i) {
                end = end.next;
            }
            // 如果end为null，说明当前区间的结点数量不够k个，停止循环直接按原顺序返回
            if (end == null) {
                break;
            }
            // 第一个反转结点
            ListNode start = prev.next;
            // 区间末结点后面的结点存起来，要截断重链
            ListNode rest = end.next;
            // 截断
            rest.next = null;
            // prev接反转后的链表
            prev.next = reverse(start);
            // 链接剩余结点，因为start已被反转到末尾，所以链到start后面
            start.next = rest;
            // 更新下一个区间的prev和end
            prev = start;
            end = prev;
        }
        return headHolder.next;
    }

    private ListNode reverse(ListNode head) {
        // 每个区间的头结点要被反转到末尾，所以头结点反转后要链到null
        // 否则会有循环链表的问题
        ListNode pre = null;
        // 反转起始结点
        ListNode curr = head;
        // 区间内循环（区间末尾结点的next已经设为null），原本在前面的结点放到链表后面
        // 前结点作为当前结点的next
        // 当前结点更新为prev
        // 循环的下一个结点为原next
        // 如1->2->3，第一次循环1(pre)->null，第二次2(pre)->1-null，第三次3(pre)->2->1->null，
        // 根据k的大小，将列表按反顺序拼接
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        // 最终的pre为新的区间头结点
        return pre;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode node2 = new ListNode(1, new ListNode(2));
        ListNode result = new Q25().reverseKGroup(node2, 2);
        System.out.println();
    }
}
