package co.bitcarpentry.codedecipher.logic.extractor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import co.bitcarpentry.codedecipher.model.Class;

public abstract class SourceExtractor {
			
	protected String fileExtension;
	protected File basePath;
	protected Properties properties;
	
	private Logger logger;
	
	public SourceExtractor(String fileExtension, File basePath, Properties properties) {
		super();
		this.logger = Logger.getLogger(this.getClass().getSimpleName());
		this.fileExtension = fileExtension;
		this.basePath = basePath;
		this.properties = properties;
	}
	
	public List<Class> extractModel(){		
		return this.extractModel( this.basePath );
	}
	
	private List<Class> extractModel(File basePathFile){
		this.logger.info("Extracting from folder: "+basePathFile);
		List<Class> retValue = new ArrayList<Class>();
		for( File file : basePathFile.listFiles() ) {
			if( file.isDirectory() && !file.getName().endsWith(".git")) {
				List<Class> extractedClasses = this.extractClasses(file);
				if( extractedClasses != null ) {
					retValue.addAll(this.extractModel(file));
				}				
			}
			else if( file.getName().endsWith("."+fileExtension )){
				List<Class> extractedClasses = this.extractClasses(file);
				if( extractedClasses != null ) {
					retValue.addAll(extractedClasses);
				}
			}
		}
		return retValue;
	}
	
	public abstract List<Class> extractClasses(File filePath);
	
}
