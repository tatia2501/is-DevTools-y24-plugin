package com.tatia.helper.poems;

import com.intellij.notification.*;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Objects;

public class QuotesShow  extends AnAction {
    public static final NotificationGroup GROUP_DISPLAY_ID_INFO = new NotificationGroup("My notification group", NotificationDisplayType.BALLOON, true);

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        assert editor != null;
        var project = event.getData(PlatformDataKeys.PROJECT);
        assert project != null;

        String[] names = {"", "Carol Burnett", "Albert Einstein", "Marva Collins", "Thomas Edison", "Mark Twain", "Estee Lauder"};

        JFrame frame = new JFrame();
        var author = (String)JOptionPane.showInputDialog(frame, "Write author name", "Author", JOptionPane.QUESTION_MESSAGE, Messages.getInformationIcon(), names, names[0]);

        if (Objects.equals(author, "")){
            Notification notification = GROUP_DISPLAY_ID_INFO.createNotification("You must choose something", NotificationType.ERROR);
            Notifications.Bus.notify(notification, project);
        } else {
        String data;

        if (Objects.equals(author, "Carol Burnett")) {
            data = "'Only I can change my life. No one can do it for me'";
        } else if (Objects.equals(author, "Albert Einstein")) {
            data = "'In the middle of difficulty lies opportunity'";
        } else if (Objects.equals(author, "Marva Collins")) {
            data = "'Success does not come to you. You go to it'";
        } else if (Objects.equals(author, "Thomas Edison")) {
            data = "'Genius is one percent inspiration, and ninety-nine percent perspiration'";
        } else if (Objects.equals(author, "Mark Twain")) {
            data = "'Twenty years from now you will be more disappointed by the things that you did not do than by the ones you did do'";
        } else if (Objects.equals(author, "Estee Lauder")) {
            data = "'I did not get there by wishing for it or hoping for it, but by working for it'";
        } else {
            data = "";
        }

        Runnable r = ()-> EditorModificationUtil.insertStringAtCaret(editor, data);
        WriteCommandAction.runWriteCommandAction(project, r);
        }
    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
