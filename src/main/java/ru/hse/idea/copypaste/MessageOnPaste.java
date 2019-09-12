package ru.hse.idea.copypaste;

import com.intellij.codeInsight.editorActions.PasteHandler;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class contains logic for AntiCopyPaste tool
 */
public class MessageOnPaste extends PasteHandler {

    /**
     * Constructs instance
     * @param originalAction previous paste handler action
     */
    public MessageOnPaste(EditorActionHandler originalAction) {
        super(originalAction);
    }

    /**
     * This method is responsible for showing message after pasting
     * @param editor editor with pasted code
     * @param caret caret in editor
     * @param dataContext context
     */
    @Override
    public void doExecute(@NotNull Editor editor, @Nullable Caret caret, DataContext dataContext) {
        super.doExecute(editor, caret, dataContext);
        JBPopupFactory.getInstance().createMessage("Запахам в коде скажем нет!").showInBestPositionFor(editor);
    }
}