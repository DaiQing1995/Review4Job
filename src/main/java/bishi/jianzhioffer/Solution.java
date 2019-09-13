package bishi.jianzhioffer;

public class Solution {

	public static class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}

	public ListNode deleteDuplication(ListNode pHead) {
		ListNode p = pHead;
		int tmp = 0;
		boolean delFlag = false;
		// 头部是否重复
		while (pHead.next != null && pHead.val == pHead.next.val) {
			if (pHead.val == pHead.next.val) {
				tmp = p.val;
				while (pHead.val == tmp) {
					p = pHead.next;
					pHead.next = null;
					pHead = p;
					if (pHead == null)
						return null;
				}
			}
		}
		// 固定头部，检查后两个
		p = pHead;
		ListNode q = pHead.next;
		if (q == null)
			return pHead;
		ListNode r = q.next;
		if (r == null)
			return pHead;
		while (r != null) {
			if (q.val == r.val) {
				tmp = q.val;
				while (p.next != null && tmp == p.next.val) {
					q = p.next;
					p.next = q.next;
					q.next = null;
				}
			} else {
				p = p.next;
			}
			q = p.next;
			if (q != null)
				r = q.next;
			else
				r = null;
		}
		return pHead;
	}
	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		ListNode head = node;
		node.next = new ListNode(1);
		node = node.next;
		node.next = new ListNode(1);
		node = node.next;
		node.next = new ListNode(2);
		node = node.next;
		node.next = new ListNode(3);
		node = node.next;
		node.next = new ListNode(3);
		node = node.next;
		node.next = new ListNode(4);
		node = node.next;
		node.next = new ListNode(5);
		node = node.next;
		node.next = new ListNode(5);
		node = node.next;
		new Solution().deleteDuplication(head);
	}
}