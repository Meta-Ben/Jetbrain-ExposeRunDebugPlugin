import com.intellij.openapi.observable.properties.GraphProperty
import com.intellij.openapi.observable.properties.PropertyGraph
import com.intellij.openapi.observable.util.not
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.JBColor
import com.intellij.ui.dsl.builder.*
import config.ServerConfig

class ServerSettingsPanel {

    private val propertyGraph = PropertyGraph()

    private val portProperty: GraphProperty<Int> = propertyGraph.property(ServerConfig.getPort())

    private val serverRunningProperty: GraphProperty<Boolean> =
        propertyGraph.property(ServerManager.isRunning)

    private val serverRunningStatusText: GraphProperty<String> =
        propertyGraph.property(getStatusServerMessage())


    fun create(): DialogPanel {
        return panel {
            group {

                row("Exposing Run/Debug on port:") {
                    intTextField(0..65535)

                        .columns(10)
                        .bindIntText(portProperty)
                        .enabledIf(serverRunningProperty.not())
                        .whenTextChangedFromUi { text ->
                            text.toIntOrNull()?.let { intText ->
                                ServerConfig.setPort(intText)
                            }
                        }
                }

                row {
                    button("Start") {
                        ServerManager.startServer()
                        serverRunningProperty.set(true)
                        serverRunningStatusText.set(getStatusServerMessage())
                    }.enabledIf(serverRunningProperty.not())

                    button("Stop") {
                        ServerManager.stopServer()
                        serverRunningProperty.set(false)
                        serverRunningStatusText.set(getStatusServerMessage())
                    }.enabledIf(serverRunningProperty)
                }

                row {
                    val statusLabel = label("")
                        .bindText(serverRunningStatusText)
                        .component

                        statusLabel.foreground = if (serverRunningProperty.get()) JBColor.GREEN else JBColor.RED

                        serverRunningProperty.afterChange {
                            statusLabel.foreground = if (it) JBColor.GREEN else JBColor.RED
                        }
                }

                row {
                    label("Apply will automatically restart the server if started")
                        .apply {
                            component.foreground = JBColor.GRAY
                            component.font = component.font.deriveFont(component.font.style or java.awt.Font.ITALIC)
                        }
                }
            }
        }
    }

    fun getStatusServerMessage() : String{
        return if (ServerManager.isRunning) {
            "Server is running on port " + ServerConfig.getPort() + " you can access it from http://localhost:" + ServerConfig.getPort() + "/debug or /run"
        } else {
            "Server is not running"
        }
    }

    fun applySettings() {
        if(serverRunningProperty.get()) {
            ServerManager.startServer()
        }
    }
}

