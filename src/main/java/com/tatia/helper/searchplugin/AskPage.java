package com.tatia.helper.searchplugin;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.datatransfer.*;

public class AskPage  extends AnAction{

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        @Nullable Editor editor = event.getData(PlatformDataKeys.EDITOR);
        String selectedText = editor.getSelectionModel().getSelectedText();

        if (selectedText != null){
            StringSelection selection = new StringSelection(selectedText);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }

        var mess = Messages.showYesNoDialog("Do you want to go to browser?", "Go to Browser", Messages.getQuestionIcon());
        if (mess == 0) {
            String url = "https://stackoverflow.com/questions/ask";
            BrowserUtil.browse(url);
        }
    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
