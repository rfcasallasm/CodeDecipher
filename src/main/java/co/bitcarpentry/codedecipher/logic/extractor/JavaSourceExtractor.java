package co.bitcarpentry.codedecipher.logic.extractor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.javaparser.JavaParser;

import co.bitcarpentry.codedecipher.model.Class;

public class JavaSourceExtractor extends SourceExtractor {
	
	private Logger logger;
	private JavaParser javaParser;
	
	
	public JavaSourceExtractor(File basePath, Properties properties) {
		super("java", basePath, properties);
		this.javaParser = new JavaParser();
		this.logger = Logger.getLogger(this.getClass().getSimpleName());			
	}

	@Override
	public List<Class> extractClasses(File filePath) {
		JavaVisitorAdapter jVisitorAdapter = new JavaVisitorAdapter();
		try {			
			jVisitorAdapter.visit(this.javaParser.parse(filePath).getResult().get(), null);
		}
		catch(IOException pe) {
			//logger.log(Level.SEVERE, "No se pudo procesar el archivo \""+filePath+"\""+pe.getMessage(), pe);
			logger.log(Level.SEVERE, "No se pudo procesar el archivo \""+filePath+"\""+pe.getMessage());
		}						
		return jVisitorAdapter.getClasses();
	}


}
