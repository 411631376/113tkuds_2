import java.util.ArrayList;
import java.util.List;

public class PersistentAVLExercise {

    // 不可變節點類別
    static class Node {
        final int data;
        final Node left, right;
        final int height;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.height = Math.max(getHeight(left), getHeight(right)) + 1;
        }
    }

    private List<Node> versions = new ArrayList<>(); // 保存不同版本的 root

    // 建構子：建立空版本
    public PersistentAVLExercise() {
        versions.add(null);
    }

    // 取得節點高度
    private static int getHeight(Node node) {
        return (node != null) ? node.height : 0;
    }

    // 平衡因子
    private static int getBalance(Node node) {
        return (node != null) ? getHeight(node.left) - getHeight(node.right) : 0;
    }

    // 右旋
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        return new Node(x.data, x.left, new Node(y.data, T2, y.right));
    }

    // 左旋
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        return new Node(y.data, new Node(x.data, x.left, T2), y.right);
    }

    // 插入節點（返回新 root）
    private Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data, null, null);
        }

        if (data < node.data) {
            Node newLeft = insert(node.left, data);
            node = new Node(node.data, newLeft, node.right);
        } else if (data > node.data) {
            Node newRight = insert(node.right, data);
            node = new Node(node.data, node.left, newRight);
        } else {
            return node; // 不插入重複
        }

        int balance = getBalance(node);

        // LL
        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);
        }
        // RR
        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }
        // LR
        if (balance > 1 && data > node.left.data) {
            Node newLeft = leftRotate(node.left);
            return rightRotate(new Node(node.data, newLeft, node.right));
        }
        // RL
        if (balance < -1 && data < node.right.data) {
            Node newRight = rightRotate(node.right);
            return leftRotate(new Node(node.data, node.left, newRight));
        }

        return node;
    }

    // 新增版本（插入）
    public void insertNewVersion(int data) {
        Node latestRoot = versions.get(versions.size() - 1);
        Node newRoot = insert(latestRoot, data);
        versions.add(newRoot);
    }

    // 查詢指定版本的中序遍歷
    public void printVersion(int versionIndex) {
        if (versionIndex < 0 || versionIndex >= versions.size()) {
            System.out.println("版本不存在");
            return;
        }
        System.out.print("版本 " + versionIndex + ": ");
        inOrder(versions.get(versionIndex));
        System.out.println();
    }

    // 中序遍歷
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    public static void main(String[] args) {
        PersistentAVLExercise tree = new PersistentAVLExercise();

        tree.insertNewVersion(10); // v1
        tree.insertNewVersion(20); // v2
        tree.insertNewVersion(30); // v3
        tree.insertNewVersion(25); // v4

        tree.printVersion(0); // 空版本
        tree.printVersion(1);
        tree.printVersion(2);
        tree.printVersion(3);
        tree.printVersion(4);
    }
}
