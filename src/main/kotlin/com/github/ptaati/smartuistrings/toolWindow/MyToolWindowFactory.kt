package com.github.ptaati.smartuistrings.toolWindow

import com.github.ptaati.smartuistrings.MyBundle
import com.github.ptaati.smartuistrings.services.MyProjectService
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.components.JBTextField
import com.intellij.ui.content.ContentFactory
import java.awt.GridLayout
import javax.swing.JButton


class MyToolWindowFactory : ToolWindowFactory {

    init {
        thisLogger().warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }

    private val contentFactory = ContentFactory.SERVICE.getInstance()

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = MyToolWindow(toolWindow)
        val content = contentFactory.createContent(myToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true

    class MyToolWindow(toolWindow: ToolWindow) {

        private val service = toolWindow.project.service<MyProjectService>()

        fun getContent() = JBPanel<JBPanel<*>>().apply {
//            val label = JBLabel(MyBundle.message("randomLabel", "?"))
//
//            add(label)
//            add(JButton(MyBundle.message("shuffle")).apply {
//                addActionListener {
//                    label.text = MyBundle.message("randomLabel", service.getRandomNumber())
//                }
//            })


            val titleVariableLabel = JBLabel("Please enter ui strings name.")
            val titleThaiTextLabel = JBLabel("Please enter thai text.")
            val titleEnglishTextLabel = JBLabel("Please enter english text.")

            val variableTextField = JBTextArea()
            val thaiTextField = JBTextArea()
            val englishTextField = JBTextArea()

            add(titleVariableLabel)
            add(variableTextField)

            add(titleThaiTextLabel)
            add(thaiTextField)

            add(titleEnglishTextLabel)
            add(englishTextField)


            val uiStringLabel = JBLabel("UI String")
            val uiStringField = JBTextArea()

            val thaiLocalizeValueLabel = JBLabel("Thai Localize value")
            val thaiLocalizeValueField = JBTextArea()

            val englishLocalizeValueLabel = JBLabel("English Localize value")
            val englishLocalizeValueField = JBTextArea()

            val thaiLocalizeValueTestLabel = JBLabel("Test Thai Localize value")
            val thaiLocalizeValueTestField = JBTextArea()

            val englishLocalizeValueTestLabel = JBLabel("Test English Localize value")
            val englishLocalizeValueTestField = JBTextArea()

            add(JButton("Generate").apply {
                addActionListener {
                    val variableName = variableTextField.text
                    uiStringField.text = "static String get "+variableName+" => LanguageLocalizations.getText('"+ variableName +"');"
                    thaiLocalizeValueField.text = "'"+ variableName +"': '"+ thaiTextField.text+"',"
                    englishLocalizeValueField.text = "'"+ variableName +"': '"+ englishTextField.text+"',"
                    thaiLocalizeValueTestField.text = " expect(\n" +
                            "      LanguageLocalizations.getText('"+ variableName +"'),\n" +
                            "      '"+thaiTextField.text +"',\n" +
                            "    );"
                    englishLocalizeValueTestField.text = " expect(\n" +
                            "      LanguageLocalizations.getText('"+ variableName +"'),\n" +
                            "      '"+englishTextField.text +"',\n" +
                            "    );"
                }
            })

            add(uiStringLabel)
            add(uiStringField)

            add(thaiLocalizeValueLabel)
            add(thaiLocalizeValueField)

            add(englishLocalizeValueLabel)
            add(englishLocalizeValueField)

            add(thaiLocalizeValueTestLabel)
            add(thaiLocalizeValueTestField)

            add(englishLocalizeValueTestLabel)
            add(englishLocalizeValueTestField)

            val gridLayout = GridLayout(20, 1)
            layout = gridLayout
        }
    }
}
