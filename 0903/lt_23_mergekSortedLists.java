import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int v) { val = v; }
    ListNode(int v, ListNode n) { val = v; next = n; }
}

public class lt_23_mergekSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode head : lists) if (head != null) pq.offer(head);

        ListNode dummy = new ListNode(-1), cur = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) pq.offer(node.next);
        }
        return dummy.next;
    }

    // helpers
    static ListNode build(int... a) {
        ListNode d = new ListNode(-1), c = d;
        for (int x : a) { c.next = new ListNode(x); c = c.next; }
        return d.next;
    }
    static void print(ListNode h) {
        while (h != null) { System.out.print(h.val + (h.next!=null?" -> ":"")); h = h.next; }
        System.out.println();
    }

    public static void main(String[] args) {
        lt_23_mergekSortedLists s = new lt_23_mergekSortedLists();
        ListNode[] lists1 = { build(1,4,5), build(1,3,4), build(2,6) };
        print(s.mergeKLists(lists1)); // 1->1->2->3->4->4->5->6

        ListNode[] lists2 = {};
        print(s.mergeKLists(lists2)); // (空)

        ListNode[] lists3 = { build() };
        print(s.mergeKLists(lists3)); // (空)
    }
}

/*
解題思路：
1. k 條已排序鏈表合併為一條升序鏈表。
2. 使用最小堆(PriorityQueue)存每條鏈表當前頭節點；每次取最小節點接到結果串，並將其 next 壓回堆。
3. 時間 O(N log k)：N 為所有節點總數；堆操作 log k。
4. 空間 O(k)：堆大小最多 k。
備註：也可用「兩兩合併 / 分治」：時間 O(N log k)，空間 O(1)（迭代）或遞迴棧。
*/
