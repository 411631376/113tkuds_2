public class AVLNode {
    int data;
    AVLNode left, right;
    int height;

    public AVLNode(int data) {
        this.data = data;
        this.height = 1; // 新節點的高度預設為 1（葉節點）
    }

    // 取得節點高度（避免重複 null 判斷）
    private int getHeight(AVLNode node) {
        return (node != null) ? node.height : 0;
    }

    // 計算平衡因子（左高 - 右高）
    public int getBalance() {
        return getHeight(left) - getHeight(right);
    }

    // 更新節點高度
    public void updateHeight() {
        this.height = Math.max(getHeight(left), getHeight(right)) + 1;
    }
}
