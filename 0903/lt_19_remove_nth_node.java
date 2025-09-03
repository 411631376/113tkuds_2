public class lt_19_remove_nth_node {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0); // 虛擬節點，方便處理邊界情況
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        
        // 讓 fast 指針先走 n 步
        for (int i = 1; i <= n + 1; i++) {
            fast = fast.next;
        }
        
        // 然後兩個指針一起走，直到 fast 指針到達最後
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        // 刪除 slow 的下一個節點
        slow.next = slow.next.next;
        
        return dummy.next; // 返回新的頭節點
    }

    public static void main(String[] args) {
        lt_19_remove_nth_node solution = new lt_19_remove_nth_node();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode result = solution.removeNthFromEnd(head, 2);
        
        // 打印結果
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
