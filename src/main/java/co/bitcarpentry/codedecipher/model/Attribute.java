package co.bitcarpentry.codedecipher.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Attribute extends NamedElement{

	@Relationship(type = "TYPE", direction = "OUTGOING")
	protected DataType type;
	
	@Relationship(type = "OWNS", direction = "OUTGOING")
	protected Class owningClass;

	public Attribute() {
		super();
	}
	
	public Attribute(
			Class owningClass, 
			String name, 
			VisibilityKind visibilityKind,
			DataType type) {
		super(owningClass.qualifiedName+"."+name, name, visibilityKind);
		this.type = type;
	}

	public DataType getType() {
		return type;
	}

	public void setType(DataType type) {
		this.type = type;
	}

	public Class getOwningClass() {
		return owningClass;
	}

	public void setOwningClass(Class owningClass) {
		this.owningClass = owningClass;
	}

	@Override
	public String toString() {
		return (this.type!=null?this.type:"void")+" "+ this.qualifiedName;
	}
	
	
}
