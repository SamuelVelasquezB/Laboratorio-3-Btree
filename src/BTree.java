import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BTree {

    int order;
    Node root;

    public BTree(int order) {
        this.order = order;
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int findPosition(Node node, String key) {
        int i = 0;
        while (i < node.keys.size() && node.keys.get(i).compareTo(key) < 0) {
            i++;
        }
        return i;
    }

    
    public void insertSorted(List<String> list, String key) {
        int pos = 0;
        while (pos < list.size() && list.get(pos).compareTo(key) < 0) {
            pos++;
        }
        list.add(pos, key);
    }

    public SplitResult splitNode(Node node) {
        int mid = (node.keys.size() - 1) / 2;
        String promotedKey = node.keys.get(mid);

        Node left = new Node(node.isLeaf);
        Node right = new Node(node.isLeaf);

        
        for (int i = 0; i < mid; i++) {
            left.keys.add(node.keys.get(i));
        }
        
        for (int i = mid + 1; i < node.keys.size(); i++) {
            right.keys.add(node.keys.get(i));
        }

        
        if (!node.isLeaf) {
            for (int i = 0; i <= mid; i++) {
                left.children.add(node.children.get(i));
            }
            for (int i = mid + 1; i < node.children.size(); i++) {
                right.children.add(node.children.get(i));
            }
        }

        return new SplitResult(promotedKey, left, right);
    }

    public void insert(String key) {
        // Implementación a cargo del Integrante 2
    }

   
    public boolean search(String key) {
        // Implementación a cargo del Integrante 2
        return false;
    }

   
    public void delete(String key) {
        new BTreeDeletion(this, order).delete(key);
    }

  
    public void printByLevels() {
        if (isEmpty()) {
            System.out.println("El árbol está vacío.");
            return;
        }
        Queue<NodeLevel> queue = new LinkedList<>();
        queue.add(new NodeLevel(root, 0));
        int currentLevel = -1;

        while (!queue.isEmpty()) {
            NodeLevel current = queue.poll();

            if (current.level != currentLevel) {
                if (currentLevel != -1) System.out.println();
                currentLevel = current.level;
                System.out.print("Nivel " + currentLevel + ": ");
            }

            System.out.print(current.node + "  ");

            if (!current.node.isLeaf) {
                for (Node child : current.node.children) {
                    queue.add(new NodeLevel(child, current.level + 1));
                }
            }
        }
        System.out.println();
    }
}
