class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class lt_21_mergetwosortedlists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        if (list1 != null) current.next = list1;
        if (list2 != null) current.next = list2;

        return dummy.next;
    }

    // 輔助方法：建立鏈表
    public static ListNode createList(int[] arr) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        for (int num : arr) {
            current.next = new ListNode(num);
            current = current.next;
        }
        return dummy.next;
    }

    // 輔助方法：輸出鏈表
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        lt_21_mergetwosortedlists solution = new lt_21_mergetwosortedlists();

        // 測試用例
        int[] arr1 = {1, 2, 4};
        int[] arr2 = {1, 3, 4};
        ListNode list1 = createList(arr1);
        ListNode list2 = createList(arr2);

        ListNode merged = solution.mergeTwoLists(list1, list2);

        System.out.print("Merged List: ");
        printList(merged);
    }
}

/*
解題思路：
1. 使用一個虛擬頭節點 dummy 來簡化處理，最後返回 dummy.next。
2. 兩個指標 list1、list2 逐一比較，將較小的節點接到新鏈表。
3. 當一個鏈表結束時，直接把另一個鏈表剩餘部分接上。
4. 時間複雜度 O(m+n)，空間複雜度 O(1)。
*/
