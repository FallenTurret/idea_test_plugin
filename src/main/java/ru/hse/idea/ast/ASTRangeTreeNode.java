package ru.hse.idea.ast;

import com.intellij.lang.ASTNode;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

/**
 * This class implements logic of building range subtree from general AST.
 * This class also implements swing interface of tree node for convenient output
 */
public class ASTRangeTreeNode implements TreeNode {

    private TreeNode parent;
    private String name;
    private ArrayList<TreeNode> children;

    private static ASTNode largestCommonAncestor(ASTNode left, ASTNode right) {
        while (!left.getTextRange().contains(right.getTextRange())) {
            left = left.getTreeParent();
        }
        return left;
    }

    /**
     * Constructs subtree of general AST with root in largest common ancestor of argument nodes
     * @param left node, which will represent far left lists
     * @param right node, which will represent far right list
     */
    public ASTRangeTreeNode(ASTNode left, ASTNode right) {
        this(ASTRangeTreeNode.largestCommonAncestor(left, right), null,
                left.getTextRange().getStartOffset(),
                right.getTextRange().getEndOffset());
    }

    private ASTRangeTreeNode(ASTNode node, TreeNode parent, int left, int right) {
        this.parent = parent;
        this.name = node.toString();
        children = new ArrayList<>();
        for (var child: node.getChildren(null)) {
            if (child.getTextRange().getEndOffset() >= left && child.getTextRange().getStartOffset() <= right) {
                children.add(new ASTRangeTreeNode(child, this, left, right));
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return children.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i) == node) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return Collections.enumeration(children);
    }
}