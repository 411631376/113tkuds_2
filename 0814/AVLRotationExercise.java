public class AVLRotationExercise {

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

    // 取得節點高度
    static int getHeight(Node node) {
        return (node != null) ? node.height : 0;
    }

    // 更新節點高度
    static void updateHeight(Node node) {
        if (node != null) {
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        }
    }

    // 計算平衡因子
    static int getBalance(Node node) {
        return (node != null) ? getHeight(node.left) - getHeight(node.right) : 0;
    }

    // 左旋
    static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // 右旋
    static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // 左右旋
    static Node leftRightRotate(Node node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    // 右左旋
    static Node rightLeftRotate(Node node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    // 建立測試樹
    static Node createNode(int data) {
        return new Node(data);
    }

    // 輔助列印
    static void printNodeInfo(String msg, Node root) {
        System.out.println(msg);
        System.out.println("根節點: " + root.data + ", 高度: " + root.height + ", 平衡因子: " + getBalance(root));
    }

    public static void main(String[] args) {
        // 測試 LL (右旋)
        System.out.println("===== 測試右旋 (LL) =====");
        Node rootLL = createNode(30);
        rootLL.left = createNode(20);
        rootLL.left.left = createNode(10);
        updateHeight(rootLL.left.left);
        updateHeight(rootLL.left);
        updateHeight(rootLL);
        printNodeInfo("旋轉前", rootLL);
        rootLL = rightRotate(rootLL);
        printNodeInfo("旋轉後", rootLL);

        // 測試 RR (左旋)
        System.out.println("\n===== 測試左旋 (RR) =====");
        Node rootRR = createNode(10);
        rootRR.right = createNode(20);
        rootRR.right.right = createNode(30);
        updateHeight(rootRR.right.right);
        updateHeight(rootRR.right);
        updateHeight(rootRR);
        printNodeInfo("旋轉前", rootRR);
        rootRR = leftRotate(rootRR);
        printNodeInfo("旋轉後", rootRR);

        // 測試 LR (左右旋)
        System.out.println("\n===== 測試左右旋 (LR) =====");
        Node rootLR = createNode(30);
        rootLR.left = createNode(10);
        rootLR.left.right = createNode(20);
        updateHeight(rootLR.left.right);
        updateHeight(rootLR.left);
        updateHeight(rootLR);
        printNodeInfo("旋轉前", rootLR);
        rootLR = leftRightRotate(rootLR);
        printNodeInfo("旋轉後", rootLR);

        // 測試 RL (右左旋)
        System.out.println("\n===== 測試右左旋 (RL) =====");
        Node rootRL = createNode(10);
        rootRL.right = createNode(30);
        rootRL.right.left = createNode(20);
        updateHeight(rootRL.right.left);
        updateHeight(rootRL.right);
        updateHeight(rootRL);
        printNodeInfo("旋轉前", rootRL);
        rootRL = rightLeftRotate(rootRL);
        printNodeInfo("旋轉後", rootRL);
    }
}
