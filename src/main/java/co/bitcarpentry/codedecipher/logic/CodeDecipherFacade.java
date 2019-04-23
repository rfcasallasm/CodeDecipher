package co.bitcarpentry.codedecipher.logic;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;

import co.bitcarpentry.codedecipher.logic.extractor.JavaSourceExtractor;
import co.bitcarpentry.codedecipher.logic.extractor.SourceExtractor;
import co.bitcarpentry.codedecipher.logic.loader.GitSourceLoader;
import co.bitcarpentry.codedecipher.logic.loader.SourceLoader;
import co.bitcarpentry.codedecipher.model.Class;

public class CodeDecipherFacade {
	
	private String name;
	private File basePath;
	
	private Logger logger;
	
	private GraphDatabaseFacade graphDatabaseFacade;
	
	private SourceLoader sourceLoader;
	private SourceExtractor sourceExtractor;
	
	public CodeDecipherFacade(String name) {
		super();		
		this.name                = name;				
		this.graphDatabaseFacade = GraphDatabaseFacade.getInstance();
		this.logger              = Logger.getLogger("CodeDecipher");
	}
	
	private File resolveBy(String name) {
		return new File(System.getProperty("java.io.tmpdir")+"code-decipher/"+name);
	}
	
	public void init() {		
		this.basePath = this.resolveBy(name);
		if(this.basePath.exists()) {
			this.graphDatabaseFacade.deleteDatabase(name);
			try {
				this.logger.info(this.name+": deleting existing folder");
				FileUtils.deleteDirectory(this.basePath);				
			} catch (IOException ioe) {
				this.logger.log(Level.WARNING, this.name+": deleting existing folder", ioe );
			}
		}
		this.sourceLoader = new GitSourceLoader();
		this.sourceExtractor = new JavaSourceExtractor(this.basePath, new Properties());
	}
	
	public void loadSource(URI from) {
		File sourceFile = new File(this.basePath, "/code");
		this.sourceLoader.loadFromSource(from, sourceFile.toURI() );
	}
	
	public void extractSource() {
		List<Class> classes = this.sourceExtractor.extractModel();
		Session dbSession = this.graphDatabaseFacade.createSession(this.name);
		Transaction transaction = null;
		try {
			transaction = dbSession.beginTransaction();			
			for( Class clase : classes ) {
				dbSession.save(clase);
			}
			transaction.commit();
		}
		catch(Throwable e) {
			this.logger.log(Level.SEVERE, "Error guardando modelo", e);
			transaction.rollback();
		}
		finally {
			//TODO: no hay que cerrar nada?
		}
	}
	
	public void finish() {
		
	}
	
}
