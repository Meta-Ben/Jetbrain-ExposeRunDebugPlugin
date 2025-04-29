package views

import ServerSettingsPanel
import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

class ServerSettingsConfigurable : Configurable {

    private val serverSettingsView = ServerSettingsPanel()

    override fun getDisplayName(): String = "Expose Run/Debug Plugin Settings"

    override fun createComponent(): JComponent? {
        return serverSettingsView.create()
    }

    override fun isModified(): Boolean {
        return true
    }

    override fun apply() {
        serverSettingsView.applySettings()
    }
}
