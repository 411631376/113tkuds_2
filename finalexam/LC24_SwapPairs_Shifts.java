class ListNode2 {
    int val;
    ListNode2 next;
    ListNode2(int v) { val = v; }
}

public class LC24_SwapPairs_Shifts {
    public static void main(String[] args) {
        ListNode2 head = build(new int[]{1,2,3,4});
        head = swapPairs(head);
        print(head);
    }

    public static ListNode2 swapPairs(ListNode2 head) {
        ListNode2 dummy = new ListNode2(0);
        dummy.next = head;
        ListNode2 p = dummy;
        while (p.next != null && p.next.next != null) {
            ListNode2 a = p.next, b = a.next, nxt = b.next;
            p.next = b; b.next = a; a.next = nxt;
            p = a;
        }
        return dummy.next;
    }

    private static ListNode2 build(int[] arr) {
        ListNode2 d = new ListNode2(0), t = d;
        for (int v : arr) { t.next = new ListNode2(v); t = t.next; }
        return d.next;
    }

    private static void print(ListNode2 h) {
        while (h != null) { System.out.print(h.val + (h.next==null?"":" ")); h = h.next; }
    }
}
