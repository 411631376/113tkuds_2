import java.util.*;

public class AVLLeaderboardSystem {

    static class Node {
        String name;
        int score;
        Node left, right;
        int height;
        int size;

        Node(String name, int score) {
            this.name = name;
            this.score = score;
            this.height = 1;
            this.size = 1;
        }
    }

    private Node root;
    private Map<String, Integer> playerScores = new HashMap<>();

    private int getHeight(Node node) {
        return (node != null) ? node.height : 0;
    }

    private int getSize(Node node) {
        return (node != null) ? node.size : 0;
    }

    private void updateNode(Node node) {
        if (node != null) {
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
            node.size = getSize(node.left) + getSize(node.right) + 1;
        }
    }

    private int getBalance(Node node) {
        return (node != null) ? getHeight(node.left) - getHeight(node.right) : 0;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateNode(x);
        updateNode(y);

        return y;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateNode(y);
        updateNode(x);

        return x;
    }

    private Node insertNode(Node node, String name, int score) {
        if (node == null) {
            return new Node(name, score);
        }

        if (score > node.score || (score == node.score && name.compareTo(node.name) < 0)) {
            node.left = insertNode(node.left, name, score);
        } else {
            node.right = insertNode(node.right, name, score);
        }

        updateNode(node);
        return rebalance(node, name, score);
    }

    private Node deleteNode(Node node, String name, int score) {
        if (node == null) return null;

        if (score > node.score || (score == node.score && name.compareTo(node.name) < 0)) {
            node.left = deleteNode(node.left, name, score);
        } else if (score < node.score || (score == node.score && name.compareTo(node.name) > 0)) {
            node.right = deleteNode(node.right, name, score);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node successor = getMinNode(node.right);
            node.name = successor.name;
            node.score = successor.score;
            node.right = deleteNode(node.right, successor.name, successor.score);
        }

        updateNode(node);
        return rebalanceAfterDelete(node);
    }

    private Node getMinNode(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node rebalance(Node node, String name, int score) {
        int balance = getBalance(node);

        if (balance > 1 && (score > node.left.score || (score == node.left.score && name.compareTo(node.left.name) < 0))) {
            return rightRotate(node);
        }
        if (balance < -1 && (score < node.right.score || (score == node.right.score && name.compareTo(node.right.name) > 0))) {
            return leftRotate(node);
        }
        if (balance > 1 && (score < node.left.score || (score == node.left.score && name.compareTo(node.left.name) > 0))) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && (score > node.right.score || (score == node.right.score && name.compareTo(node.right.name) < 0))) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private Node rebalanceAfterDelete(Node node) {
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public void addPlayer(String name, int score) {
        if (playerScores.containsKey(name)) {
            System.out.println("玩家已存在，請使用 updateScore 更新分數");
            return;
        }
        root = insertNode(root, name, score);
        playerScores.put(name, score);
    }

    public void updateScore(String name, int newScore) {
        if (!playerScores.containsKey(name)) {
            System.out.println("玩家不存在");
            return;
        }
        int oldScore = playerScores.get(name);
        root = deleteNode(root, name, oldScore);
        root = insertNode(root, name, newScore);
        playerScores.put(name, newScore);
    }

    public int getRank(String name) {
        if (!playerScores.containsKey(name)) return -1;
        int score = playerScores.get(name);
        return getRankHelper(root, name, score);
    }

    private int getRankHelper(Node node, String name, int score) {
        if (node == null) return 0;

        if (score > node.score || (score == node.score && name.compareTo(node.name) < 0)) {
            return getRankHelper(node.left, name, score);
        } else if (score < node.score || (score == node.score && name.compareTo(node.name) > 0)) {
            return getSize(node.left) + 1 + getRankHelper(node.right, name, score);
        } else {
            return getSize(node.left) + 1;
        }
    }

    public List<String> topK(int k) {
        List<String> result = new ArrayList<>();
        topKHelper(root, result, k);
        return result;
    }

    private void topKHelper(Node node, List<String> result, int k) {
        if (node == null || result.size() >= k) return;
        topKHelper(node.left, result, k);
        if (result.size() < k) result.add(node.name + " (" + node.score + ")");
        topKHelper(node.right, result, k);
    }

    public static void main(String[] args) {
        AVLLeaderboardSystem lb = new AVLLeaderboardSystem();
        lb.addPlayer("Alice", 50);
        lb.addPlayer("Bob", 70);
        lb.addPlayer("Charlie", 60);
        lb.addPlayer("David", 80);
        lb.addPlayer("Eve", 65);

        System.out.println("前3名: " + lb.topK(3));
        System.out.println("Bob 排名: " + lb.getRank("Bob"));

        lb.updateScore("Alice", 85);
        System.out.println("更新後前3名: " + lb.topK(3));
        System.out.println("Alice 排名: " + lb.getRank("Alice"));
    }
}
