package ru.hse.idea.ast;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.treeStructure.Tree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class ASTAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        var editor = anActionEvent.getData(CommonDataKeys.EDITOR);
        var psiFile = anActionEvent.getData(CommonDataKeys.PSI_FILE);
        if (editor == null || psiFile == null) {
            return;
        }
        if (!editor.getSelectionModel().hasSelection()) {
            return;
        }
        var selectionStart = psiFile.findElementAt(editor.getSelectionModel().getSelectionStart());
        var selectionEnd = psiFile.findElementAt(editor.getSelectionModel().getSelectionEnd());
        if (selectionStart == null || selectionEnd == null) {
            return;
        }
        var startNode = selectionStart.getNode();
        var endNode = selectionEnd.getNode();
        var root = new ASTRangeTreeNode(startNode, endNode);
        var tree = new Tree(root);
        var frame = new JFrame("AST");
        frame.setContentPane(ToolbarDecorator.createDecorator(tree).createPanel());
        frame.pack();
        frame.setVisible(true);
    }

    private void dfs(TreeNode x) {
        System.out.println(x);
        for (int i = 0; i < x.getChildCount(); i++) {
            dfs(x.getChildAt(i));
        }
    }

    @Override
    public boolean displayTextInToolbar() {
        return true;
    }
}
