import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class FindPathInBinaryTree {

    public static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
    }

    public void findPath(TreeNode root, TreeNode a, TreeNode b) {
        if(root == null || a == null || b == null) {
            return;
        }
        Stack<TreeNode> stackA = new Stack<>();
        Stack<TreeNode> stackB = new Stack<>();
        findTargetNode(root, a, stackA);
        findTargetNode(root, b, stackB);
        mergePath(stackA, stackB);
    }

    private void mergePath(Stack<TreeNode> stackA, Stack<TreeNode> stackB) {
        List<TreeNode> listA = toList(stackA);
        List<TreeNode> listB = toList(stackB);
        List<TreeNode> pathA = new ArrayList<>();
        List<TreeNode> pathB = new ArrayList<>();
        int size = Math.max(listA.size(), listB.size());
        for (int i = 0; i < size; i++) {
            if(i < listA.size() && i < listB.size() && listA.get(i) != listB.get(i)) {
                pathA.add(listA.get(i));
                pathB.add(listB.get(i));
            }else {
                if(i < listA.size() && i > listB.size() - 1) {
                    pathA.add(listA.get(i));
                }
                if(i < listB.size() && i > listA.size() - 1) {
                    pathB.add(listB.get(i));
                }
            }
        }
        Collections.reverse(pathA);
        for (TreeNode node : pathA) {
            System.out.println(node);
        }
        for (TreeNode node : pathB) {
            System.out.println(node);
        }
    }

    private List<TreeNode> toList(Stack<TreeNode> stackA) {
        Stack<TreeNode> rs = new Stack<>();
        while (!stackA.isEmpty()) {
            rs.add(stackA.pop());
        }
        return rs;
    }

    private boolean findTargetNode(TreeNode root, TreeNode a, Stack<TreeNode> stack) {
        if(root == null) {
            return false;
        }
        if(root == a) {
            return true;
        }
        stack.push(root);
        if(findTargetNode(root.left, a, stack) || findTargetNode(root.right, a, stack)) {
            return true;
        }
        stack.pop();
        return false;
    }

}
