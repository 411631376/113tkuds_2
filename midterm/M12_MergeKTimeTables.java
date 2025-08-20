import java.util.*;

public class M12_MergeKTimeTables {
    static int toMinutes(String s) {
        if (s.indexOf(':') < 0) return Integer.parseInt(s);
        String[] p = s.split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }
    static String toHHMM(int m) {
        int h = m / 60, mm = m % 60;
        return String.format("%02d:%02d", h, mm);
    }

    static class Node {
        int t, li, idx;
        Node(int t, int li, int idx) { this.t = t; this.li = li; this.idx = idx; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        List<int[]> lists = new ArrayList<>();
        boolean useHHMM = false;

        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) {
                String tok = sc.next();
                if (tok.indexOf(':') >= 0) useHHMM = true;
                arr[j] = toMinutes(tok);
            }
            lists.add(arr);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.t));
        for (int i = 0; i < K; i++) if (lists.get(i).length > 0) pq.add(new Node(lists.get(i)[0], i, 0));

        List<Integer> merged = new ArrayList<>();
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            merged.add(cur.t);
            int[] src = lists.get(cur.li);
            int ni = cur.idx + 1;
            if (ni < src.length) pq.add(new Node(src[ni], cur.li, ni));
        }

        for (int i = 0; i < merged.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(useHHMM ? toHHMM(merged.get(i)) : merged.get(i));
        }
    }
}

/*
時間複雜度：O(T log K)，T 為總筆數
空間複雜度：O(K)
*/
