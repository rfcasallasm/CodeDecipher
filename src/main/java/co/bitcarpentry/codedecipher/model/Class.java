package co.bitcarpentry.codedecipher.model;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Class extends Interface {

	@Relationship(type = "OWNS", direction = "INCOMING")
	protected List<Attribute> attributes = new ArrayList<>();
	
	public Class() {
		super();
	}

	public Class(String name) {
		super(name);
	}
	
	public Class(Package owningPackage, String name) {
		super(owningPackage, name);
	}
	
	public Class(Package owningPackage, String name, VisibilityKind visibilityKind) {
		super(owningPackage, name, visibilityKind);
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	
}
