package dev.robotfield;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

public final class RobotFieldToolWindow implements ToolWindowFactory, DumbAware {
    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        RobotField field = new RobotField();
        Content content = ContentFactory.getInstance().createContent(field, "", false);
        content.setPreferredFocusableComponent(field);
        toolWindow.getContentManager().addContent(content);
    }
}
//.\gradlew.bat -p ./FieldTester buildPlugin