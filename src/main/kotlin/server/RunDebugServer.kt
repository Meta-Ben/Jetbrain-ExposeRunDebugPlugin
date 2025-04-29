package server

import com.intellij.execution.ProgramRunnerUtil
import com.intellij.execution.RunManager
import com.intellij.execution.RunnerAndConfigurationSettings
import com.intellij.execution.executors.DefaultDebugExecutor
import com.intellij.execution.executors.DefaultRunExecutor
import com.intellij.openapi.project.ProjectManager
import config.ServerConfig
import fi.iki.elonen.NanoHTTPD

class RunDebugServer() : NanoHTTPD(ServerConfig.getPort()) {
    override fun serve(session: IHTTPSession): Response {
        val params = session.parameters
        val configName = params["config"]?.firstOrNull()

        if (session.uri == "/debug" && !configName.isNullOrEmpty()) {
            val project = ProjectManager.getInstance().openProjects.firstOrNull()
            project?.let { project ->
                val runManager = RunManager.Companion.getInstance(project)

                val configSettings: RunnerAndConfigurationSettings? = runManager
                    .allSettings
                    .firstOrNull { it.name == configName }

                configSettings?.let { settings ->
                    val executor = DefaultDebugExecutor.getDebugExecutorInstance()
                    ProgramRunnerUtil.executeConfiguration(settings, executor)
                    return newFixedLengthResponse("Debug started with configuration: $configName")
                }
            }
            return newFixedLengthResponse("Configuration $configName not found")
        }

        if (session.uri == "/run" && !configName.isNullOrEmpty()) {
            val project = ProjectManager.getInstance().openProjects.firstOrNull()
            project?.let { project ->
                val runManager = RunManager.Companion.getInstance(project)

                val configSettings: RunnerAndConfigurationSettings? = runManager
                    .allSettings
                    .firstOrNull { it.name == configName }

                configSettings?.let { settings ->
                    val executor = DefaultRunExecutor.getRunExecutorInstance()
                    ProgramRunnerUtil.executeConfiguration(settings, executor)
                    return newFixedLengthResponse("Run started with configuration: $configName")
                }
            }
            return newFixedLengthResponse("Configuration $configName not found")
        }

        return newFixedLengthResponse("Unknown command")
    }
}