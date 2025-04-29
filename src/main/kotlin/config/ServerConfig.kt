package config

import com.intellij.ide.util.PropertiesComponent

object ServerConfig {
    private const val PORT_KEY = "plugin.run.debug.server.port"

    fun getPort(): Int {
        val storedPort = PropertiesComponent.getInstance().getValue(PORT_KEY)
        return storedPort?.toIntOrNull() ?: 5555
    }

    fun setPort(port: Int) {
        PropertiesComponent.getInstance().setValue(PORT_KEY, port.toString())
    }
}