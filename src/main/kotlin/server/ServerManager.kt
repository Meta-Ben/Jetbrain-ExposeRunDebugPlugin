import config.ServerConfig
import server.RunDebugServer
import java.util.logging.Logger

object ServerManager {

    private var server: RunDebugServer? = null
    var isRunning : Boolean = false

    private val logger : Logger = Logger.getLogger(ServerManager::class.java.name)

    fun startServer() {
        //Stop server if already running
        stopServer()

        server = RunDebugServer()
        try {
            server?.start()
            isRunning = true
            logger.info("Run/Debug Server started on port : " + ServerConfig.getPort())
        } catch (e: Exception) {
            logger.warning("Error during server launch: "+ e.message)
            e.printStackTrace()
        }
    }

    fun stopServer() {
        try {
            server?.stop()
            server = null
            isRunning = false
            logger.info("Run/Debug Server stopped")
        } catch (e: Exception) {
            logger.warning("Error on server stop: " + e.message)
            e.printStackTrace()
        }
    }

}
