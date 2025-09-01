// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class lt_02_addTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;

        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;

            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }

        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummyHead.next;
    }

    // 測試用 main
    public static void main(String[] args) {
        lt_02_addTwoNumbers solution = new lt_02_addTwoNumbers();

        // 建立 l1 = [2,4,3]
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));

        // 建立 l2 = [5,6,4]
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        // 執行相加
        ListNode result = solution.addTwoNumbers(l1, l2);

        // 輸出結果
        while (result != null) {
            System.out.print(result.val);
            if (result.next != null) System.out.print(" -> ");
            result = result.next;
        }
        System.out.println();
    }
}
