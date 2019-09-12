package ru.hse.idea.ast;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.treeStructure.Tree;

import javax.swing.*;

/**
 * This class implements tool, which shows Abstract Syntax Tree of selected code in editor.
 * Tool runs by button located on toolbar.
 */
public class ASTAction extends AnAction {

    /**
     * This method builds AST and shows it in a separate window
     * Something went wrong or empty range of text was selected: method returns
     * @param anActionEvent standard parameter for such method in base class
     */
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

    /**
     * This method in base class is overriden so that short description of tool is shown under button
     * @return Always true
     */
    @Override
    public boolean displayTextInToolbar() {
        return true;
    }
}
