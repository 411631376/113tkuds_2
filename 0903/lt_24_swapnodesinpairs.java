class ListNode2 {
    int val;
    ListNode2 next;
    ListNode2() {}
    ListNode2(int v) { val = v; }
    ListNode2(int v, ListNode2 n) { val = v; next = n; }
}

public class lt_24_swapnodesinpairs {

    // 迭代解
    public ListNode2 swapPairs(ListNode2 head) {
        ListNode2 dummy = new ListNode2(-1, head), prev = dummy;
        while (prev.next != null && prev.next.next != null) {
            ListNode2 a = prev.next, b = a.next;
            a.next = b.next;
            b.next = a;
            prev.next = b;
            prev = a;
        }
        return dummy.next;
    }

    // helpers
    static ListNode2 build(int... a) {
        ListNode2 d = new ListNode2(-1), c = d;
        for (int x : a) { c.next = new ListNode2(x); c = c.next; }
        return d.next;
    }
    static void print(ListNode2 h) {
        while (h != null) { System.out.print(h.val + (h.next!=null?" -> ":"")); h = h.next; }
        System.out.println();
    }

    public static void main(String[] args) {
        lt_24_swapnodesinpairs s = new lt_24_swapnodesinpairs();
        print(s.swapPairs(build(1,2,3,4))); // 2 -> 1 -> 4 -> 3
        print(s.swapPairs(build()));         // (空)
        print(s.swapPairs(build(1)));        // 1
        print(s.swapPairs(build(1,2,3)));    // 2 -> 1 -> 3
    }
}

/*
解題思路：
1. 每兩個相鄰節點交換位置，不能修改節點值，只能改指標。
2. 迭代做法：用 dummy + prev 指標，對 (a,b) 兩節點做翻轉：
   prev -> a -> b -> next   →   prev -> b -> a -> next
   然後 prev 移到 a，繼續處理。
3. 邊界：空鏈表或單節點直接返回。
4. 時間 O(n)，空間 O(1)；（遞迴版時間 O(n)，空間 O(n) 遞迴棧）。
*/
