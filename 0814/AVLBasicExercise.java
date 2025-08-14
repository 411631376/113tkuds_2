public class AVLBasicExercise {

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

    // 插入節點（標準 BST 插入）
    public void insert(int data) {
        root = insertNode(root, data);
    }

    private Node insertNode(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        if (data < node.data) {
            node.left = insertNode(node.left, data);
        } else if (data > node.data) {
            node.right = insertNode(node.right, data);
        } else {
            return node; // 不允許重複
        }

        // 更新高度
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        return node;
    }

    // 搜尋節點
    public boolean search(int data) {
        return searchNode(root, data);
    }

    private boolean searchNode(Node node, int data) {
        if (node == null) return false;
        if (data == node.data) return true;
        return (data < node.data) ? searchNode(node.left, data) : searchNode(node.right, data);
    }

    // 計算高度
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // 檢查是否為有效 AVL
    public boolean isValidAVL() {
        return checkAVL(root) != -1;
    }

    private int checkAVL(Node node) {
        if (node == null) return 0;

        int leftHeight = checkAVL(node.left);
        int rightHeight = checkAVL(node.right);

        if (leftHeight == -1 || rightHeight == -1) return -1;
        if (Math.abs(leftHeight - rightHeight) > 1) return -1; // 不平衡

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // 中序輸出
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

    // 測試
    public static void main(String[] args) {
        AVLBasicExercise tree = new AVLBasicExercise();

        // 插入節點
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(4);
        tree.insert(15);

        System.out.print("中序遍歷: ");
        tree.printInOrder();

        // 搜尋測試
        System.out.println("搜尋 15: " + tree.search(15));
        System.out.println("搜尋 100: " + tree.search(100));

        // 高度
        System.out.println("樹高度: " + tree.getHeight());

        // AVL 驗證
        System.out.println("是否為有效 AVL 樹: " + tree.isValidAVL());
    }
}
