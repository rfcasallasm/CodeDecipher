package co.bitcarpentry.codedecipher.model;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.Relationship;

public class Interface extends DataType{

	@Relationship(type = "OWNS", direction = "OUTGOING")
	protected Package owningPackage;
	
	@Relationship(type = "OWNS", direction = "INCOMING")
	protected List<Method> methods = new ArrayList<>();
	
	public Interface() {
		super();
	}

	public Interface(String name) {
		super(name,name,VisibilityKind.PUBLIC);
	}
	
	public Interface(Package owningPackage, String name) {
		this(owningPackage, name, VisibilityKind.PUBLIC);
	}
	
	public Interface(Package owningPackage, String name, VisibilityKind visibilityKind) {
		super(owningPackage.qualifiedName+"."+name,name, visibilityKind);
	}

	public Package getOwningPackage() {
		return owningPackage;
	}

	public void setOwningPackage(Package owningPackage) {
		this.owningPackage = owningPackage;
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	
}
