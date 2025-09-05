class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class LC19_RemoveNth_Node_Clinic {
    public static void main(String[] args) {
        // 建立測試鏈結串列：1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int k = 2;
        ListNode newHead = removeNthFromEnd(head, k);

        // 輸出刪除後的序列
        while (newHead != null) {
            System.out.print(newHead.val + " ");
            newHead = newHead.next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        // fast 先走 n+1 步，確保 slow 停在刪除節點的前一個
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // fast 到尾，slow 到刪除點前一格
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除 slow.next
        slow.next = slow.next.next;
        return dummy.next;
    }
}
