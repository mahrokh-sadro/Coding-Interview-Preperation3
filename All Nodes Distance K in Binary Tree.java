// Given the root of a binary tree, the 
// value of a target node target, and an 
// integer k, return an array of the values 
// of all nodes that have a distance k from the target node.
// You can return the answer in any order.

// The number of nodes in the tree is in the
// range [1, 500].
// 0 <= Node.val <= 500
// All the values Node.val are unique.
// target is the value of one of the nodes 
// in the tree.
// 0 <= k <= 1000
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

// -------------------------------
// Step 1: BFS to build parent map
// -------------------------------
// We need to move "up" from the target as well as down.
// Tree nodes don’t have parent pointers, so we store each child → parent.
    Map<TreeNode, TreeNode> parent = new HashMap<>();
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);

    while (!q.isEmpty()) {
        TreeNode node = q.poll();
        // If left child exists, record its parent and add to queue
        if (node.left != null) {
            parent.put(node.left, node);
            q.offer(node.left);
        }
        // If right child exists, record its parent and add to queue
        if (node.right != null) {
            parent.put(node.right, node);
            q.offer(node.right);
        }
    }
// --------------------------------------
// Step 2: BFS from target node to distance k
// --------------------------------------
// We treat the tree as an undirected graph:
// neighbors = left, right, parent

    Set<TreeNode> visited = new HashSet<>(); 
    q.offer(target); // Start BFS from target
    visited.add(target);

    int dist = 0; // Current distance from target

    while (!q.isEmpty()) {
        int size = q.size(); 
// If current distance == k, all nodes in queue are at distance k
        if (dist == k) {
            List<Integer> res = new ArrayList<>();
            for (TreeNode node : q) {
                res.add(node.val);
            }
            return res; // Return all nodes at exact distance k
        }
    // BFS: expand all neighbors of nodes at current distance
        for (int i = 0; i < size; i++) {
            TreeNode node = q.poll();
            // Neighbors: left child, right child, parent
            if (node.left != null && visited.add(node.left)) {
                q.offer(node.left);
            }
            if (node.right != null && visited.add(node.right)) {
                q.offer(node.right);
            }
            TreeNode p = parent.get(node);
            if (p != null && visited.add(p)) {
                q.offer(p);
            }
        }
// Increment distance after expanding this BFS level
        dist++; 
    }
    // If there are no nodes at distance k, return empty list
    return new ArrayList<>();
}

//time:o(n)
//space:o(n)