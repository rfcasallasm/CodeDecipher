package co.bitcarpentry.codedecipher.model;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Package extends NamedElement{
	
	@Relationship(type = "OWNS", direction = "OUTGOING")
	protected Package owningPackage;
	
	@Relationship(type = "OWNS", direction = "INCOMING")
	protected List<Package> ownedPackages = new ArrayList<>();
	
	public Package() {
		super();
	}
	
	public Package(String name) {
		super(name, name, VisibilityKind.PUBLIC);
	}
	
	public Package(Package owningPackage, String name) {
		super(owningPackage.qualifiedName+"."+name, name, VisibilityKind.PUBLIC);
	}

	public Package getOwningPackage() {
		return owningPackage;
	}

	public void setOwningPackage(Package owningPackage) {
		this.owningPackage = owningPackage;
	}

	public List<Package> getOwnedPackages() {
		return ownedPackages;
	}

	public void setOwnedPackages(List<Package> ownedPackages) {
		this.ownedPackages = ownedPackages;
	}

	@Override
	public String toString() {
		return this.qualifiedName;
	}
	
	
	
}
