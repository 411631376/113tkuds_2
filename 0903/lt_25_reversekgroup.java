public class lt_25_reversekgroup {
    // 定義鏈結串列節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    // 主函數：每 k 個一組翻轉鏈結串列
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy, nex = dummy, pre = dummy;
        int count = 0;

        // 計算鏈結串列長度
        while (curr.next != null) {
            curr = curr.next;
            count++;
        }

        // 每 k 個一組翻轉
        while (count >= k) {
            curr = pre.next;
            nex = curr.next;
            for (int i = 1; i < k; i++) {
                curr.next = nex.next;
                nex.next = pre.next;
                pre.next = nex;
                nex = curr.next;
            }
            pre = curr;
            count -= k;
        }
        return dummy.next;
    }

    // 測試用 main
    public static void main(String[] args) {
        // 建立測資：1->2->3->4->5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int k = 2;
        ListNode result = reverseKGroup(head, k);

        // 輸出結果
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}

/*
解題思路：
1. 先計算鏈結串列總長度，確定可以翻轉的完整區塊數量。
2. 使用三個指標：
   - pre：指向每組要翻轉區塊的前一個節點
   - curr：指向當前要翻轉區塊的第一個節點
   - nex：輔助交換 curr 之後的節點
3. 在每組 k 個節點內，逐步翻轉指標連結。
4. 更新 pre 指向下一組，直到剩下不足 k 個節點為止。
5. 時間複雜度 O(n)，空間複雜度 O(1)。
*/
