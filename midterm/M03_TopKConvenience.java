import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        int index; 
        Item(String name, int qty, int index) {
            this.name = name;
            this.qty = qty;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int K = sc.nextInt();
        sc.nextLine();

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().split(" ");
            String name = parts[0];
            int qty = Integer.parseInt(parts[1]);
            items.add(new Item(name, qty, i));
        }

        items.sort((a, b) -> {
            if (b.qty != a.qty) return b.qty - a.qty; // 大到小
            return a.index - b.index; // 同數量依輸入順序
        });

        for (int i = 0; i < Math.min(K, items.size()); i++) {
            System.out.println(items.get(i).name + " " + items.get(i).qty);
        }
    }
}

/*
時間複雜度：O(n log n) （排序主導）
空間複雜度：O(n) （存放商品）
*/
