package co.bitcarpentry.codedecipher.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;

import co.bitcarpentry.codedecipher.logic.ProjectFacade;
import co.bitcarpentry.codedecipher.logic.exception.LogicException;
import co.bitcarpentry.codedecipher.model.Project;

@javax.inject.Named
@javax.faces.view.ViewScoped
public class ProjectCtrl implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6961561557617640481L;

	
	private Project project;
	
	public ProjectCtrl() {
		super();
	}
	
	public List<Project> getProjects() {
		return ProjectFacade.getInstance().getProjects();
	}
	
	public String createProject() {
		try{
			ProjectFacade.getInstance().createProject(project);
			return "/index?faces-redirect=true";
		}
		catch(LogicException fe) {
			//TODO: hacer algo con esto
			return null;
		}
	}

	public Project getProject() {
		if( this.project == null ) {
			String name = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("name");
			if( name != null ) {
				this.project = ProjectFacade.getInstance().getProjectByName(name);
			}
			if(this.project == null) {
				this.project = new Project();
			}
		}		
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
		
}
