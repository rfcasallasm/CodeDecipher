package co.bitcarpentry.codedecipher.model;

import java.util.Arrays;
import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Method extends NamedElement{

	@Relationship(type = "TYPE", direction = "OUTGOING")
	protected DataType type;
	
	@Relationship(type = "OWNS", direction = "OUTGOING")
	protected List<DataType> parameters;
	
	@Relationship(type = "OWNS", direction = "OUTGOING")
	protected Interface owningInterface;

	public Method() {
		super();
	}
	
	public Method(
			Interface owningInterface, 
			String name, 
			VisibilityKind visibilityKind,
			DataType type,
			List<DataType> parameters) {
		super(owningInterface.qualifiedName+"."+name, name, visibilityKind);
		this.type = type;
		this.parameters = parameters;
	}
	
	public DataType getType() {
		return type;
	}

	public void setType(DataType type) {
		this.type = type;
	}

	public List<DataType> getParameters() {
		return parameters;
	}

	public void setParameters(List<DataType> parameters) {
		this.parameters = parameters;
	}

	public Interface getOwningInterface() {
		return owningInterface;
	}

	public void setOwningInterface(Interface owningInterface) {
		this.owningInterface = owningInterface;
	}

	@Override
	public String toString() {
		return (this.type!=null?this.type:"void")+" "+ this.qualifiedName + "(" + (this.parameters!=null?Arrays.toString(this.parameters.toArray()):"") + ");";
	}
	
}
