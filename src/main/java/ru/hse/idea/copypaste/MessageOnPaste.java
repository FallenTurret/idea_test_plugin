package ru.hse.idea.copypaste;

import com.intellij.codeInsight.editorActions.PasteHandler;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MessageOnPaste extends PasteHandler {

    public MessageOnPaste(EditorActionHandler originalAction) {
        super(originalAction);
    }
    
    @Override
    public void doExecute(@NotNull Editor editor, @Nullable Caret caret, DataContext dataContext) {
        super.doExecute(editor, caret, dataContext);
        JBPopupFactory.getInstance().createMessage("Запахам в коде скажем нет!").showInBestPositionFor(editor);
    }
}