package ru.hse.idea.copypaste;

import com.intellij.openapi.actionSystem.IdeActions;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.editor.actionSystem.EditorActionManager;

public class MessageOnPasteInitializer implements ProjectComponent {

    @Override
    public void initComponent() {
        var actionManager = EditorActionManager.getInstance();
        var handler = actionManager.getActionHandler(IdeActions.ACTION_EDITOR_PASTE);
        actionManager.setActionHandler(IdeActions.ACTION_EDITOR_PASTE, new MessageOnPaste(handler));
    }
}
