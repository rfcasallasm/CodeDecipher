package co.bitcarpentry.codedecipher.model;

import org.neo4j.ogm.annotation.RelationshipEntity;

@RelationshipEntity(type="IS_COMPOSED_OF")
public class Composition extends Association {

	@Override
	public String toString() {
		return this.fromClass+" IS_COMPOSED_OF ("+this.name+") "+this.toClass;
	}
	
}
