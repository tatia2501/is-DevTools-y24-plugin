package com.tatia.helper.picture;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class OpeningListener implements ProjectManagerListener {
    @Override
    public void projectOpened(@NotNull Project project){
        if (ApplicationManager.getApplication().isUnitTestMode()){
            return;
        }

        try {
            StartPicture.main();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}