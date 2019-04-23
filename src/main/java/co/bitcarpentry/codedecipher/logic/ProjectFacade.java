package co.bitcarpentry.codedecipher.logic;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import co.bitcarpentry.codedecipher.logic.exception.LogicException;
import co.bitcarpentry.codedecipher.model.Project;

public class ProjectFacade {

	private static volatile ProjectFacade _instance;
	
	public static ProjectFacade getInstance() {
		if(_instance == null) {
			synchronized (ProjectFacade.class) {
				if(_instance == null) {
					_instance = new ProjectFacade();
				}				
			}
		}
		return _instance;
	}

	private List<Project> projects;
	
	private ProjectFacade() {
		super();
		this.projects = new ArrayList<>(); 
	}

	public List<Project> getProjects() {
		return projects;
	}

	public Project getProjectByName(String name) {
		Project retValue = null;
		for( Project project : this.projects ) {
			if( project.getName().equals(name) ) {
				retValue = project;
				break;
			}
		}
		return retValue;
	}
	
	public void createProject(Project project) throws LogicException{
		try {
			CodeDecipherFacade codeDecipherFacade = new CodeDecipherFacade(project.getName());
			codeDecipherFacade.init();
			codeDecipherFacade.loadSource( project.getUrl().toURI() );
			codeDecipherFacade.extractSource();
			project.setPort( GraphDatabaseFacade.getInstance().getBoltPort(project.getName()) );
			this.projects.add(project);
		}
		catch(URISyntaxException use) {
			throw new LogicException("Error creando el proyecto", use);
		}
	}
	
}
