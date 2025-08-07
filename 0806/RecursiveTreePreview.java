import java.util.*;
public class RecursiveTreePreview {
    static class FileNode {
        String name;
        List<FileNode> children;

        public FileNode(String name) {
            this.name = name;
            this.children = new ArrayList<>();
        }

        public void add(FileNode child) {
            children.add(child);
        }
    }

    public static int countFiles(FileNode node) {
        if (node.children.isEmpty()) return 1;
        int count = 0;
        for (FileNode child : node.children)
            count += countFiles(child);
        return count;
    }

    public static void printTree(FileNode node, String indent) {
        System.out.println(indent + node.name);
        for (FileNode child : node.children)
            printTree(child, indent + "  ");
    }

    public static List<String> flatten(FileNode node) {
        List<String> list = new ArrayList<>();
        list.add(node.name);
        for (FileNode child : node.children)
            list.addAll(flatten(child));
        return list;
    }

    public static int maxDepth(FileNode node) {
        if (node.children.isEmpty()) return 1;
        int max = 0;
        for (FileNode child : node.children)
            max = Math.max(max, maxDepth(child));
        return max + 1;
    }
}
