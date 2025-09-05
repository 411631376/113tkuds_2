import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int v) { val = v; }
}

public class LC23_MergeKLists_Hospitals {
    public static void main(String[] args) {
        ListNode a = build(new int[]{1,4,5});
        ListNode b = build(new int[]{1,3,4});
        ListNode c = build(new int[]{2,6});
        ListNode[] lists = new ListNode[]{a,b,c};
        ListNode merged = mergeKLists(lists);
        print(merged);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        for (ListNode x : lists) if (x != null) pq.offer(x);
        ListNode dummy = new ListNode(0), tail = dummy;
        while (!pq.isEmpty()) {
            ListNode n = pq.poll();
            tail.next = n; tail = n;
            if (n.next != null) pq.offer(n.next);
        }
        return dummy.next;
    }

    private static ListNode build(int[] arr) {
        ListNode d = new ListNode(0), t = d;
        for (int v : arr) { t.next = new ListNode(v); t = t.next; }
        return d.next;
    }

    private static void print(ListNode h) {
        while (h != null) { System.out.print(h.val + (h.next==null?"":" ")); h = h.next; }
    }
}
