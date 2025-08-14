public class AVLDeleteExercise {

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

    // 計算平衡因子
    private int getBalance(Node node) {
        return (node != null) ? getHeight(node.left) - getHeight(node.right) : 0;
    }

    // 更新高度
    private void updateHeight(Node node) {
        if (node != null) {
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        }
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

    // 插入節點
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
            return node;
        }

        updateHeight(node);
        return rebalance(node, data);
    }

    // 刪除節點
    public void delete(int data) {
        root = deleteNode(root, data);
    }

    private Node deleteNode(Node node, int data) {
        if (node == null) return null;

        if (data < node.data) {
            node.left = deleteNode(node.left, data);
        } else if (data > node.data) {
            node.right = deleteNode(node.right, data);
        } else {
            // Case 1: 葉子節點
            if (node.left == null && node.right == null) {
                return null;
            }
            // Case 2: 只有一個子節點
            else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            // Case 3: 兩個子節點
            else {
                Node minNode = findMin(node.right);
                node.data = minNode.data;
                node.right = deleteNode(node.right, minNode.data);
            }
        }

        updateHeight(node);
        return rebalanceAfterDelete(node);
    }

    // 找最小值節點
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // 刪除後重新平衡
    private Node rebalanceAfterDelete(Node node) {
        int balance = getBalance(node);

        // LL
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }
        // LR
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RR
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }
        // RL
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 插入後重新平衡
    private Node rebalance(Node node, int data) {
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

    // 中序遍歷
    public void printInOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + "(" + getBalance(node) + ") ");
            inOrder(node.right);
        }
    }

    // 測試
    public static void main(String[] args) {
        AVLDeleteExercise tree = new AVLDeleteExercise();

        // 插入資料
        int[] values = {10, 20, 30, 40, 50, 25};
        for (int v : values) {
            tree.insert(v);
        }

        System.out.println("初始樹:");
        tree.printInOrder();

        // 刪除葉子節點
        System.out.println("\n刪除葉子節點 25:");
        tree.delete(25);
        tree.printInOrder();

        // 刪除只有一個子節點的節點
        System.out.println("\n刪除只有一個子節點的節點 50:");
        tree.delete(50);
        tree.printInOrder();

        // 刪除有兩個子節點的節點
        System.out.println("\n刪除有兩個子節點的節點 30:");
        tree.delete(30);
        tree.printInOrder();
    }
}
