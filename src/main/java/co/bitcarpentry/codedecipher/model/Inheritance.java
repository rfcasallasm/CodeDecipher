package co.bitcarpentry.codedecipher.model;

import org.neo4j.ogm.annotation.RelationshipEntity;

@RelationshipEntity(type="INHERITS_FROM")
public class Inheritance extends Relationship {
		
	@Override
	public String toString() {
		return this.fromClass+" INHERITS_FROM ("+this.name+") "+this.toClass;
	}
	
	
}
