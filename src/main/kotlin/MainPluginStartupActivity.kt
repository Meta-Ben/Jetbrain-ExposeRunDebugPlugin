import com.intellij.openapi.Disposable
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.intellij.openapi.util.Disposer

class MainPluginStartupActivity : StartupActivity {

    override fun runActivity(project: Project) {
        ServerManager.startServer()

        Disposer.register(project, Disposable {
           ServerManager.stopServer()
        })
    }
}
