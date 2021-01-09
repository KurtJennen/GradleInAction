package plugins.cloudbees

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.WarPlugin
import plugins.cloudbees.tasks.CloudBeesTask
import plugins.cloudbees.tasks.app.CloudBeesAppInfo1
import plugins.cloudbees.tasks.app.CloudBeesAppDeployWar
import plugins.cloudbees.CloudBeesPluginExtension

class CloudBeesPlugin implements Plugin<Project> {
	static final String EXTENSION_NAME = 'cloudBees'
	
	@Override
	void apply(Project project) {
		project.plugins.apply(WarPlugin)
		project.extensions.create(EXTENSION_NAME, CloudBeesPluginExtension)
		addTasks(project)
	}
	
	private void addTasks(Project project) {
		project.tasks.withType(CloudBeesTask) {
//			apiUrl = 'https://api.cloudbees.com/api'
//			apiKey = project.property('cloudBeesApiKey')
//			secret = project.property('cloudBeesApiSecret')
			def extension = project.extensions.findByName(EXTENSION_NAME)
			conventionMapping.apiUrl = { extension.apiUrl }
			conventionMapping.apiKey = { extension.apiKey }
			conventionMapping.secret = { extension.secret }
		}
		
		addAppTasks(project)
	}
	
	private void addAppTasks(Project project) {
		project.task('cloudBeesAppInfo', type: CloudBeesAppInfo1) {
			conventionMapping.appId = { getAppId(project) }
		}
		
		project.task('cloudBeesAppDeployWar', type: CloudBeesAppDeployWar) {
//			appId = getAppId(project)
//			message = project.hasProperty('message') ? project.message : null
//			warFile = getWarFile(project)
			conventionMapping.appId = { getAppId(project) }
			conventionMapping.message = { project.hasProperty('message') ? project.message : null }
			conventionMapping.warFile = { getWarFile(project) }
		}
	}
	
	private String getAppId(Project project) {
		project.hasProperty('appId') ? project.appId : null
	}

	private File getWarFile(Project project) {
		project.hasProperty('warFile') ? new File(project.getProperty('warFile')) : project.tasks.getByName(WarPlugin.WAR_TASK_NAME).archivePath
	}
}