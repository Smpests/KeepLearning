import common.ListNode;

import java.util.List;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 *
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：lists = [[]]
 * 输出：[]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q23 {

    private Q21 q21 = new Q21();
    /**
     * Q21.合并两个有序链表的升级版
     * 思路：
     * 两两合并是简单的，那么拆分一下不就好了，如lists[0]和lists[1]合并的新链表再和lists[2]合并。
     * 就可以多次调用Q21的方法来实现，但这里想用一下之前没用到的回溯算法。
     * 经实验，回溯更慢。另外每次新链表和下一个链表合并的方式很慢（比分治慢100多倍），采用两两分组后合并。
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        return merge(lists, 0, lists.length - 1);
    }

    /**
     * 将链表两两配对合并，如k个链表第一次分成k/2组进行合并，第二次分成k/4组合并，直到只剩一个链表
     * 比原方法每次循环合并快100多倍
     */
    private ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return q21.mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    /**
     * 比Q21的合并方式慢，慢得不多
     */
    private ListNode backtrace(ListNode l1, ListNode l2) {
        // 有链表遍历到底，前面的都已经有序，直接返回剩下的拼在后面
        // 因链表本身有序，剩下的一定是大值
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        // 如果当前l1的值小于等于l2，即l2后面的值也都大于l1，
        // 有可能l1.next的值仍然小于当前的l2，于是l2不变，l1 = l1.next
        // 但无论l1后面的值是小于还是大于，当前的l1一定是前结点
        if (l1.val <= l2.val) {
            l1.next = backtrace(l1.next, l2);
            return l1;
        } else {
            // 同上，但相反
            l2.next = backtrace(l1, l2.next);
            return l2;
        }
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode node2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode node3 = new ListNode(2, new ListNode(6));
        ListNode result = new Q23().mergeKLists(new ListNode[]{node1, node2, node3});
        while (result != null) {
            System.out.print(result.val);
            result = result.next;
        }
    }
}
