## AST and Anticopypaster plugin for Intellij Idea

This plugin contains a tool, which shows Abstract Syntax Tree of selected code in editor. Tool activation button is located on the toolbar. Also, if user pastes code from clipboard, special message distracts user from work.

* After pressing the "AST" toolbar button `ASTAction.actionPerformed` is invoked. It constructs `ASTRangeTreeNode` object, which represents tree root, passes it to `com.intellij.ui.treeStructure.Tree` constructor and shows final tree in a separate window.
* During the start of a project `MessageOnPasteInitializer` class is loaded and `MessageOnPasteInitializer.initComponent` replaces default paste handler with custom `MessageOnPaste`, which will force a popup message to appear after base class `com.intellij.codeInsight.editorActions.PasteHandler` processes paste action.
