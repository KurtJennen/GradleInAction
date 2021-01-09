package plugins.cloudbees.tasks

import com.cloudbees.api.BeesClient
import org.gradle.api.*
import org.gradle.api.tasks.*

abstract class CloudBeesTask extends DefaultTask {
	@Input String apiFormat = 'xml'
	@Input String apiVersion = '1.0'
	@Input String apiUrl = 'https://api.cloudbees.com/api'
	@Input String apiKey
	@Input String secret
	
	CloudBeesTask(String description) {
		this.description = description
		group = 'CloudBees'
	}
	
	@TaskAction
	void start() {
		withExceptionHandling {
			BeesClient client = new BeesClient(apiUrl, apiKey, secret, apiFormat, apiVersion)
			executeAction(client)
		}
	}
	
	private void withExceptionHandling(Closure c) {
		try {
			c()
		}
		catch(Exception e) {
			throw new GradleException(e.message)
		}
	}
		
	abstract void executeAction(BeesClient client)
}