import java.util.ArrayList;
import java.util.List;

public class AVLRangeQueryExercise {

    // 節點類別
    static class Node {
        int data;
        Node left, right;
        int height;

        Node(int data) {
            this.data = data;
            this.height = 1;
        }
    }

    private Node root;

    // 取得高度
    private int getHeight(Node node) {
        return (node != null) ? node.height : 0;
    }

    // 更新高度
    private void updateHeight(Node node) {
        if (node != null) {
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        }
    }

    // 計算平衡因子
    private int getBalance(Node node) {
        return (node != null) ? getHeight(node.left) - getHeight(node.right) : 0;
    }

    // 右旋
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // 左旋
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // 插入節點
    public void insert(int data) {
        root = insertNode(root, data);
    }

    private Node insertNode(Node node, int data) {
        if (node == null) return new Node(data);

        if (data < node.data) {
            node.left = insertNode(node.left, data);
        } else if (data > node.data) {
            node.right = insertNode(node.right, data);
        } else {
            return node; // 不插入重複值
        }

        updateHeight(node);

        int balance = getBalance(node);

        // LL
        if (balance > 1 && data < node.left.data) return rightRotate(node);
        // RR
        if (balance < -1 && data > node.right.data) return leftRotate(node);
        // LR
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 範圍查詢
    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, min, max, result);
        return result;
    }

    private void rangeQueryHelper(Node node, int min, int max, List<Integer> result) {
        if (node == null) return;

        // 如果 min < 當前節點值，則可能左邊還有符合條件的
        if (min < node.data) {
            rangeQueryHelper(node.left, min, max, result);
        }

        // 如果當前節點在範圍內
        if (node.data >= min && node.data <= max) {
            result.add(node.data);
        }

        // 如果 max > 當前節點值，則可能右邊還有符合條件的
        if (max > node.data) {
            rangeQueryHelper(node.right, min, max, result);
        }
    }

    // 中序列印
    public void printInOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    // 測試主程式
    public static void main(String[] args) {
        AVLRangeQueryExercise tree = new AVLRangeQueryExercise();

        int[] values = {20, 10, 30, 5, 15, 25, 35, 3, 7, 13, 17};
        for (int v : values) {
            tree.insert(v);
        }

        System.out.print("中序遍歷: ");
        tree.printInOrder();

        int min = 7, max = 25;
        List<Integer> result = tree.rangeQuery(min, max);
        System.out.println("範圍查詢 [" + min + ", " + max + "]: " + result);
    }
}
