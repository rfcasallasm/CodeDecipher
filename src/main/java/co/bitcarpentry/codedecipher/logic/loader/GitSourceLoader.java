package co.bitcarpentry.codedecipher.logic.loader;

import java.io.File;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;

public class GitSourceLoader implements SourceLoader{

	public GitSourceLoader() {
		super();
		this.logger = Logger.getLogger(this.getClass().getSimpleName());
	}

	private Logger logger;
	
	@Override
	public boolean loadFromSource(URI from, URI to) {		
		CloneCommand cloneCommand = Git.cloneRepository();		
		cloneCommand.setURI(from.toString());
		cloneCommand.setDirectory( new File( to ) );		
		try {
			cloneCommand.call();
			return true;
		} catch (GitAPIException | JGitInternalException gae) {
			this.logger.log(Level.SEVERE, "Error obteniendo el repositorio", gae);
		}
		return false;
		
	}
	
		
	
	
}
