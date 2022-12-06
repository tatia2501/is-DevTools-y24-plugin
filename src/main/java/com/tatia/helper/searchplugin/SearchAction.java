package com.tatia.helper.searchplugin;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SearchAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        @Nullable Editor editor = event.getData(PlatformDataKeys.EDITOR);
        String selectedText = editor.getSelectionModel().getSelectedText();

        if (selectedText != null) {
            String encoded = "";

            try {
                encoded = URLEncoder.encode(selectedText, StandardCharsets.UTF_8.toString());
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            String url = String.format("https://stackoverflow.com/search?q=%s", encoded);

            BrowserUtil.browse(url);
        } else {
            Messages.showMessageDialog("Please, select some text", "Search Action", Messages.getInformationIcon());
        }
    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
