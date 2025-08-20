import java.util.*;

public class M11_HeapSortWithTie {
    static class Item {
        int score, idx;
        Item(int s, int i) { score = s; idx = i; }
    }

    static boolean greater(Item a, Item b) {
        if (a.score != b.score) return a.score > b.score;
        return a.idx > b.idx; // 分數同，idx 較大者視為「較大」
    }

    static void heapifyDown(Item[] h, int n, int i) {
        while (true) {
            int l = 2*i+1, r = 2*i+2, m = i;
            if (l < n && greater(h[l], h[m])) m = l;
            if (r < n && greater(h[r], h[m])) m = r;
            if (m == i) break;
            Item t = h[i]; h[i] = h[m]; h[m] = t;
            i = m;
        }
    }

    static void buildHeap(Item[] h, int n) {
        for (int i = n/2 - 1; i >= 0; i--) heapifyDown(h, n, i);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Item[] a = new Item[n];
        for (int i = 0; i < n; i++) a[i] = new Item(sc.nextInt(), i);

        buildHeap(a, n);
        for (int end = n - 1; end > 0; end--) {
            Item t = a[0]; a[0] = a[end]; a[end] = t;
            heapifyDown(a, end, 0);
        }

        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(a[i].score);
        }
    }
}

/*
時間複雜度：O(n log n)
空間複雜度：O(1)
*/
