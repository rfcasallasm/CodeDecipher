package co.bitcarpentry.codedecipher.model;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity
public class NamedElement {

	@Id
	protected String qualifiedName;
	
	@Property
	protected String name;
	
	@Property
	protected VisibilityKind visibilityKind;
	
	public NamedElement() {
		super();
	}	
	
	public NamedElement(String qualifiedName, String name, VisibilityKind visibilityKind) {
		this();
		this.qualifiedName = qualifiedName;
		this.name = name;
		this.visibilityKind = visibilityKind;
	}

	public String getQualifiedName() {
		return qualifiedName;
	}

	public void setQualifiedName(String qualifiedName) {
		this.qualifiedName = qualifiedName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public VisibilityKind getVisibilityKind() {
		return visibilityKind;
	}

	public void setVisibilityKind(VisibilityKind visibilityKind) {
		this.visibilityKind = visibilityKind;
	}

	@Override
	public String toString() {
		return this.qualifiedName;
	}

}
