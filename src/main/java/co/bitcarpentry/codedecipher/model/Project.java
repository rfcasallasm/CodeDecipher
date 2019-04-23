package co.bitcarpentry.codedecipher.model;

import java.io.Serializable;
import java.net.URL;

public class Project implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5674314226909760183L;

	protected String name;
	
	protected Integer port;
	
	protected URL url;
	
	protected String description;
	
	protected String uploader;
	
	public Project() {
		super();
	}
	
	public Project(String name, URL url, String description, String uploader) {
		super();
		this.name = name;
		this.url  = url;
		this.description = description;
		this.uploader = uploader;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
		
}
